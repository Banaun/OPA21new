package opa21thefarm;

import java.util.ArrayList;
import java.util.Random;

public class Animal {

    Random rand = new Random();
    private String name;
    private int value;

    public Animal(String name) {

        this.name = name;
        value = rand.nextInt(20) + 20;
    }

    public String getName() {

        return name;
    }

    public int getValue() {

        return value;
    }

    public void decreaseValue() {

        this.value *= 0.8;
    }
}
