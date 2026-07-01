package com.example.java_training.day3_payment_processing_system;
import java.math.BigDecimal;

/**
 * PaymentProcessor Class
 * - abstract class that implements PaymentMethod
 * - shows abstraction since objects cannot directly instantiate this class
 * - shows encapsulation through private fields
 */
public abstract sealed class PaymentProcessor implements PaymentMethod permits ElectronicPayment, BankTransfer {
    
    private final BigDecimal amount;
    private boolean processed;
    
    protected PaymentProcessor(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public boolean isProcessed() {
        return processed;
    }
    
    protected void markAsProcessed() {
        processed = true;
    }
    
    public void showPaymentAmount() {
        System.out.println("Payment Amount: PHP " + amount);
    }
    
    @Override
    public void displayPaymentInfo() {
        System.out.println("Amount: PHP " + amount);
    }
    
    public abstract void processPayment();
}