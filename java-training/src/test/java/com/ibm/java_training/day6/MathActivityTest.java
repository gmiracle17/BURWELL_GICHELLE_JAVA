package com.ibm.java_training.day6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the MathActivity class.
 * These tests verify the correctness of basic arithmetic operations
 * such as addition, subtraction, multiplication, and division.
 */
public class MathActivityTest {

    float greaterPositiveFloat = 5.6f;
    float lesserPositiveFloat = 3.7f;
    float greaterNegativeFloat = -5.6f;
    float lesserNegativeFloat = -3.7f;

    /**
     * Testing add() method.
     */
    @Test
    void exec01() {
        assertEquals(9.3f, MathActivity.add(greaterPositiveFloat, lesserPositiveFloat), 0.001f);
        assertEquals(-9.3f, MathActivity.add(greaterNegativeFloat, lesserNegativeFloat), 0.001f);
        assertEquals(-1.9f, MathActivity.add(greaterNegativeFloat, lesserPositiveFloat), 0.001f);
        assertEquals(1.9f, MathActivity.add(greaterPositiveFloat, lesserNegativeFloat), 0.001f);
    }

    /**
     * Testing subtract() method.
     */
    @Test
    void exec02() {
        assertEquals(1.9f, MathActivity.subtract(greaterPositiveFloat, lesserPositiveFloat), 0.001f);
        assertEquals(-1.9f, MathActivity.subtract(greaterNegativeFloat, lesserNegativeFloat), 0.001f);
        assertEquals(-9.3f, MathActivity.subtract(greaterNegativeFloat, lesserPositiveFloat), 0.001f);
        assertEquals(9.3f, MathActivity.subtract(greaterPositiveFloat, lesserNegativeFloat), 0.001f);
        
        assertEquals(-1.9f, MathActivity.subtract(lesserPositiveFloat, greaterPositiveFloat), 0.001f);
        assertEquals(1.9f, MathActivity.subtract(lesserNegativeFloat, greaterNegativeFloat), 0.001f);
        assertEquals(9.3f, MathActivity.subtract(lesserPositiveFloat, greaterNegativeFloat), 0.001f);
        assertEquals(-9.3f, MathActivity.subtract(lesserNegativeFloat, greaterPositiveFloat), 0.001f);
   }

    /**
     * Testing multiple() method.
     */
    @Test
    void exec03() {
        assertEquals(20.72f, MathActivity.multiply(greaterPositiveFloat, lesserPositiveFloat), 0.001f);
        assertEquals(20.72f, MathActivity.multiply(greaterNegativeFloat, lesserNegativeFloat), 0.001f);
        assertEquals(-20.72f, MathActivity.multiply(greaterNegativeFloat, lesserPositiveFloat), 0.001f);
        assertEquals(-20.72f, MathActivity.multiply(greaterPositiveFloat, lesserNegativeFloat), 0.001f);
    }

    /**
     * Testing divide() method.
     */
    @Test
    void exec04() {
        assertEquals(1.5135f, MathActivity.divide(greaterPositiveFloat, lesserPositiveFloat), 0.001f);
        assertEquals(1.5135f, MathActivity.divide(greaterNegativeFloat, lesserNegativeFloat), 0.001f);
        assertEquals(-1.5135f, MathActivity.divide(greaterNegativeFloat, lesserPositiveFloat), 0.001f);
        assertEquals(-1.5135f, MathActivity.divide(greaterPositiveFloat, lesserNegativeFloat), 0.001f);
        
        assertEquals(0.6607f, MathActivity.divide(lesserPositiveFloat, greaterPositiveFloat), 0.001f);
        assertEquals(0.6607f, MathActivity.divide(lesserNegativeFloat, greaterNegativeFloat), 0.001f);
        assertEquals(-0.6607f, MathActivity.divide(lesserPositiveFloat, greaterNegativeFloat), 0.001f);
        assertEquals(-0.6607f, MathActivity.divide(lesserNegativeFloat, greaterPositiveFloat), 0.001f);
    }
    
    /**
     * Testing add(), subtract(), multiply(), and divide() with 0
     */
    @Test
    void exec05() {
    	assertEquals(5.6f, MathActivity.add(0f, greaterPositiveFloat), 0.001f);
    	assertEquals(-5.6f, MathActivity.add(0f, greaterNegativeFloat), 0.001f);
    	assertEquals(5.6f, MathActivity.add(greaterPositiveFloat, 0f), 0.001f);
    	assertEquals(-5.6f, MathActivity.add(greaterNegativeFloat, 0f), 0.001f);
    	
    	assertEquals(-5.6f, MathActivity.subtract(0f, greaterPositiveFloat), 0.001f);
    	assertEquals(5.6f, MathActivity.subtract(0f, greaterNegativeFloat), 0.001f);
    	assertEquals(5.6f, MathActivity.subtract(greaterPositiveFloat, 0f), 0.001f);
    	assertEquals(-5.6f, MathActivity.subtract(greaterNegativeFloat, 0f), 0.001f);
    	
    	assertEquals(0f, MathActivity.multiply(0f, greaterPositiveFloat), 0.001f);
    	assertEquals(0f, MathActivity.multiply(0f, greaterNegativeFloat), 0.001f);
    	assertEquals(0f, MathActivity.multiply(greaterPositiveFloat, 0f), 0.001f);
    	assertEquals(0f, MathActivity.multiply(greaterNegativeFloat, 0f), 0.001f);
    	
    	assertEquals(0f, MathActivity.multiply(0f, greaterPositiveFloat), 0.001f);
    	assertEquals(0f, MathActivity.multiply(0f, greaterNegativeFloat), 0.001f);
    }
    /**
     * Testing divide() method throws ArithmeticException when dividing by zero.
     */
    @Test
    void exec06() {
        assertThrows(
                ArithmeticException.class,
                () -> MathActivity.divide(greaterPositiveFloat, 0.0f)
        );
    }
}