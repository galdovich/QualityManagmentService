package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.util.mail.MailUtil;
import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qaulity.util.ParameterKey.*;

/**
 * The class represents command of adding a new {@code User} to the data base.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class RegisterCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(RegisterCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> requestData = new HashMap<>();
        HttpSession session = request.getSession();
        requestData.put(LOGIN, request.getParameter(LOGIN));
        requestData.put(NAME, request.getParameter(NAME));
        requestData.put(PASSWORD, request.getParameter(PASSWORD));
        requestData.put(PASSWORD_AGAIN, request.getParameter(PASSWORD_AGAIN));
        requestData.put(EMAIL, request.getParameter(EMAIL));
        requestData.put(DEPARTMENT, request.getParameter(DEPARTMENT));
        Router router;
        try {
            if (USER_SERVICE.findByLogin(request.getParameter(LOGIN)) == null) {
                if (USER_SERVICE.add(requestData)) {
                    request.setAttribute(AttributeKey.USER_SUCCESS_ADD, true);
                    MailUtil.getInstance().sendMessage((String) session.getAttribute(AttributeKey.LOCALE),
                            request.getParameter(EMAIL), request.getRequestURL().toString());
                } else {
                    request.setAttribute(AttributeKey.USER_FAIL_ADD, true);
                }
            } else {
                request.setAttribute(AttributeKey.USER_SAME_LOGIN, true);
            }
            request.setAttribute(AttributeKey.REGISTER_DATA_MAP, requestData);
            router = new Router(PageManager.REGISTER.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while adding new User", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
