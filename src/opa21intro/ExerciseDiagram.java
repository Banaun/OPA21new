package opa21intro;

import java.util.Scanner;

public class ExerciseDiagram {

    private Scanner scanConsole = new Scanner(System.in);
    private String userInput;
    private int userInputInt;

    public static void main(String[] args) {

        ExerciseDiagram app = new ExerciseDiagram();

        //Första lösningen
        app.wantSomeFruit();

        //Andra lösningen
        app.wantSomeMoreFruit();
    }

    public void wantSomeFruit() {

        String[] fruitBasket = {"Pear", "Apple", "Orange", "Grapefruit"};
        String[] citrusFruit = {fruitBasket[2], fruitBasket[3]};
        String[] nonCitrusFruit = {fruitBasket[0], fruitBasket[1]};

        System.out.print("Would you like some citrus fruit? [Y/N]: ");

        userInput = scanConsole.nextLine();

        if (userInput.toUpperCase().equals("Y")) {

            System.out.println("See below for the available fruit.");
            System.out.println("1. " + citrusFruit[0] + "\n2. " + citrusFruit[1]);
            System.out.print("Choose a fruit with the corresponding number: ");

            userInputInt = scanConsole.nextInt();

            if (userInputInt == 1) {

                System.out.print("You recieve an orange!\nChew responsibly.");
            }
            else if (userInputInt == 2) {

                System.out.print("You recieve a grapefruit!\nChew responsibly.");
            }
            else {

                System.out.print("Invalid input.");
            }
        }
        else if (userInput.toUpperCase().equals("N")) {

            System.out.println("See below for the available fruit.");
            System.out.println("1. " + nonCitrusFruit[0] + "\n2. " + nonCitrusFruit[1]);
            System.out.print("Choose a fruit with the corresponding number: ");

            userInputInt = scanConsole.nextInt();

            if (userInputInt == 1) {

                System.out.print("You recieve a pear!\nChew responsibly.");
            }
            else if (userInputInt == 2) {

                System.out.print("You recieve an apple!\nChew responsibly.");
            }
            else {

                System.out.print("Invalid input.");
            }
        }
        else {

            System.out.print("Invalid input.");
        }
    }

    public void wantSomeMoreFruit() {

        String[] fruitBasket = {"Pear", "Apple", "Orange", "Grapefruit"};
        int choice = 0;

        System.out.print("Would you like some citrus fruit? [Y/N]: ");

        userInput = scanConsole.nextLine();

        if (userInput.toUpperCase().equals("Y")) {

            choice = 1;
        }
        else if (userInput.toUpperCase().equals("N")) {

            choice = 4;
        }

        switch (choice) {

            case 1:
                System.out.println("See below for the available fruit.");
                System.out.println("1. " + fruitBasket[2] + "\n2. " + fruitBasket[3]);
                System.out.print("Choose a fruit with the corresponding number: ");

                userInputInt = scanConsole.nextInt();

                if (userInputInt == 1) {

                    choice = 2;
                } else if (userInputInt == 2) {

                    choice = 3;
                } else {

                    choice = 7;
                }
            case 2:
                System.out.print("You recieve an orange!\nChew responsibly.");
                break;
            case 3:
                System.out.print("You recieve a grapefruit!\nChew responsibly.");
                break;
            case 4:
                System.out.println("See below for the available fruit.");
                System.out.println("1. " + fruitBasket[0] + "\n2. " + fruitBasket[1]);
                System.out.print("Choose a fruit with the corresponding number: ");

                userInputInt = scanConsole.nextInt();

                if (userInputInt == 1) {

                    choice = 5;
                } else if (userInputInt == 2) {

                    choice = 6;
                }
                else {

                    choice = 7;
                }
            case 5:
                System.out.print("You recieve a pear!\nChew responsibly.");
                break;
            case 6:
                System.out.print("You recieve an apple!\nChew responsibly.");
                break;
            case 7:
                System.out.print("Invalid input.");
                break;
        }

    }
}
