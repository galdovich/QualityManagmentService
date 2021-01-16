package com.galdovich.qulity.controller.command.impl;

import java.util.EnumSet;
import java.util.Set;

import static com.galdovich.qulity.controller.command.impl.CommandType.*;

/**
 * This enum is described to differentiate the user's access levels to the elements
 * of the application, depending on its roles. Today the user has one of the
 * roles: WORKER, ADMIN, ({@code User.Role}). Depending on the role,
 * the web filter {@code ServletSecurityFilter} can pass requests to execute a particular
 * command {@code CommandType} or block access.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public enum CommandRoleAccess {

    /**
     * Available commands for user role WORKER.
     */
    WORKER(EnumSet.of(WELCOME,
            LOGIN,
            LOGIN_PAGE,
            REGISTER,
            REGISTER_PAGE,
            PROFILE,
            LOGOUT,
            UPDATE_ROUTE_MAP,
            CLIENT_PRODUCT,
            CLIENT_PAGE,
            LOCALE,
            PAGINATION)),

    /**
     * Available commands for user role ADMIN.
     */
    ADMIN(EnumSet.of(WELCOME,
            LOGIN,
            LOGIN_PAGE,
            REGISTER,
            REGISTER_PAGE,
            PROFILE,
            EDIT_PROFILE,
            LOGOUT,
            PRODUCT,
            UPDATE_ROUTE_MAP,
            UPDATE_PRODUCT,
            ADD_PRODUCT,
            UPLOAD_PRODUCT,
            ADMIN_MENU,
            CLIENT_PRODUCT,
            CLIENT_PAGE,
            LOCALE,
            PAGINATION)),

    /**
     * Available commands for user role GUEST.
     */
    GUEST(EnumSet.of(WELCOME,
            LOGIN,
            LOGIN_PAGE,
            REGISTER,
            LOCALE,
            REGISTER_PAGE));

    private Set<CommandType> accessCommands;

    CommandRoleAccess(Set<CommandType> accessCommands) {
        this.accessCommands = accessCommands;
    }

    /**
     * Returns the set of available commands according to the corresponding role value
     *
     * @return the set
     */
    public Set<CommandType> getAccessCommands() {
        return this.accessCommands;
    }

}
