package opa21thefarm;

public class Person {

    private String name;
    private double money;

    public Person(String name) {

        money = 100;
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public double getMoney() {

        return money;
    }

    public void setMoney(double money) {

        this.money = money;
    }
}
