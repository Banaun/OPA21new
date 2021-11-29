package opa21login;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game {
    private Database db;
    private Scanner userInput;
    private String userName;

    public Game() {
        this.db = new Database();
        this.userInput = new Scanner(System.in);

        this.runGame();
    }

    public void runGame() {
        showMenu();
        showSubMenu();
    }

    public void showMenu() {
        System.out.println("MENU");
        System.out.println("1. Login");
        System.out.println("2. Create new player");
        System.out.println("3. Show existing players");
        System.out.println("4. EXIT");
        System.out.print("Choose an option by entering the corresponding number: ");

        String menuOption = userInput.nextLine();
        switch (menuOption) {
            case "1":
                //LOGIN
                showLogin();
                break;
            case "2":
                //Create new player
                showCreatePlayer();
                break;
            case "3":
                //Show existing players
                showPlayers();
                break;
            case "4":
                //EXIT
                System.out.print("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input");
                System.exit(0);
                break;
        }
    }

    public void showSubMenu() {
        System.out.println("MENU");
        System.out.println("1. Flip a coin");
        System.out.println("2. Play Rock, Paper, Scissors");
        System.out.println("3. Delete player");
        System.out.println("4. EXIT");
        System.out.print("Choose an option by entering the corresponding number: ");

        String subMenuOption = userInput.nextLine();
        switch (subMenuOption) {
            case "1":
                //Flip a coin
                coinFlip();
                break;
            case "2":
                //Rock, paper, scissors
                rockPaperScissors();
                break;
            case "3":
                showDeletePlayer();
                break;
            case "4":
                //EXIT
                System.out.print("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input\n");
                showSubMenu();
        }
    }

    public void showLogin() {

        System.out.print("Please enter your username: ");
        userName = userInput.nextLine();

        Player chosenPlayer = db.getPlayerByName(userName);
        if (chosenPlayer != null) {
            System.out.print("Please enter your password: ");
            String password = userInput.nextLine();

            if (db.checkPassword(userName, password)) {
                System.out.println("Welcome " + userName + "!\n");
            }
            else {
                System.out.println("Invalid password.\n");
                System.exit(0);
            }
        }
        else {
            System.out.println("Invalid username.\n");
            System.exit(0);
        }
    }

    public void showCreatePlayer() {
        String chosenUsername;
        String chosenPassword;

        System.out.print("Enter username: ");
        chosenUsername = userInput.nextLine();

        if (!db.checkDuplicateName(chosenUsername)) {
            System.out.print("Enter password: ");
            chosenPassword = userInput.nextLine();

            Player newPlayer = new Player(chosenUsername, chosenPassword);
            System.out.println(db.createPlayer(newPlayer));
        }
        else {
            System.out.println("Username is already taken!");
            showCreatePlayer();
        }
        runGame();
    }

    public void showDeletePlayer() {
        System.out.println("\nAre you sure you want to delete " + userName + "?");
        System.out.println("1. YES");
        System.out.println("2. NO");
        System.out.print("Choose an option by entering the corresponding number: ");

        String yesOrNo = userInput.nextLine();
        switch (yesOrNo) {
            case "1":
                db.deletePlayer(userName);
                System.out.println(userName + " was deleted from the database.\n");
                showMenu();
                break;
            case "2":
                System.out.println("No action was taken.\n");
                showSubMenu();
            default:
                System.out.println("Incorrect input\n");
                showSubMenu();
        }
    }

    public void showPlayers() {
        ArrayList<Player> players = db.getPlayers();

        System.out.println("\nPlayers in the database:");

        for (Player player : players) {
            System.out.println(player);
        }
        System.out.println("");
        runGame();
    }

    public void coinFlip() {
        Random rand = new Random();
        int randomValue;
        int timeToWait = 3;

        System.out.print("\nFlipping coin");
        try {
            for (int i=0; i<timeToWait; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            Thread.sleep(1000);
        }
        catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        randomValue = rand.nextInt(2) + 1;
        if (randomValue == 1) {
            System.out.println("\nHEADS!\n");
        }
        else if (randomValue == 2) {
            System.out.println("\nTAILS!\n");
        }
        else {
            System.out.println("\nThe coin broke mid air...\n");
        }

        try {
            Thread.sleep(2000);
            showSubMenu();
        }
        catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    public void rockPaperScissors() {
        Random rand = new Random();
        String randomWeapon = "";
        int randomWeaponNum = 0;
        int chosenWeaponNum = 0;

        System.out.println("\n1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        System.out.print("Choose a weapon by entering the corresponding number: ");

        randomWeaponNum = rand.nextInt(3);
        switch (randomWeaponNum) {
            case 0:
                randomWeapon = "Rock";
                break;
            case 1:
                randomWeapon = "Paper";
                break;
            case 2:
                randomWeapon = "Scissors";
                break;
        }

        String chosenWeapon = userInput.nextLine();
        switch (chosenWeapon) {
            case "1":
                //Rock
                System.out.println("You have chosen Rock.");
                chosenWeaponNum = 0;
                break;
            case "2":
                //Paper
                System.out.println("You have chosen Paper.");
                chosenWeaponNum = 1;
                break;
            case "3":
                //Scissors
                System.out.println("You have chosen Scissors.");
                chosenWeaponNum = 2;
                break;
            default:
                System.out.println("Incorrect input\n");
                rockPaperScissors();
        }
        System.out.println("Your opponent has chosen " + randomWeapon + ".");

        if (chosenWeaponNum == randomWeaponNum) {
            System.out.println("It's a tie!\n");
        }
        else if (chosenWeaponNum == 0) {
            if (randomWeaponNum == 1) {
                System.out.println("Paper beats Rock... You lose!\n");
            }
            else {
                System.out.println("Rock beats Scissors... You win!\n");
            }
        }
        else if (chosenWeaponNum == 1) {
            if (randomWeaponNum == 0) {
                System.out.println("Paper beats Rock... You win!\n");
            }
            else {
                System.out.println("Scissors beats Paper... You lose!\n");
            }
        }
        else if (chosenWeaponNum == 2) {
            if (randomWeaponNum == 0) {
                System.out.println("Rock beats Scissors... You lose!\n");
            }
            else {
                System.out.println("Scissors beats Paper... You win!\n");
            }
        }
        showSubMenu();
    }
}
