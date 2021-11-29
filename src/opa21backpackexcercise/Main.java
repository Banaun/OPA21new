package opa21backpackexcercise;

public class Main {

    public static void main(String[] args) {

        Backpack backpack = new Backpack();

        Item item1 = new Item(3, "Potion", 200);
        Item item2 = new Item(2.2f, "Cloth", 180);
        Item item3 = new Item(1.6f , "Cotton", 150);
        Item item4 = new Item(2.3f, "Dagger", 120);
        Item item5 = new Item(4.7f, "Staff", 130);

        backpack.addItem(item1);
        backpack.addItem(item2);
        backpack.addItem(item3);
        backpack.addItem(item4);
        backpack.addItem(item5);
        //backpack.addItem(new Item(8, "Iron armor", 160));

        // the item in the backpack will also change its name
        // because both variables points at the same object
        //item1.setName("Extreme socks");

        backpack.showContent();

//        backpack.removeItem("Cloth");
//        backpack.showContent();

        System.out.printf("Available weight: %.2f \n", backpack.getAvailableWeight());
    }
}
