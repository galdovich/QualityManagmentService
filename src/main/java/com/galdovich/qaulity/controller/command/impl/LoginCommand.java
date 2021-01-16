package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.User;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import com.galdovich.qaulity.util.ParameterKey;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.galdovich.qaulity.entity.Role.ADMIN;

/**
 * The class represents login command.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class LoginCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(LoginCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(ParameterKey.LOGIN);
        String password = request.getParameter(ParameterKey.PASSWORD);
        HttpSession session = request.getSession();
        Router router;
        try {
            if (USER_SERVICE.logIn(login, password)) {
                User user = USER_SERVICE.findByLogin(login);
                session.setAttribute(AttributeKey.USER_ID, user.getId());
                session.setAttribute(AttributeKey.USER_NAME, user.getName());
                session.setAttribute(AttributeKey.USER_ROLE, user.getRole().name());
                session.setAttribute(AttributeKey.IS_AUTHORIZED, true);
                return user.getRole() == ADMIN ? new Router(PageManager.ADMIN_PATH.getPath()) :
                        new Router(PageManager.CLIENT_MENU.getPath());
            } else {
                request.setAttribute(AttributeKey.LOGIN_ERROR_TAG, true);
                router = new Router(PageManager.LOGIN.getPath());
            }
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Verification error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
