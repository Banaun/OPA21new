package opa21intro;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExcersiceArrayList {

    static ArrayList<String> colors = new ArrayList<>();

    public static void main(String[] args) {

        //miniExcerciseOne();

        //miniExcerciseTwo();

        miniExcerciseThree();
    }

    public static void miniExcerciseOne() {

        //Skapa en ArrayLista av namn.
        //Lägg till 5 namn i arraylistan, en åt gången, sist i listan.
        //Ta bort det sista namnet.

        ArrayList<String> names = new ArrayList<>();

        names.add("Emil");
        names.add("Jens");
        names.add("Alex");
        names.add("Dan");
        names.add("Jakob");

        var i = names.size();

        System.out.println(names);

        names.remove(i -1);

        System.out.println(names);
    }

    public static void miniExcerciseTwo() {

        //Använd samma ArrayLista som övning 1.
        //Skriv en if-sats som tar bort det namn som matchar från listan.

        ArrayList<String> names = new ArrayList<>();

        names.add("Emil");
        names.add("Jens");
        names.add("Alex");
        names.add("Dan");
        names.add("Jakob");

        System.out.println(names);

        if (names.indexOf("Alex") != -1) {

            names.remove("Alex");
        }

        System.out.println(names);
    }

    public static void miniExcerciseThree() {

        //Behåll det namnet du tagit bort i en variabel.
        //Sätt tillbaks namnet först i listan.

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> removedItems = new ArrayList<>();

        names.add("Emil");
        names.add("Jens");
        names.add("Alex");
        names.add("Dan");
        names.add("Jakob");

        System.out.println(names);

        if (names.indexOf("Alex") != -1) {

            removedItems.add(names.get(names.indexOf("Alex")));
            names.remove("Alex");
        }

        System.out.println(names);

        names.add(0, removedItems.get(0));

        System.out.println(names);
    }
}
