package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.UserService;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qulity.util.ParameterKey.*;

public class RegisterCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(RegisterCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> requestData = new HashMap<>();
        requestData.put(LOGIN, request.getParameter(LOGIN));
        requestData.put(NAME, request.getParameter(NAME));
        requestData.put(PASSWORD, request.getParameter(PASSWORD));
        requestData.put(PASSWORD_AGAIN, request.getParameter(PASSWORD_AGAIN));
        requestData.put(EMAIL, request.getParameter(EMAIL));
        requestData.put(DEPARTMENT, request.getParameter(DEPARTMENT));
        Router router;
        try {
            USER_SERVICE.add(requestData);
            request.setAttribute(AttributeKey.REGISTER_DATA_MAP, requestData);
            router = new Router(PageManager.REGISTER.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "ServiceException", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
