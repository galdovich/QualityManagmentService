package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.util.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PaginationCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int page = Integer.valueOf(request.getParameter(AttributeKey.PRODUCT_PAGE));
        session.setAttribute(AttributeKey.PRODUCT_PAGE, page);
        return new Router(PageManager.ADMIN_MENU.getPath());
    }
}
