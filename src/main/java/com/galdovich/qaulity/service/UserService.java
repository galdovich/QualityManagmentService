package com.galdovich.qaulity.service;

import com.galdovich.qaulity.entity.User;

import java.util.List;
import java.util.Map;

/**
 * The interface represents user service.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface UserService {

    /**
     * Find list of all users.
     *
     * @return the list of users
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the user
     * @throws ServiceException the service exception
     */
    User findById(int id) throws ServiceException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Checking if a user exists.
     *
     * @param login    the login
     * @param password the password
     * @return true if user exists
     * @throws ServiceException the service exception
     */
    boolean logIn(String login, String password) throws ServiceException;

    /**
     * Update user fields.
     *
     * @param editData map with request data
     * @param id       user id
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean update(Map<String, String> editData, int id) throws ServiceException;

    /**
     * Add new user.
     *
     * @param requestData map with request data
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean add(Map<String, String> requestData) throws ServiceException;
}
