package com.galdovich.qaulity.entity;

import java.io.Serializable;

/**
 * The enum Priority.
 * <p>
 * Defines the product priority at the firm.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public enum Priority implements Serializable {
    /**
     * High priority level.
     */
    A("A"),
    /**
     * Medium priority level.
     */
    B("B"),
    /**
     * Low priority level.
     */
    C("C");

    private String name;

    Priority(String name) {
        this.name = name;
    }

    /**
     * Gets priority name.
     *
     * @return priority name
     */
    public String getName() {
        return name;
    }
}
