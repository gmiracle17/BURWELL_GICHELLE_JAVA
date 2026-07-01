package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;
public record TransactionReceipt(String id, BigDecimal amount) {}