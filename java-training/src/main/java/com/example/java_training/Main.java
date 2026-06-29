package com.example.java_training;

public class Main {

    private static int blackjack(int a, int b) {
        if (a > 21 && b > 21) {
            return 0;
        }
        else if (a > 21 || a < b) {
            return b;
        }
        return a;
    }
    public static void main(String[] args) {
        System.out.println(blackjack(21, 21));
        System.out.println(blackjack(19, 21));
        System.out.println(blackjack(21, 20));
        System.out.println(blackjack(22, 23));
        System.out.println(blackjack(18, 20));
        System.out.println(blackjack(20, 18));
    }
}