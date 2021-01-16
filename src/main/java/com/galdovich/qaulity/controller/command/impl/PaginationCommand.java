package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.util.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents pagination command.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class PaginationCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int page = Integer.valueOf(request.getParameter(AttributeKey.PRODUCT_PAGE));
        session.setAttribute(AttributeKey.PRODUCT_PAGE, page);

        return new Router(PageManager.ADMIN_MENU.getPath());
    }
}
