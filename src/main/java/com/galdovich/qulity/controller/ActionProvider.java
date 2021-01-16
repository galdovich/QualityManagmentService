package com.galdovich.qulity.controller;

import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.impl.CommandType;
import com.galdovich.qulity.controller.command.impl.EmptyCommand;
import com.galdovich.qulity.util.MessageManager;
import com.galdovich.qulity.util.ParameterKey;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents action provider which define command from request.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ActionProvider {
    private static final ActionProvider instance = new ActionProvider();

    private ActionProvider() {
    }

    /**
     * Gets instance of command provider.
     *
     * @return the instance
     */
    public static ActionProvider getInstance() {
        return instance;
    }

    /**
     * Define command.
     *
     * @param request the request
     * @return the action command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand command = new EmptyCommand();
        String action = request.getParameter(ParameterKey.COMMAND);
        if (action == null || action.isEmpty()) {
            return command;
        }
        try {
            command = CommandType.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException exc) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return command;
    }
}
