package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.User;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.UserService;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ProfileCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        String id = request.getParameter(AttributeKey.USER_ID);
        HttpSession session = request.getSession();
        Router router;
        try {
            User user = (id != null) ? USER_SERVICE.findById(Integer.valueOf(id)) :
                    USER_SERVICE.findById((Integer) session.getAttribute(AttributeKey.USER_ID));
            session.setAttribute(AttributeKey.USER_NAME, user.getName());
            session.setAttribute(AttributeKey.USER_LOGIN, user.getLogin());
            session.setAttribute(AttributeKey.USER_EMAIL, user.getEmail());
            session.setAttribute(AttributeKey.USER_DEPARTMENT, user.getDepartment().getName());
            session.setAttribute(AttributeKey.USER_ROLE, user.getRole().name());
            router = new Router(PageManager.PROFILE.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Finding user error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
