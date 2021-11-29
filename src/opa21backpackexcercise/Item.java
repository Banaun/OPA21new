package opa21backpackexcercise;

public class Item {

    private float weight;
    private String name;
    private int priority;

    public Item(float weight, String name, int priority) {

        this.weight = weight;
        this.name = name;
        this.priority = priority;
    }

    public float getWeight() {

        return weight;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getPriority() {

        return priority;
    }

    @Override
    public String toString() {

        return "Item name: " + name + ", Item weight: " + weight +
                ", Item priority:" + priority;
    }
}
