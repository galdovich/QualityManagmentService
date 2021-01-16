package com.galdovich.qaulity.controller.command;

import com.galdovich.qaulity.controller.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command which define functionality of commands.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface ActionCommand {

    /**
     * The method is designed to process a {@code HttpServletRequest} from a controller.
     * The result of processing is an object of the {@code Router} class.
     * During processing, modification of the request object is possible.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);
}
