package com.galdovich.qaulity.dao.impl;

import com.galdovich.qaulity.dao.DAOException;
import com.galdovich.qaulity.dao.UserDAO;
import com.galdovich.qaulity.dao.pool.ConnectionPool;
import com.galdovich.qaulity.dao.util.ColumnName;
import com.galdovich.qaulity.dao.util.DAOQuery;
import com.galdovich.qaulity.entity.Department;
import com.galdovich.qaulity.entity.Role;
import com.galdovich.qaulity.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type User dao.
 * {@code UserDAO} interface implementation
 *
 * @author Galdovich Alexander
 * @version 1.0
 * @see UserDAO
 */
public class UserDAOImpl implements UserDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<User> findAll() throws DAOException {
        Connection connection = pool.getConnection();
        List<User> result = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOQuery.USER_FIND_ALL);
            while (resultSet.next()) {
                result.add(createUser(resultSet));
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception while finding all customers", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    public Optional<User> findById(int id) throws DAOException {
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<User> user;
        try {
            statement = connection.prepareStatement(DAOQuery.USER_FIND_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = Optional.of(createUser(resultSet));
            }else {
                user = Optional.empty();
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception ", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DAOException {
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<User> user;
        try {
            statement = connection.prepareStatement(DAOQuery.USER_FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(createUser(resultSet));
            } else {
                user = Optional.empty();
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception ", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public Optional<User> singIn(String login, String password) throws DAOException {
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<User> user;
        try {
            statement = connection.prepareStatement(DAOQuery.USER_FIND_BY_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                user = Optional.of(createUser(resultSet));
            }else {
                user = Optional.empty();
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception ", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean update(int id, String name, String email, String department) throws DAOException {
        boolean result = false;
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DAOQuery.USER_UPDATE);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setInt(3, Department.getDepartment(department).ordinal());
            statement.setInt(4, id);
            statement.executeUpdate();
            result = true;
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception ", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public String findPasswordByLogin(String login) throws DAOException {
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String result = null;
        try {
            statement = connection.prepareStatement(DAOQuery.USER_FIND_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(1);
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception ", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean add(User newUser, String password) throws DAOException {
        boolean result = false;
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DAOQuery.USER_ADD);
            statement.setString(1, newUser.getName());
            statement.setString(2, newUser.getLogin());
            statement.setString(3, password);
            statement.setString(4, newUser.getEmail());
            statement.setInt(5, newUser.getDepartment().ordinal());
            statement.setInt(6, newUser.getRole().ordinal());
            statement.executeUpdate();
            result = true;
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception ", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ColumnName.USER_ID));
        user.setName(resultSet.getString(ColumnName.USER_NAME));
        user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
        user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
        user.setDepartment(Department.getUserDepartment(resultSet.getInt(ColumnName.USER_DEPARTMENT)));
        user.setRole(Role.getUserRole(resultSet.getInt(ColumnName.USER_ROLE)));
        return user;
    }
}
