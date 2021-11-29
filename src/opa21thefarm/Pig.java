package opa21thefarm;

import java.text.DecimalFormat;

public class Pig extends Animal {

    private int weight;
    private double porkAmount = 0;
    private double porkYield = 0.7;

    public Pig(String name) {

        super(name);
        weight = rand.nextInt(30) + 50;
    }

    public double slaughterPig() {

        porkAmount += weight * porkYield;

        return porkAmount;
    }

    public double getPorkAmount() {

        return porkAmount;
    }

    public int getWeight() {

        return weight;
    }

    public String toString() {

        return "Animal: Pig. Name: " + getName() + ". Value: " + getValue() + ".";
    }
}
