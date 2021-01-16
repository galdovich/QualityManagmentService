package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Processes a request to change the site locale. After changing the locale,
 * a forwarding to the current page occurs.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class LocaleCommand implements ActionCommand {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(AttributeKey.LOCALE);
        session.setAttribute(AttributeKey.LOCALE, locale);

        return new Router((String) session.getAttribute(AttributeKey.CURRENT_PAGE));
    }
}
