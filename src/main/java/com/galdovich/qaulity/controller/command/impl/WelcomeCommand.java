package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.Role;
import com.galdovich.qaulity.service.CustomerService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents command of creating welcome page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class WelcomeCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(WelcomeCommand.class);
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();
    private static final String DEFAULT_LOCALE = "ru_RU";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Router router;
        if (session.isNew()) {
            session.setAttribute(AttributeKey.LOCALE, DEFAULT_LOCALE);
            session.setAttribute(AttributeKey.USER_ROLE, Role.GUEST.name());
        }
        try {
            request.getSession().setAttribute(AttributeKey.CUSTOMER_LIST, CUSTOMER_SERVICE.findAll());
            request.getSession().setAttribute(AttributeKey.USER_AMOUNT, USER_SERVICE.findAll().size());
            router = new Router(PageManager.MAIN.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while finding customer list and user list", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
