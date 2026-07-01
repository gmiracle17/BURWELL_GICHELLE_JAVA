package com.example.java_training.day2_payment_processing_system;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * TransactionReceipt Record
 * - shows encapsulation because records create immutable fields
 * - used for storing transaction data safely
 */
public record TransactionReceipt(String id, BigDecimal amount, LocalDateTime timestamp) {}