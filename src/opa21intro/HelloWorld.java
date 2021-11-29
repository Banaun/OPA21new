package opa21intro;

import java.util.Scanner;

public class HelloWorld {

    public static void main(String[] args){

        String name;
        int i;

        System.out.println("Hello World!");

        Scanner scanConsole = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        name = scanConsole.nextLine();
        System.out.println("Hello " + name + "!");

        i = 0;
        while (i < 99) {
            System.out.println("Hello World!");
            i++;
        }

        //for (i = 0; i < 99; i++) {
        //    System.out.println("Hello World!");
        //}

    }
}
