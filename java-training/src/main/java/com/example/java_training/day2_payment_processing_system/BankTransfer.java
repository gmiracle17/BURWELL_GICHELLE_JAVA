package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;

/**
 * BankTransfer Class
 * - inherits from PaymentProcessor
 */
public non-sealed class BankTransfer extends PaymentProcessor {
    private String accountNumber;
    
    public BankTransfer(BigDecimal amount, String accountNumber) {
        super(amount);
        this.accountNumber = accountNumber;
    }
    
    @Override
    public Boolean verifyPaymentDetails() {
        if (accountNumber.matches("\\d{10}")) { return true; }
        else { return false; }
    }
    
    // Shows polymorphism as processPayment here has its own process (method is from PaymentMethod)
    @Override
    public void processPayment() {
        if (verifyPaymentDetails()) {
            markAsProcessed();
            System.out.println("Processed Bank Transfer for Account " + getAccountNumber());
        }
        else {
        	System.out.println("You entered an invalid Bank Account number.");
        }
    }
    
    @Override
    public void displayPaymentInfo() {
        showPaymentAmount();
        System.out.println("Account Number: " + getAccountNumber());
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}