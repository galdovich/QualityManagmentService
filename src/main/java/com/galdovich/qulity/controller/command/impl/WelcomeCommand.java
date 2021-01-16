package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.Role;
import com.galdovich.qulity.service.CustomerService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.UserService;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WelcomeCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(WelcomeCommand.class);
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();
    private static final String DEFAULT_LOCALE = "ru_RU";
    private static final String USER_AMOUNT = "userAmount";
    private static final String CUSTOMER_LIST = "customerList";
    private static final String PARAM_LOCALE = "locale";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        if (session.isNew()) {
            session.setAttribute(PARAM_LOCALE, DEFAULT_LOCALE);
            session.setAttribute(AttributeKey.USER_ROLE, Role.GUEST.name());
        }
        try {
            request.getSession().setAttribute(CUSTOMER_LIST, CUSTOMER_SERVICE.findAll());
            request.getSession().setAttribute(USER_AMOUNT, USER_SERVICE.findAll().size());
            router = new Router(PageManager.MAIN.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Finding data error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
