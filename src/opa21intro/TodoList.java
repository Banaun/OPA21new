package opa21intro;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> doneList = new ArrayList<>();

    void addItem(String item) {

        items.add(item);
    }

    void showList() {

        System.out.println("TODO LIST");

        for (int i = 0; i < items.size(); i++) {

            System.out.println(i + 1 + ": " + items.get(i));
        }
    }

    void showDoneList() {

        System.out.println("DONE LIST");

        if (doneList.size() > 0) {

            for (int i = 0; i < doneList.size(); i++) {

                System.out.println(i + 1 + ": " + doneList.get(i));
            }
        }
        else {
            System.out.println("There's nothing here!");
        }
    }

    int getIndexOf(String item) {

        return items.indexOf(item);
    }

    void addToTopOfList(String item) {

        items.add(0, item);
    }

    String removeFromBottomOfList() {

        String lastItem = items.remove(items.size() -1);

        return "Removed " + "'" + lastItem + "'" + " from the list.";
    }

    String removeFromTopOfList() {

        String firstItem = items.remove(0);

        return "Removed " + "'" + firstItem + "'" + " from the list.";
    }

    String removeFromListByIndex(int index) {

        if (index < 0) {

            return "Nothing was removed because the input was incorrect.";
        }
        else {
            String removed = items.remove(index);

            return "Removed " + "'" + removed + "'" + " from the list.";
        }
    }

    String removeFromListByName(String item) {

        int i = getIndexOf(item);

        return removeFromListByIndex(i);
    }

    void removeFromListAndAddToDone(String item) {

        removeFromListByName(item);
        doneList.add(item);
        System.out.println("Checked off " + "'" + item + "'.");
    }

    void removeFromListAndAddToDoneInt(int num) {

        doneList.add(items.get(num));
        System.out.println("Checked off " + "'" + items.get(num) + "'.");
        items.remove(num);
    }

    void moveBackToTodo(String item) {

        if (doneList.contains(item)) {
            addItem(item);
            doneList.remove(doneList.indexOf(item));
            System.out.println("'" + item + "'" + " was moved back to the todo list.");
        }
        else {
            System.out.println("'" + item + "'" + " does not exist in the done list.");
        }
    }

    void moveToTop(String item) {

        removeFromListByName(item);

        addToTopOfList(item);

        System.out.println("'" + item + "'" + "was moved to the top of the list.\n");
    }

    void moveToBottom(String item) {

        removeFromListByName(item);

        addItem(item);

        System.out.println("'" + item + "'" + "was moved to the bottom of the list.\n");
    }

    void moveDown(String item) {

        int i = getIndexOf(item);
        var swap = items.get(i -1);
        items.set(i -1, item);
        items.set(i, swap);

        System.out.println("'" + item + "'" + "was moved up one step on the list.\n");
    }

    void moveUp(String item) {

        int i = getIndexOf(item);
        var swap = items.get(i +1);
        items.set(i +1, item);
        items.set(i, swap);

        System.out.println("'" + item + "'" + "was moved down one step on the list.\n");
    }

    void showMenu() {

        System.out.println("MENU");
        System.out.println("1: Add todo item");
        System.out.println("2: Check off item on todo list by number");
        System.out.println("3: Check off item on todo list by name");
        System.out.println("4: Remove item from todo list by number");
        System.out.println("5: Remove item from todo list by name");
        System.out.println("6: Change order of item on todo list");
        System.out.println("7: Move an item from the done list back to the todo list");
        System.out.println("8: Show todo list");
        System.out.println("9: Show done list");
        System.out.println("0: Exit");
        System.out.print("Choose an option by entering the corresponding number: ");

        letUserSelect();
    }

    void letUserSelect() {

        Scanner myInt = new Scanner(System.in);
        Scanner myText = new Scanner(System.in);
        int checkOff;
        int removeByInt;
        String inputNum;
        String option;
        String input;
        String inputName;

        option = myInt.next();

        switch (option) {

            case "1":
                //Add todo item
                System.out.print("Enter todo item: ");

                input = myText.next();

                addItem(input);

                System.out.println("'" + input + "'" + " has been added to the todo list.\n");
                break;
            case "2":
                //Check off item on todo list by number
                System.out.println("");
                showList();
                System.out.print("Enter the corresponding number for the item that you want to check off: ");

                checkOff = myInt.nextInt() - 1;
                removeFromListAndAddToDoneInt(checkOff);
                System.out.println("");
                break;
            case "3":
                //Check off item on todo list by name
                System.out.println("");
                showList();
                System.out.print("Enter the name of the item that you want to check off: ");
                inputName = myText.next();

                if (inputName instanceof String) {
                    removeFromListAndAddToDone(inputName);
                    System.out.println("");
                }
                else {

                    System.out.println("Nothing was removed because the input was incorrect.\n");
                }
                break;
            case "4":
                //Remove item from todo list by number
                showList();
                System.out.print("Enter the corresponding number for the item that you want to remove: ");
                removeByInt = myInt.nextInt() - 1;
                System.out.println(removeFromListByIndex(removeByInt));
                break;
            case "5":
                ////Remove item from todo list by name
                showList();
                System.out.print("Enter the name of the item that you want to remove: ");
                inputName = myText.next();
                System.out.println(removeFromListByName(inputName));
                break;
            case "6":
                //Change order of item on todo list
                showList();
                System.out.print("Enter the name of the item that you want to move: ");
                inputName = myText.nextLine();
                System.out.println("1. Move " + "'" + inputName + "'" + " to the top.\n" +
                        "2. Move " + "'" + inputName + "'" + " to the bottom.\n" +
                        "3. Move " + "'" + inputName + "'" + " up one step.\n" +
                        "4. Move " + "'" + inputName + "'" + " down one step.");
                System.out.print("Enter the corresponding number for the move you want to make: ");
                inputNum = myInt.next();

                if (inputNum == "1") {
                    moveToTop(inputName);
                }
                else if (inputNum == "2") {
                    moveToBottom(inputName);
                }
                else if (inputNum == "3") {
                    moveDown(inputName);
                }
                else if (inputNum == "4") {
                    moveUp(inputName);
                }
                else {
                    System.out.println("Nothing was moved because the input was incorrect.");
                }
                break;
            case "7":
                //Move an item from the done list back to the todo list
                showDoneList();

                System.out.print("Enter the name of the item that you want to move back: ");

                inputName = myText.nextLine();

                moveBackToTodo(inputName);

                break;
            case "8":
                //Show todo list
                System.out.println("The current todo list is shown below:\n");

                showList();

                System.out.print("\nPress [ENTER] to continue");
                input = myText.nextLine();
                if (input.equals("")) {
                    break;
                }
            case "9":
                //Show done list
                System.out.println("The done list is shown below:\n");

                showDoneList();

                System.out.print("\nPress [ENTER] to continue");
                input = myText.nextLine();
                if (input.equals("")) {
                    break;
                }
                break;
            case "10":
                //Exit application
                System.out.print("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input.");
                break;
        }

        showMenu();
    }
}
