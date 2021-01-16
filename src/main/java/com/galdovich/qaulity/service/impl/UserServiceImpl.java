package com.galdovich.qaulity.service.impl;

import com.galdovich.qaulity.dao.DAOException;
import com.galdovich.qaulity.dao.UserDAO;
import com.galdovich.qaulity.dao.factory.DAOFactory;
import com.galdovich.qaulity.entity.Department;
import com.galdovich.qaulity.entity.Role;
import com.galdovich.qaulity.entity.User;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.validator.UserValidator;
import com.galdovich.qaulity.util.CustomEncoding;

import java.util.List;
import java.util.Map;

import static com.galdovich.qaulity.util.ParameterKey.*;

/**
 * The class represents user service implementation.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private static UserDAO USER_DAO = DAOFactory.getInstance().getUserDao();
    private static final String DEFAULT_ROLE = "WORKER";

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> list;
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDao();
        try {
            list = userDAO.findAll();
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding users", exc);
        }
        return list;
    }

    @Override
    public User findById(int id) throws ServiceException {
        User user;
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
                throw new ServiceException("Error while finding user by login", exc);
            }
        }
        return result;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        User user = null;
        if (UserValidator.isLoginValid(login)) {
            try {
                user = USER_DAO.findByLogin(login);
            } catch (DAOException exc) {
                throw new ServiceException("Error while finding user by login", exc);
            }
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
                result = USER_DAO.update(id, name, email, department);
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
            if (UserValidator.validateRequestData(requestData)) {
                String name = requestData.get(NAME);
                String login = requestData.get(LOGIN);
                String password = CustomEncoding.encodePassword(requestData.get(PASSWORD));
                String email = requestData.get(EMAIL);
                String department = requestData.get(DEPARTMENT);
                result = USER_DAO.add(createUser(name, login, email, department, DEFAULT_ROLE), password);
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
