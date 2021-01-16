package com.galdovich.qulity.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The enum Role.
 * Defines the user role in the project.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public enum Role implements Serializable {
    /**
     * Represents the rights of bosses and directors at the firm.
     */
    ADMIN,
    /**
     * Represents the rights of workers.
     */
    WORKER,
    /**
     * Represents the rights of guests.
     */
    GUEST;

    /**
     * Gets user Role enum constant.
     *
     * @param index index that corresponds to ordinal number
     * @return role enum constant
     */
    public static Role getUserRole(int index) {
        return Arrays.stream(Role.values()).filter(r -> r.ordinal() == index).findFirst().get();
    }
}