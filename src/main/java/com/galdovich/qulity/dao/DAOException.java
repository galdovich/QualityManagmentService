package com.galdovich.qulity.dao;

/**
 * Sub class class Exceptions. It's checked, used when exceptions
 * occur in the DAO application layer
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class DAOException extends Exception {

    /**
     * Instantiates a new Dao project exception.
     */
    public DAOException() {
        super();
    }

    /**
     * Instantiates a new Dao project exception.
     *
     * @param message the message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Dao project exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Dao project exception.
     *
     * @param cause the cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }
}
