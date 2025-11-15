package com.von.curcalc.exception;

public class CalculatorException extends RuntimeException {
    final String message;

    public CalculatorException(String message) {
        super(message);
        this.message = message;
    }
}
