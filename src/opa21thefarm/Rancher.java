package opa21thefarm;

import java.util.ArrayList;

public class Rancher extends Person {

    private ArrayList<Animal> availableAnimals;

    public Rancher(String name) {

        super(name);

    }

    public void addAvailableAnimals() {

        availableAnimals = new ArrayList<>();

        availableAnimals.add(new Cow("Bessie"));
        availableAnimals.add(new Cow("Betsy"));
        availableAnimals.add(new Cow("Buttercup"));
        availableAnimals.add(new Cow("Maja"));
        availableAnimals.add(new Cow("Stella"));
        availableAnimals.add(new Cow("Nellie"));

        availableAnimals.add(new Pig("Piglet"));
        availableAnimals.add(new Pig("Piggly Wiggly"));
        availableAnimals.add(new Pig("Porkchop"));
        availableAnimals.add(new Pig("Baby Buns"));
    }

    public Animal returnAnimalFromList(String name) {

        /*for (int i = 0; i < availableAnimals.size(); i++) {

            if (availableAnimals.get(i).getName().equalsIgnoreCase(name)) {

                return availableAnimals.get(i);
            }
        }*/

        for (Animal animal : availableAnimals) {

            if(animal.getName().equalsIgnoreCase(name)) {

                return animal;
            }
        }
        return null;
    }

    public void sellAnimal(Animal animal) {

        availableAnimals.remove(animal);
    }

    public void showAvailableAnimals() {

        for (Animal animal : availableAnimals) {

            System.out.println(animal.toString() + " Price: " + animal.getValue());
        }
    }

    public boolean isAnimalInList(String name) {

        for (Animal animal : availableAnimals) {

            if (name.equalsIgnoreCase(animal.getName())) {

                return true;
            }
            else {

                return false;
            }
        }
        return false;
    }

    public ArrayList<Animal> getAvailableAnimals() {

        return availableAnimals;
    }
}
