package org.taylorsoft.taylorsoft.exception;

/**
 * Exception levée lorsqu'une ressource dupliquée est détectée
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s avec %s : '%s' existe déjà", resourceName, fieldName, fieldValue));
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

