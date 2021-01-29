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
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qaulity.util.ParameterKey.*;

/**
 * The class represents command to edit {@code User} profile.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class EditProfileCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(EditProfileCommand.class);
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();

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
            User user = USER_SERVICE.findById(id).get();
            if (!isSameData(editData, user)) {
                if (USER_SERVICE.update(editData, id)) {
                    request.setAttribute(AttributeKey.PROFILE_VALID_MESSAGE, true);
                } else {
                    request.setAttribute(AttributeKey.USER_EDIT_MAP, editData);
                }
                User editUser = USER_SERVICE.findById(id).get();
                request.setAttribute(AttributeKey.USER_LOGIN, editUser.getLogin());
                request.setAttribute(AttributeKey.USER_NAME, editUser.getName());
                request.setAttribute(AttributeKey.USER_EMAIL, editUser.getEmail());
                request.setAttribute(AttributeKey.USER_DEPARTMENT, editUser.getDepartment().getName());
            } else {
                request.setAttribute(AttributeKey.PROFILE_INVALID_MESSAGE, true);
            }
            router = new Router(PageManager.PROFILE.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while searching for a user", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }

    /**
     * Check for identical values.
     *
     * @param editData the map with new edit user's values
     * @param user     parameter with the old user's values
     * @return true if {@code Map<String, String>} consists the same values as the {@code User}
     */
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
