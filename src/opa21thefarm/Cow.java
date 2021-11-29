package opa21thefarm;

public class Cow extends Animal {

    private final int milkAmount = 10;
    private int totalMilk;

    public Cow(String name) {

        super(name);
        this.totalMilk = 30;
    }

    public int milkCow() {

        if (totalMilk >= milkAmount) {

            totalMilk -= milkAmount;

            return milkAmount;
        }
        else {
            System.out.println("This cow has been milked dry...");
        }
        return 0;
    }

    public int getMilkAmount() {

        return milkAmount;
    }

    public String toString() {

        return "Animal: Cow. Name: " + getName() + ". Value: " + getValue() + ".";
    }
}
