package com.ibm.java_training.day3;

/**
 * PaymentMethod Interface
 * - shows abstraction by only defining methods
 * - implementing classes decide how the methods work
 */
public interface PaymentMethod {
    Boolean verifyPaymentDetails();
    void processPayment();
    void displayPaymentInfo();
}