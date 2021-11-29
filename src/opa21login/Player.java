package opa21login;

public class Player {

    private String username;
    private String password;

    public Player(String name) {
        this.username = name;
    }

    public Player(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return "Username: " + getUsername() + ".";
    }
}
