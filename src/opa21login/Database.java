package opa21login;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection conn = null;

    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:game-database.db");

        }
        catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    public Player getPlayerByName(String name) {
        Player player = null;
        String query = "SELECT * FROM players WHERE username = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String playerName = resultSet.getString("username");
                String playerPassword = resultSet.getString("password");

                player = new Player(playerName, playerPassword);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return player;
    }

    public boolean checkPassword(String name, String password) {
        String query = "SELECT password FROM players WHERE username = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.getString("password").equals(password)) {
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicateName(String name) {
        String query = "SELECT username FROM players";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("username").contains(name)) {
                    return true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String createPlayer(Player newPlayer) {
        String query = "INSERT INTO players(username, password) VALUES(?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newPlayer.getUsername());
            stmt.setString(2, newPlayer.getPassword());

            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "User created.\n";
    }

    public void deletePlayer(String name) {
        String query = "DELETE FROM players WHERE username = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);

            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        String query = "SELECT username FROM players ORDER BY username";

        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                String userName = resultSet.getString("username");

                players.add(new Player(userName));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return players;
    }
}
