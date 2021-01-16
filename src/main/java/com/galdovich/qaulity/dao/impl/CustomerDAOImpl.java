package com.galdovich.qaulity.dao.impl;

import com.galdovich.qaulity.dao.builder.CustomBuilder;
import com.galdovich.qaulity.dao.pool.ConnectionPool;
import com.galdovich.qaulity.dao.util.DAOQuery;
import com.galdovich.qaulity.dao.CustomerDAO;
import com.galdovich.qaulity.dao.DAOException;
import com.galdovich.qaulity.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Customer dao.
 * {@code CustomerDAO} interface implementation
 *
 * @author Galdovich Alexander
 * @version 1.0
 * @see CustomerDAO
 */
public class CustomerDAOImpl implements CustomerDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<Customer> findAll() throws DAOException {
        Connection connection = pool.getConnection();
        List<Customer> result = new ArrayList<Customer>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOQuery.CUSTOMER_FIND_ALL);
            while (resultSet.next()) {
                result.add(CustomBuilder.createCustomer(resultSet));
            }
        } catch (SQLException exc) {
            throw new DAOException("Error while finding all customers", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public Customer findById(String id) throws DAOException {
        Connection connection = pool.getConnection();
        Customer result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(DAOQuery.CUSTOMER_FIND_BY_ID);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = CustomBuilder.createCustomer(resultSet);
            }
        } catch (SQLException exc) {
            throw new DAOException("Error while finding customer by id", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean add(Customer customer) throws DAOException {
        boolean result = false;
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DAOQuery.CUSTOMER_ADD);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getFax());
            statement.setString(5, customer.getEmail());
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
}
