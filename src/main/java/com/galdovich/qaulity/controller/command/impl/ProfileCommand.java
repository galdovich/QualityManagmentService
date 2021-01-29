package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.User;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The class represents command of creating {@code User} page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ProfileCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ProfileCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        String id = request.getParameter(AttributeKey.USER_ID);
        HttpSession session = request.getSession();
        Router router;
        try {
            User user = (id != null) ? USER_SERVICE.findById(Integer.valueOf(id)).get() :
                    USER_SERVICE.findById((Integer) session.getAttribute(AttributeKey.USER_ID)).get();
            session.setAttribute(AttributeKey.USER_NAME, user.getName());
            session.setAttribute(AttributeKey.USER_LOGIN, user.getLogin());
            session.setAttribute(AttributeKey.USER_EMAIL, user.getEmail());
            session.setAttribute(AttributeKey.USER_DEPARTMENT, user.getDepartment().getName());
            session.setAttribute(AttributeKey.USER_ROLE, user.getRole().name());
            router = new Router(PageManager.PROFILE.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while searching for user", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
