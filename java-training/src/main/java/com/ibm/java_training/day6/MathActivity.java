package com.ibm.java_training.day6;

public class MathActivity {
    public static float add(float a, float b) {
        return a + b;
    }
    public static float subtract(float a, float b) {
        return a - b;
    }
    public static float multiple(float a, float b) {
        return a * b;
    }
    public static float divide(float a, float b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}