package opa21dbexcercise;

import java.sql.*;
import java.util.ArrayList;

/*
    C - Create
    R - Read
    U - Update
    D - Delete
 */

public class Database {

    private Connection conn = null;

    public Database() {

        try {

            conn = DriverManager.getConnection("jdbc:sqlite:my-awesome-database.db");
        }
        catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT id, name as user_name, age" +
                " FROM users " +
                "ORDER BY name";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            // resultSet is an iterator
            // to loop an iterator we have to use
            // a while loop with the condition "resultSet.next()"

            // each iteration in the while loop
            // the resultSet becomes the row in the table

            while(resultSet.next()) {
                // we can extract the value from a column
                // by specifying the type and column name
                String userName = resultSet.getString("user_name");
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");

                users.add(new User(id, userName, age));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public User getUserById(int id) {
        // "typical" SQL injection, by concatenating strings
//        String sqlInjectionId = "2; SELECT * FROM admins";
//        String query = "SELECT * FROM users WHERE id = " + sqlInjectionId;

        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            // secure from SQL injections
            stmt.setInt(1, id);

            // executeQuery() is only for reading data
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                int userAge = resultSet.getInt("age");

                user = new User(userId, userName, userAge);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public int createUser(User newUser) {
        int incrementedId = 0;
        String query = "INSERT INTO users(name, age) VALUES(?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newUser.getName());
            stmt.setInt(2, newUser.getAge());

            // executeUpdate() is when we want to change some
            // data in the database
            stmt.executeUpdate();

            // we can get the auto incremented id  from
            // the stmt after update is run
            ResultSet generatedKeys = stmt.getGeneratedKeys();

            while (generatedKeys.next()) {
                incrementedId = generatedKeys.getInt(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return incrementedId;
    }

    public void updateUser(User user) {
        // only update users with an id
        if(user.getId() < 1) {
            System.out.println("Must have an id!");
            return; // cancels the method
        }
        // validate that a user with the same id exists

        String query = "UPDATE users SET name = ?, age = ? WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getAge());
            stmt.setInt(3, user.getId());

            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user) {

        deleteUserById(user.getId());
    }

    public void deleteUserById(int id) {
        // validate the ID

        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
