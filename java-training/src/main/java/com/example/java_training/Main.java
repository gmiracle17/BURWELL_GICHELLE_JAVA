package com.example.java_training;

import java.util.Scanner;

public class Main {

	/* Blackjack Activity */
    private static int blackjack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        }
        else if (a > 21 || a < b) {
            return b;
        }
        return a;
    }
    
    /* Switch Assignment */
    enum daysOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
    
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
    	
    	/* Switch Case Assignment */
        System.out.println("----- Switch Assignment: Days of the Week -----\n");
    	System.out.print("Enter a number (1-7): ");
        int num = scanner.nextInt();
        
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
        System.out.println();
    	
    	/* Loops Assignment: For, While, Do-while */
        System.out.println("----- Loops Assignment: For, While, Do-while -----\n");
    	System.out.print("Enter a number (1-20): ");
    	int input = scanner.nextInt();
    	
    	while (input < 1 || input > 20) {
    		System.out.println(input + " is not a valid input. Please try again! \n");
    		System.out.print("Enter a number (1-20): ");
    		
    		input = scanner.nextInt();
    	}
    	
    	scanner.close();
    	
    	for (int row = 1; row <= input; row++) {
    		int end = row;
    		for (int i = 1; i <= row; i++) {
    			System.out.print(i + " ");
    		}
    		System.out.println();
    	}
    }
}