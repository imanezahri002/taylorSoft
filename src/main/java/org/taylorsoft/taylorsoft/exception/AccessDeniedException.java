package org.taylorsoft.taylorsoft.exception;

/**
 * Exception levée lorsque l'accès à une ressource est refusé
 */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}

