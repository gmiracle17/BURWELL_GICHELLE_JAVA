package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;

/**
 * PayPal Class
 * - inherits from ElectronicPayment that inherits from PaymentProcessor
 */
public final class PayPal extends ElectronicPayment {
    private String email;
    
    public PayPal(BigDecimal amount, String email) {
        super(amount);
        this.email = email;
    }
    
    @Override
    public Boolean verifyPaymentDetails() {
        if (email.contains("@")) { return true; }
        else { return false; }
    }
    
    // Shows polymorphism as processPayment here has its own process (method is from PaymentMethod)
    @Override
    public void processPayment() {
        if (verifyPaymentDetails()) {
            markAsProcessed();
            System.out.println("Processed PayPal payment for " + getEmail());
        }
        else {
        	System.out.println("You entered an invalid email.");
        }
    }
    
    @Override
    public void displayPaymentInfo() {
        showPaymentAmount();
        System.out.println("PayPal Email: " + getEmail());
    }
    
    public String getEmail() {
        return email;
    }
}