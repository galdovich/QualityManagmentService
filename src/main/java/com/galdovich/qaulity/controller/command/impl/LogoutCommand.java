package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.util.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents login command. The session is
 * no longer valid, the user is forwarding to the index page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class LogoutCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return new Router(PageManager.INDEX.getPath());
    }
}
