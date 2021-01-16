package com.galdovich.qaulity.controller;

import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.CommandType;
import com.galdovich.qaulity.controller.command.impl.EmptyCommand;
import com.galdovich.qaulity.util.ParameterKey;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The class represents action provider which define command from request.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ActionProvider {
    private static final Logger logger = Logger.getLogger(ActionProvider.class);
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
            logger.log(Level.ERROR, "Error while define command", exc);
        }
        return command;
    }
}
