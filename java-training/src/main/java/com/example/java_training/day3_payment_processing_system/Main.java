package com.example.java_training.day3_payment_processing_system;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        PaymentMethod[] basket = {
                // Valid entries
                new CreditCard(new BigDecimal("100500.64"), "1234567890123456"),
                new PayPal(new BigDecimal("100.56"), "gc@gmail.com"),
                new BankTransfer(new BigDecimal("50000.00"), "1234567890"),

                // Invalid entries -> verification will return false, payment not processed
                new CreditCard(new BigDecimal("2500.00"), "1234-5678"),
                new PayPal(new BigDecimal("75.00"), "not-an-email"),
                new BankTransfer(new BigDecimal("1000.00"), "AB12CD34")
        };

        System.out.println("--- PAYMENT PROCESSING SYSTEM ---\n");

        for (PaymentMethod payment : basket) {
            payment.displayPaymentInfo();
            System.out.println("Verification Result: " + payment.verifyPaymentDetails());
            payment.processPayment();
            System.out.println("--------------------------------");
        }

        TransactionReceipt receipt = new TransactionReceipt("TXN-001", new BigDecimal("100500.64"), LocalDateTime.now());
        System.out.println("\n--- TRANSACTION RECEIPT ---");
        System.out.println("Receipt ID: " + receipt.id());
        System.out.println("Receipt Amount: PHP " + receipt.amount());
        System.out.println("Timestamp: " + receipt.timestamp());
    }
}