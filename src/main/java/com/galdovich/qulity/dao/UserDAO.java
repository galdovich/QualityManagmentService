package com.galdovich.qulity.dao;

import com.galdovich.qulity.entity.User;

import java.util.List;

/**
 * The interface represents user dao. Defines specific methods which interactions with User entities in database.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface UserDAO {

    /**
     * Find all users.
     *
     * @return the List of users
     * @throws DAOException the dao exception
     */
    List<User> findAll() throws DAOException;

    /**
     * Find user by id.
     *
     * @param id user id
     * @return the user instance if searches were successful, otherwise null
     * @throws DAOException the dao exception
     */
    User findById(int id) throws DAOException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the user instance if searches were successful, otherwise null
     * @throws DAOException the dao exception
     */
    User findByLogin(String login) throws DAOException;

    /**
     * Find user password by user login.
     *
     * @param login user login
     * @return user password
     * @throws DAOException the dao exception
     */
    String findPasswordByLogin(String login) throws DAOException;

    /**
     * Update user fields.
     *
     * @param id         user id
     * @param name       user name
     * @param email      user email
     * @param department user department
     * @return true if updating successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean update(int id, String name, String email, String department) throws DAOException;

    /**
     * Sing in (find user by password and login). Password should be coded.
     *
     * @param login    user id
     * @param password user name
     * @return the user instance if searches were successful, otherwise null
     * @throws DAOException the dao exception
     */
    User singIn(String login, String password) throws DAOException;

    /**
     * Add new user. Password should be coded.
     *
     * @param user     added user
     * @param password user password
     * @return true if adding successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean add(User user, String password) throws DAOException;
}
