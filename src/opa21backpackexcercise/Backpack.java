package opa21backpackexcercise;

import java.util.ArrayList;

public class Backpack {

    private ArrayList<Item> items = new ArrayList<>();
    private final float MAX_WEIGHT = 30.5f;

    public Backpack() {

    }

    public boolean addItem(Item item) {

        return addItemWithPrio(item);

        // check if item fits in the backpack

        // item.getWeight() == 5.3f
        // (8 + 5.3) <= 10.5 == true

//        if( (getCurrentWeight() + item.getWeight()) <= MAX_WEIGHT ) {
//            items.add(item);
//            return true;
//        }
//        else {
//            System.out.println("Item too heavy!");
//            return false;
//        }
    }

    public boolean addItemWithPrio(Item item) {
        if(item.getWeight() > MAX_WEIGHT) {
            System.out.println("Item too heavy!");
            return false;
        }

        boolean itemAdded = false;

        do {
            if( (getCurrentWeight() + item.getWeight()) <= MAX_WEIGHT ) {
                items.add(item);
                return true;
            }
            else {
                boolean didRemoveItem = removeLowestPrioItem(item.getPriority());

                if(!didRemoveItem) {
                    System.out.println("Item didn't have enough priority");
                    return false;
                }
            }
        } while (!itemAdded);

        return false;
    }

    private boolean removeLowestPrioItem(int newItemPrio) {
        int itemToRemoveIndex = 0;

        for (int i = 1; i < items.size(); i++) {
            int itemPrio = items.get(i).getPriority();
            int lowestItemPrio = items.get(itemToRemoveIndex).getPriority();

            if(itemPrio < lowestItemPrio) {
                itemToRemoveIndex = i;
            }
        }

        if(items.get(itemToRemoveIndex).getPriority() < newItemPrio) {
            items.remove(itemToRemoveIndex);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeItem(String name) {
        // loop all items and check if name match

        for (Item item : items) {
            if(item.getName().equalsIgnoreCase(name)) {
                items.remove(item);
                return true; // will end the loop and method
            }
        }

        return false;
    }

    public float getAvailableWeight() {

        return MAX_WEIGHT - getCurrentWeight();
    }

    public void showContent() {
        for (Item item : items) {
            System.out.println(item.toString());
        }
    }

    private float getCurrentWeight() {
        float weight = 0;

        for (Item item : items) {
            weight += item.getWeight();
        }

        return weight;
    }
}
