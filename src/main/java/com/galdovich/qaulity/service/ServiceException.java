package com.galdovich.qaulity.service;

/**
 * Sub class class Exceptions. It's checked, used when exceptions
 * occur in the Service application layer
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ServiceException extends Exception {

    /**
     * Instantiates a new Service project exception.
     */
    public ServiceException() {
        super();
    }

    /**
     * Instantiates a new Service project exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Service project exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Service project exception.
     *
     * @param cause the cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
