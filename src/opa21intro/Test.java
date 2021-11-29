package opa21intro;

import java.util.Scanner;
import java.lang.Object;

public class Test {

    public static void main(String[] args) {

        //questionOne();
        //questionTwo();
        questionThree();
    }

    public static void questionOne() {

        for (int i = 1; i <= 10; i++) {

            System.out.println(i);
        }
    }

    public static void questionTwo() {

        int sum = 0;

        for (int i = 1; i <= 10; i++) {

            sum += i;
        }
        System.out.println(sum);
    }

    /*public static void questionThree() {

        int userInput;

        Scanner scanConsole = new Scanner(System.in);
        System.out.print("Please enter a positive number: ");
        userInput = scanConsole.nextInt();

        if (userInput < 0) {

            System.out.print("Please enter a POSITIVE number: ");
            userInput = scanConsole.nextInt();
        }

        else {

            for(int i = 1; i <= 10; ++i)
            {
                System.out.printf("%1$d * %2$d = %3$d \n", userInput, i, userInput * i);
            }
        }

    }*/


    public static void questionThree() {

        int userInput;
        boolean active = true;
        Scanner scanConsole = new Scanner(System.in);

        while (active) {

            System.out.print("Please enter a positive number: ");

            if (scanConsole.hasNextInt()) {

                userInput = scanConsole.nextInt();

                if (userInput <= 0) {

                    continue;
                }
                else {

                    for(int i = 1; i <= 10; ++i) {
                        System.out.printf("%1$d * %2$d = %3$d \n", userInput, i, userInput * i);
                    }
                    active = false;
                }
            }
            else {
                System.out.print("Invalid input.");
                break;
            }
        }
    }
}
