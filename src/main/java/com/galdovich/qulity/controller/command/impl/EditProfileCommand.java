package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.User;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.UserService;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.MessageManager;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qulity.util.ParameterKey.*;

public class EditProfileCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(EditProfileCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();
    private static final String VALID_MESSAGE = "profile.valid.message";
    private static final String INVALID_MESSAGE = "profile.invalid.message";

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> editData = new HashMap<>();
        editData.put(NAME, request.getParameter(NAME));
        editData.put(EMAIL, request.getParameter(EMAIL));
        editData.put(DEPARTMENT, request.getParameter(DEPARTMENT));
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(AttributeKey.USER_ID);
        Router router;
        try {
            User user = USER_SERVICE.findById(id);
            if (!isSameData(editData, user)) {
                if (USER_SERVICE.update(editData, id)) {
                    request.setAttribute(AttributeKey.PROFILE_VALID_MESSAGE, MessageManager.getProperty(VALID_MESSAGE));
                } else {
                    request.setAttribute(AttributeKey.USER_EDIT_MAP, editData);
                }
                User editUser = USER_SERVICE.findById(id);
                request.setAttribute(AttributeKey.USER_LOGIN, editUser.getLogin());
                request.setAttribute(AttributeKey.USER_NAME, editUser.getName());
                request.setAttribute(AttributeKey.USER_EMAIL, editUser.getEmail());
                request.setAttribute(AttributeKey.USER_DEPARTMENT, editUser.getDepartment().getName());
            } else {
                request.setAttribute(AttributeKey.PROFILE_INVALID_MESSAGE, MessageManager.getProperty(INVALID_MESSAGE));
            }
            router = new Router(PageManager.PROFILE.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while finding user", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }

    private boolean isSameData(Map<String, String> editData, User user) {
        boolean result = true;
        if (!(editData.get(NAME).equals(user.getName())
                && editData.get(EMAIL).equals(user.getEmail())
                && editData.get(DEPARTMENT).equals(user.getDepartment().getName()))) {
            result = false;
        }
        return result;
    }
}
