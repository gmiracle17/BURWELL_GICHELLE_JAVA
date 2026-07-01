package com.example.java_training.day1;

import java.util.*;

public class ZigzagPattern {

	public static void main(String[] args) {
        System.out.print("Enter a number: ");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();

        for (int row = 1; row <= num; row++) {

            int start = (row - 1) * num + 1;
            int end = row * num;

            // Odd row
            if (row % 2 != 0) {
                for (int digit = start; digit <= end; digit++) {
                    System.out.print(digit + "\t");
                }
            }
            // Even row
            else {
                for (int digit = end; digit >= start; digit--) {
                    System.out.print(digit + "\t");
                }
            }

            System.out.println();
        }

        input.close();
    }

}
