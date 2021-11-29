package com.company;

import express.Express;

import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Express app = new Express();
        Database db = new Database();

        app.get("/rest/users", (req, res) -> {
            List<User> users = db.getUsers();

            res.json(users);
        });

        app.get("/rest/users/:username", (req, res) -> {
            String username = req.params("username");

            User user = db.getUserByUsername(username);

            res.json(user);
        });

        app.post("/rest/users/login", (req, res) -> {
            User user = req.body(User.class);

            if (db.validateUser(user) == true) {
                res.send(true);
            }
            else {
                res.send(false);
            }
        });

        app.post("/rest/users", (req, res) -> {
            User user = req.body(User.class);

            db.createUser(user);

            res.send("post OK");
        });

        app.put("/rest/users", (req, res) -> {
            User user = req.body(User.class);

            db.updateUserPassword(user);

            res.send("update OK");
        });

        app.delete("/rest/users", (req, res) -> {
            User user = req.body(User.class);

            db.deleteUser(user);

            res.send("delete OK");
        });

        app.useStatic(Paths.get("src/www"));

        app.listen(3000);
        System.out.println("Server started on port 3000.");
    }
}
