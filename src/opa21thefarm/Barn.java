package opa21thefarm;

import java.util.ArrayList;

public class Barn {

    private ArrayList<Animal> barnAnimals;

    public Barn() {

        this.barnAnimals = new ArrayList<>();
    }

    public void addAnimalToBarn(Animal animal) {

        barnAnimals.add(animal);
    }

    public void removeAnimalFromBarn(Animal animal) {

        barnAnimals.remove(animal);
    }

    /**
     * A getter method to retrieve a specific animal from list by name
     * @param name to search for
     * @return the animal object if found, otherwise return null
     */
    public Animal getAnimalFromList(String name) {

        for (Animal animal : barnAnimals) {

            if(animal.getName().equalsIgnoreCase(name)) {

                return animal;
            }
        }
        return null;
    }

    public void showAnimalsInBarn() {

        for (Animal animal : barnAnimals) {

            if (animal instanceof Cow) {

                System.out.println(((Cow)animal).toString());
            }
            else if (animal instanceof Pig) {

                System.out.println(((Pig)animal).toString());
            }
        }
    }

    public void showCowsInBarn() {

        for (Animal animal : barnAnimals) {

            if (animal instanceof Cow) {

                System.out.println(((Cow)animal).toString());
            }
        }
    }

    public void showPigsInBarn() {

        for (Animal animal : barnAnimals) {

            if (animal instanceof Pig) {

                System.out.println(((Pig)animal).toString());
            }
        }
    }

    public ArrayList<Animal> getBarnAnimals() {

        return barnAnimals;
    }

    public boolean isCowInList(String name) {

        for (Animal cow : barnAnimals) {

            if (name.equalsIgnoreCase(cow.getName()) && cow instanceof Cow) {

                return true;
            }
            else {

                return false;
            }
        }
        return false;
    }

    public boolean isPigInList(String name) {

        for (Animal pig : barnAnimals) {

            if (name.equalsIgnoreCase(pig.getName()) && pig instanceof Pig) {

                return true;
            }
        }
        return false;
    }

    public boolean checkIfThereArePigs() {

        for (Animal pig : barnAnimals) {

            if (pig instanceof Pig) {

                return true;
            }
        }

        return false;
    }

    public boolean checkIfThereAreCows() {

        for (Animal cow : barnAnimals) {

            if (cow instanceof Cow) {

                return true;
            }
        }

        return false;
    }
}
