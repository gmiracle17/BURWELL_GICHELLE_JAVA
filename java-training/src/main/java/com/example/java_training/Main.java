package com.example.java_training;

import java.util.Scanner;

public class Main {

    /* Blackjack Activity 
     * 
     * Shorter Answer to Activity
     * 
     * a = a > 21 ? 0 : a;
     * b = b > 21 ? 0 : b;
     * return a > b ? a : b;
     * 
     * */
    private static int blackjack(int a, int b) {
    	if (a > 21 && b > 21) { return 0; } 
    	else if (a > 21 || a < b) { return b; } 
    	return a;
    }

    /* Switch Assignment */
    enum daysOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

    // Normal switch
    private static void normalSwitchDayOfWeek(int num) {
        switch (num) {
            case 1:
                System.out.println(daysOfWeek.Monday);
                break;
            case 2:
                System.out.println(daysOfWeek.Tuesday);
                break;
            case 3:
                System.out.println(daysOfWeek.Wednesday);
                break;
            case 4:
                System.out.println(daysOfWeek.Thursday);
                break;
            case 5:
                System.out.println(daysOfWeek.Friday);
                break;
            case 6:
                System.out.println(daysOfWeek.Saturday);
                break;
            case 7:
                System.out.println(daysOfWeek.Sunday);
                break;
            default:
                System.out.println("Invalid day number");
        }
    }

    // Shorthand switch
    private static void shorthandSwitchDayOfWeek(int num) {
        switch (num) {
            case 1 -> System.out.println(daysOfWeek.Monday);
            case 2 -> System.out.println(daysOfWeek.Tuesday);
            case 3 -> System.out.println(daysOfWeek.Wednesday);
            case 4 -> System.out.println(daysOfWeek.Thursday);
            case 5 -> System.out.println(daysOfWeek.Friday);
            case 6 -> System.out.println(daysOfWeek.Saturday);
            case 7 -> System.out.println(daysOfWeek.Sunday);
            default -> System.out.println("Invalid day number");
        }
    }

    /* Loop Assignment */

    // For loop
    private static void forLoopPyramid(int input) {
        for (int row = 1; row <= input; row++) {
            for (int i = 1; i <= row; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    // While loop
    private static void whileLoopPyramid(int input) {
        int row = 1;

        while (row <= input) {
            int i = 1;

            while (i <= row) {
                System.out.print(i + " ");
                i++;
            }

            System.out.println();
            row++;
        }
    }

    // Do-while loop
    private static void doWhileLoopPyramid(int input) {
        int row = 1;

        do {
            int i = 1;

            do {
                System.out.print(i + " ");
                i++;
            } while (i <= row);

            System.out.println();
            row++;
        } while (row <= input);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        /* Blackjack Test Cases */
        System.out.println("----- Blackjack Activity Results -----\n");

        System.out.println("a=21 and b=21: " + blackjack(21, 21));
        System.out.println("a=19 and b=21: " + blackjack(19, 21));
        System.out.println("a=21 and b=20: " + blackjack(21, 20));
        System.out.println("a=22 and b=23: " + blackjack(22, 23));
        System.out.println("a=18 and b=20: " + blackjack(18, 20));
        System.out.println("a=20 and b=18: " + blackjack(20, 18));

        System.out.println();

        /* Switch Assignment */
        System.out.println("----- Switch Assignment: Days of the Week -----\n");

        System.out.print("Enter a number (1-7): ");
        int num = scanner.nextInt();

        System.out.println("\nNormal Switch:");
        normalSwitchDayOfWeek(num);

        System.out.println("\nShorthand Switch:");
        shorthandSwitchDayOfWeek(num);

        System.out.println();

        /* Loops Assignment */
        System.out.println("----- Loops Assignment -----\n");

        System.out.print("Enter a number (1-20): ");
        int input = scanner.nextInt();

        while (input < 1 || input > 20) {
            System.out.println(input + " is not a valid input. Please try again!\n");
            System.out.print("Enter a number (1-20): ");
            input = scanner.nextInt();
        }

        System.out.println("\nFor Loop:");
        forLoopPyramid(input);

        System.out.println("\nWhile Loop:");
        whileLoopPyramid(input);

        System.out.println("\nDo-While Loop:");
        doWhileLoopPyramid(input);

        scanner.close();
    }
}