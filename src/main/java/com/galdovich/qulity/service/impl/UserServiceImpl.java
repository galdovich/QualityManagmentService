package com.galdovich.qulity.service.impl;

import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.dao.DAOException;
import com.galdovich.qulity.dao.UserDAO;
import com.galdovich.qulity.dao.factory.DAOFactory;
import com.galdovich.qulity.entity.Department;
import com.galdovich.qulity.entity.Role;
import com.galdovich.qulity.entity.User;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.UserService;
import com.galdovich.qulity.service.validator.UserValidator;
import com.galdovich.qulity.util.CustomEncoding;

import java.util.List;
import java.util.Map;

import static com.galdovich.qulity.util.ParameterKey.*;

/**
 * The class represents user service implementation.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private static UserDAO USER_DAO = DAOFactory.getInstance().getUserDao();
    private static final String DEFAULT_ROLE = "CLIENT";

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> list;
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();
        try {
            list = userDAO.findAll();
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
        return list;
    }

    @Override
    public User findById(int id) throws ServiceException {
        User user = null;
        try {
            user = USER_DAO.findById(id);
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding user", exc);
        }
        return user;
    }

    @Override
    public boolean logIn(String login, String password) throws ServiceException {
        boolean result = false;
        if (UserValidator.isLoginValid(login)) {
            try {
                if ((USER_DAO.findByLogin(login) != null)) {
                    String encPass = CustomEncoding.encodePassword(password);
                    result = encPass.equals(USER_DAO.findPasswordByLogin(login));
                }
            } catch (DAOException exc) {
                throw new ServiceException("DAOException in  ServiceMethod", exc);
            }
        }
        return result;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        User user = null;
        try {
            if (UserValidator.isLoginValid(login)) {
                user = USER_DAO.findByLogin(login);
            }
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding user", exc);
        }
        return user;
    }

    @Override
    public boolean update(Map<String, String> editData, int id) throws ServiceException {
        boolean result = false;
        if (UserValidator.validateEditDataMap(editData)) {
            String name = editData.get(NAME);
            String email = editData.get(EMAIL);
            String department = editData.get(DEPARTMENT);
            try {
                USER_DAO.update(id, name, email, department);
                result = true;
            } catch (DAOException exc) {
                throw new ServiceException("Error while updating data", exc);
            }
        }
        return result;
    }

    @Override
    public boolean add(Map<String, String> requestData) throws ServiceException {
        boolean result = false;
        try {
            if (USER_DAO.findByLogin(requestData.get(LOGIN)) == null) {
                if (UserValidator.validateRequestData(requestData)) {
                    String name = requestData.get(NAME);
                    String login = requestData.get(LOGIN);
                    String password = CustomEncoding.encodePassword(requestData.get(PASSWORD));
                    String email = requestData.get(EMAIL);
                    String department = requestData.get(DEPARTMENT);
                    if (result = USER_DAO.add(createUser(name, login, email, department, DEFAULT_ROLE), password)) {
                        requestData.put(AttributeKey.USER_MESSAGE, "Added!");
                    }
                }
            } else {
                requestData.put(LOGIN, "");
                requestData.put(AttributeKey.USER_SAME_LOGIN_MESSAGE, "Already registered!");
                result = false;
            }
        } catch (DAOException exc) {
            throw new ServiceException("Error while adding user into database", exc);
        }
        return result;
    }

    private User createUser(String name, String login, String email, String department, String role) {
        return new User(name, login, email, Department.getDepartment(department), Role.valueOf(role));
    }
}
