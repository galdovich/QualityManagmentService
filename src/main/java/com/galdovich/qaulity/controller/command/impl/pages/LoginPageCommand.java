package com.galdovich.qaulity.controller.command.impl.pages;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.util.PageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * The command responsible for forwarding client to the login page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class LoginPageCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PageManager.LOGIN.getPath());
    }
}
