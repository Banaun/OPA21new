package opa21dbexcercise;

import java.util.ArrayList;
import java.util.Scanner;

public class DBApp {

    private Database db;
    private int userId;

    public DBApp() {

        this.db = new Database();
        this.runApp();
    }

    public void runApp(){
        // Show menu to the user:
        // 1. Show all users    2. Get user by ID   3. Create user  4. Delete user by ID    5. Quit
        // The menu should loop after each menu item.
        // Unless we choose 5. Quit, then exit loop and end program
        System.out.println("MENU");
        System.out.println("1: Show all users");
        System.out.println("2: Get user by ID");
        System.out.println("3: Create user");
        System.out.println("4: Delete user by ID");
        System.out.println("5: Quit");
        System.out.print("Choose an option by entering the corresponding number: ");

        Scanner userMenuInput = new Scanner(System.in);
        Scanner userSubMenuInput = new Scanner(System.in);
        String option = userMenuInput.nextLine();

        switch (option) {

            case "1":
                //Show all users
                System.out.println("");
                showAllUsers();
                break;
            case "2":
                //Get user by ID
                System.out.print("Enter ID: ");

                try {

                    userId = Integer.parseInt(userSubMenuInput.nextLine());
                }
                catch (NumberFormatException e) {

                    System.out.println("Incorrect input.");
                    break;
                }

                showUserById(userId);
                break;
            case "3":
                //Create user
                int chosenAge;

                System.out.print("Enter the name of the user: ");

                String chosenName = userSubMenuInput.nextLine();

                System.out.print("Enter the age of the user: ");

                try {

                    chosenAge = Integer.parseInt(userSubMenuInput.nextLine());
                }
                catch (NumberFormatException e) {

                    System.out.println("Incorrect input.");
                    break;
                }

                createUserWithInput(chosenName, chosenAge);
                break;
            case "4":
                //Delete user by ID
                System.out.print("Enter ID: ");

                try {

                    userId = Integer.parseInt(userSubMenuInput.nextLine());
                }
                catch (NumberFormatException e) {

                    System.out.println("Incorrect input.");
                    break;
                }

                deleteUserWithInput(userId);
                break;
            case "5":
                //Quit
                System.out.print("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input.");
                break;
        }
        System.out.println("");
        runApp();
    }

    public void showAllUsers() {  // show all users method

        ArrayList<User> users = db.getUsers();

        for (User user : users) {

            System.out.println(user);
        }
    }

    public void showUserById(int id) {  // get user by ID method

        User chosenUserById = db.getUserById(id);

        if (chosenUserById != null) {

            System.out.println("\n" + chosenUserById);
        }
        else {

            System.out.println("No user with ID " + id + ".");
        }
    }

    public void createUserWithInput(String name, int age) {  // create user method

        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

        User userCreatedFromInput = new User(name, age);

        System.out.println("\nUser was created with ID " + db.createUser(userCreatedFromInput));
    }

    public void deleteUserWithInput(int id) {  // delete user by ID method

        if (db.getUserById(id) != null) {

            System.out.println("\n" + db.getUserById(id).getName() + " was deleted from the database.");

            db.deleteUserById(id);
        }
        else {

            System.out.println("No user with ID " + id + ".");
        }
    }
}