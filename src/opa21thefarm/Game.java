package opa21thefarm;

import java.util.Scanner;

public class Game {

    private Rancher rancher;
    private Farmer farmer;
    private Animal chosenAnimal;
    private Cow chosenCow;
    private Pig chosenPig;

    public Game() {

        this.rancher = new Rancher("Larry");
        this.rancher.addAvailableAnimals();

        showMenu();
    }

    void showMenu() {

        System.out.println("MENU");
        System.out.println("1: Add farmer");
        System.out.println("2: Buy animal");
        System.out.println("3: Sell animal");
        System.out.println("4: Milk cow");
        System.out.println("5: Slaughter pig");
        System.out.println("6: View barn animals");
        System.out.println("7: View barn storage");
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
                //Add farmer
                if (farmer == null) {

                    System.out.print("Enter name: ");

                    textInput = myText.nextLine();

                    farmer = new Farmer(textInput);

                    System.out.println("'" + textInput + "'" + " has been added as a farmer.\n");

                    break;
                }
                else {

                    System.out.println("You can only have one farmer at a time!");

                    break;
                }
            case "2":
                //Buy animal
                if (farmer != null) {

                    System.out.println("\nSee the list of available animals below:\n");
                    rancher.showAvailableAnimals();
                    System.out.print("\nEnter the name of the animal you want to buy: ");
                    textInput = myText.nextLine();

                    if (rancher.getAvailableAnimals().contains(rancher.returnAnimalFromList(textInput))) {

                        chosenAnimal = rancher.returnAnimalFromList(textInput);
                    }
                    else {

                        System.out.println("No animal in the list with that name.\n");
                        break;
                    }

                    if (rancher.getAvailableAnimals().contains(chosenAnimal) &&
                            farmer.getMoney() >= chosenAnimal.getValue()) {

                        rancher.sellAnimal(chosenAnimal);
                        farmer.getMyBarn().addAnimalToBarn(chosenAnimal);
                        farmer.setMoney(farmer.getMoney() - chosenAnimal.getValue());
                        chosenAnimal.decreaseValue();

                        System.out.println("You have added '" + chosenAnimal.getName() +
                                "' to " + farmer.getName() + "'s barn.\n");
                    }
                    else if (farmer.getMoney() < chosenAnimal.getValue()) {

                        System.out.println("You don't have enough money for this purchase!");
                    }
                    else {

                        System.out.println("There is no animal with that name.\n");
                        break;
                    }
                }
                else {

                    System.out.println("You need a farmer to buy animals!\n");
                }

                break;
            case "3":
                //Sell animal
                if (farmer != null) {

                    if (farmer.getMyBarn().getBarnAnimals().isEmpty() == false) {

                        System.out.println("\nWhich animal would you like to sell?\n");
                        farmer.getMyBarn().showAnimalsInBarn();
                        System.out.print("\nEnter the name of the animal you want to sell: ");
                        textInput = myText.nextLine();

                        if (farmer.getMyBarn().getAnimalFromList(textInput) != null) {

                            chosenAnimal = farmer.getMyBarn().getAnimalFromList(textInput);
                        }
                        else {

                            System.out.println("No animal named '" + textInput + "'.\n");
                            break;
                        }

                        System.out.println(chosenAnimal.getName() + " has been sold for " + chosenAnimal.getValue() + ".\n");

                        farmer.setMoney(farmer.getMoney() + chosenAnimal.getValue());
                        farmer.getMyBarn().removeAnimalFromBarn(chosenAnimal);
                    }
                    else {

                        System.out.println("There are no animals to sell!\n");
                    }
                }
                else {

                    System.out.println("You need a farmer to sell animals!\n");
                }
                break;
            case "4":
                //Milk cow
                if (farmer == null) {

                    System.out.println("You need a farmer to milk a cow!\n");
                }
                else if (farmer.getMyBarn().getBarnAnimals().isEmpty() == true ||
                            farmer.getMyBarn().checkIfThereAreCows() == false) {

                    System.out.println("There are no cows in the barn!\n");
                }
                else {

                    System.out.println("\nWhich cow would you like to milk? ");

                    for (Animal animal : farmer.getMyBarn().getBarnAnimals()) {

                        if (animal instanceof Cow) {

                            System.out.println(animal.toString());
                        }
                    }
                    System.out.print("\nEnter the name of the cow you want to milk: ");
                    textInput = myText.nextLine();

                    if (farmer.getMyBarn().isCowInList(textInput) == true) {

                        chosenCow = (Cow)farmer.getMyBarn().getAnimalFromList(textInput);
                    }
                    else {

                        System.out.println("No cow in the barn with that name.\n");
                        break;
                    }

                    System.out.println(farmer.getMilkFromCow(chosenCow));
                }
                break;
            case "5":
                //Slaughter pig
                if (farmer == null) {

                    System.out.println("You need a farmer to slaughter a pig!\n");
                }
                else if (farmer.getMyBarn().getBarnAnimals().isEmpty() == true ||
                            farmer.getMyBarn().checkIfThereArePigs() == false) {

                    System.out.println("There are no pigs in the barn!\n");
                }
                else {

                    System.out.println("\nWhich pig would you like to slaughter? ");

                    for (Animal animal : farmer.getMyBarn().getBarnAnimals()) {

                        if (animal instanceof Pig) {

                            System.out.println(animal.toString());
                        }
                    }

                    System.out.print("\nEnter the name of the pig you want to slaughter: ");
                    textInput = myText.nextLine();

                    if (farmer.getMyBarn().isPigInList(textInput) == true) {

                        chosenPig = (Pig)farmer.getMyBarn().getAnimalFromList(textInput);
                    }
                    else {

                        System.out.println("No pig in the barn with that name.\n");
                        break;
                    }

                    System.out.println(farmer.getPorkFromPig(chosenPig));
                }

                //System.out.println("This option is currently unavailable.\n");
                break;
            case "6":
                //View barn animals
                if (farmer == null) {

                    System.out.println("There is no barn with animals because there is no farmer!\n");
                }
                else if (farmer.getMyBarn().getBarnAnimals().isEmpty() == true){

                    System.out.println("There are no animals in the barn!\n");
                }
                else {

                    System.out.println("\nSee the list of animals in the barn below:\n");
                    farmer.getMyBarn().showAnimalsInBarn();
                    System.out.println("");
                }

                break;
            case "7":
                //View barn storage
                if (farmer != null) {

                    System.out.println("\nMoney: " + farmer.getMoney() + ".");
                    System.out.println("Milk: " + farmer.getChurn() + "L.");
                    System.out.println("Pork: " + farmer.getPork() + "Kg.\n");
                }
                else {

                    System.out.println("There is no barn with storage because there is no farmer!\n");
                }

                break;
            case "0":
                //Exit
                System.out.print("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect input.\n");
                break;
        }

        showMenu();
    }
}