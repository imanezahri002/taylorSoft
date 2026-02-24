package org.taylorsoft.taylorsoft.exception;

/**
 * Exception levée lors d'une opération invalide
 */
public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(String message) {
        super(message);
    }

    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

