package com.example.java_training.day2_payment_processing_system;

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