package org.taylorsoft.taylorsoft.exception;

/**
 * Exception levée lorsque le stock est insuffisant
 */
public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(String productName, int requested, int available) {
        super(String.format("Stock insuffisant pour '%s'. Demandé: %d, Disponible: %d",
                productName, requested, available));
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause);
    }
}

