package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import nosqlite.utilities.Utils;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class Database {

    private Connection conn;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database/api-workshop-database.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(String username) {
        User user = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users WHERE username = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            User[] userFromRS = (User[]) Utils.resultSetToObject(rs, User[].class);

            user = userFromRS[0];

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean validateUser(User user) {

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (Objects.equals(user.getUsername(), rs.getString("username"))) {
                    if (Objects.equals(user.getPassword(), rs.getString("password"))) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public List<User> getUsers() {
        List<User> users = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users");
            ResultSet rs = stmt.executeQuery();

            User[] usersFromRS = Utils.resultSetToObject(rs, User[].class);
            users = List.of(usersFromRS);

//            while (rs.next()) {
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//
//                User user = new User(username, password);
//                users.add(user);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void createUser(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users (username, password) VALUES(?, ?)");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserPassword(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Users SET password = ? WHERE username = ?");

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUsername());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement(("DELETE FROM Users WHERE username= ?"));
            stmt.setString(1, user.getUsername());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
