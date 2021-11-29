package opa21intro;

import java.util.Arrays;
import java.util.Scanner;

public class ExtraExcercises {

    public static void main(String[] args) {

        ExtraExcercises extra = new ExtraExcercises();
        //extra.excerciseOne();
        //extra.excerciseTwo();
        //extra.excerciseThree();
        //extra.excerciseFour();
        //extra.excerciseFive();
        //extra.excerciseSix();
        extra.excerciseSeven();
    }

    void excerciseOne() {

        Scanner myInt = new Scanner(System.in);
        int inputNum;

        System.out.print("Enter a number: ");

        inputNum = myInt.nextInt();

        if (inputNum < 0) {
            System.out.println("The number is negative.");
        }
        else {
            System.out.println("The number is positive.");
        }
    }

    void excerciseTwo() {

        Scanner myInt = new Scanner(System.in);
        int inputNum;

        System.out.print("Enter a positive number: ");

        inputNum = myInt.nextInt();

        if (inputNum < 0) {

            System.out.println("Incorrect input.");
        }
        else {

            System.out.println("Number of digits in the number: " + ("" + inputNum).length());
        }
    }

    void excerciseThree() {

        Scanner myString = new Scanner(System.in);
        String inputStr;

        System.out.print("Enter a word: ");

        inputStr = myString.next();

        String lastThreeChars = inputStr.substring(inputStr.length() - 3, inputStr.length());
        System.out.println(lastThreeChars + inputStr + lastThreeChars);
    }

    void excerciseFour() {

        String inputString = "Hello how are you?";

        String[] splittedString = inputString.split(" ");

        System.out.println(splittedString[0]);
    }

    void excerciseFive() {

        Scanner myInt = new Scanner(System.in);
        int inputNum;
        String timeString;

        System.out.print("Enter a number: ");

        inputNum = myInt.nextInt();

        int hours = inputNum / 3600;
        int minutes = (inputNum % 3600) / 60;
        int seconds = inputNum % 60;

        System.out.printf("%1$d:%2$d:%3$d", hours, minutes, seconds);
    }

    void excerciseSix() {

        String input = "the quick brown fox jumps over the lazy dog.";
        String newString;
        String[] splittedString = input.split(" ");

        for (int i = 0; i < splittedString.length; i++) {

            splittedString[i] = splittedString[i].substring(0, 1).toUpperCase() + splittedString[i].substring(1).toLowerCase();
        }

        newString = String.join(" ", splittedString);

        System.out.println(newString);
    }

    void excerciseSeven() {

        Scanner myString = new Scanner(System.in);
        String inputStr;
        String newString;
        String[] splittedString;
        String[] consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","z","x"};

        System.out.print("Enter a word or sentence: ");

        inputStr = myString.nextLine();

        splittedString = inputStr.split("");

        for (int i = 0; i < splittedString.length; i++) {

            for (int j = 0; j < consonants.length; j++) {

                if (splittedString[i].contains(consonants[j])) {

                    splittedString[i] = splittedString[i] + "o" + splittedString[i];
                }
            }
        }
        newString = String.join("", splittedString);

        System.out.println(newString);
    }


}
