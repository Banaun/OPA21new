package opa21backpackexcercise;

import java.util.Scanner;

public class Menu {

    public Menu() {

    }

    void showMenu() {

        System.out.println("MENU");
        System.out.println("1: Add todo item");
        System.out.println("2: Check off item on todo list by number");
        System.out.println("3: Check off item on todo list by name");
        System.out.println("4: Remove item from todo list by number");
        System.out.println("5: Remove item from todo list by name");
        System.out.println("0: Exit");
        System.out.print("Choose an option by entering the corresponding number: ");

        letUserInput();
    }

    void letUserInput() {

        Scanner myText = new Scanner(System.in);
        Scanner myInt = new Scanner(System.in);
        String textInput;
        String option;
        String numInput;

        option = myInt.next();

        switch (option) {

            case "1":

        }

    }
}
