package com.keenant.flow.exception;

/**
 * Thrown when an assumption was made that should not have been made.
 */
public class IllegalAssumptionException extends RuntimeException {
    public IllegalAssumptionException(String msg) {
        super(msg);
    }
}
