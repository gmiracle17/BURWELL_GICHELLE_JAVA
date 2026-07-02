package com.ibm.java_training.day3;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * ElectronicPayment Class
 * - inherits from PaymentProcessor
 * - parent class of CreditCard and PayPal
 */
public abstract sealed class ElectronicPayment extends PaymentProcessor permits CreditCard, PayPal {
    private final String transactionReference;
    
    protected ElectronicPayment(BigDecimal amount) {
        super(amount);
        this.transactionReference = UUID.randomUUID().toString();
    }
    
    public String getTransactionReference() {
        return transactionReference;
    }
}