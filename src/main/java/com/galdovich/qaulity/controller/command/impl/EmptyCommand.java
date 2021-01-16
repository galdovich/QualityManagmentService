package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.util.PageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Empty command is called if it was not possible to determine the command
 * received in the request. User is forwarding to the error page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class EmptyCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageManager.ERROR.getPath());
    }
}
