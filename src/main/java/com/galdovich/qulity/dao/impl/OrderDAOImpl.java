package com.galdovich.qulity.dao.impl;

import com.galdovich.qulity.dao.DAOException;
import com.galdovich.qulity.dao.OrderDAO;
import com.galdovich.qulity.dao.pool.ConnectionPool;
import com.galdovich.qulity.dao.util.DAOQuery;
import com.galdovich.qulity.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order dao.
 * {@code OrderDAO} interface implementation
 *
 * @author Galdovich Alexander
 * @version 1.0
 * @see OrderDAO
 */
public class OrderDAOImpl implements OrderDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<Order> findAll() throws DAOException {
        Connection connection = pool.getConnection();
        List<Order> result = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOQuery.ORDER_FIND_ALL);
            while (resultSet.next()) {
                result.add(new Order(resultSet.getString("orders.name")));
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception while finding all customers", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Order> findAll(int customerId) throws DAOException {
        Connection connection = pool.getConnection();
        List<Order> result = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(DAOQuery.ORDER_FIND_BY_CUSTOMER_ID);
            statement.setInt(1, customerId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Order(resultSet.getString("orders.name")));
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception while finding all customers", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean add(Order order) throws DAOException {
        boolean result;
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DAOQuery.ORDER_ADD);
            statement.setLong(1, order.getCustomer().getId());
            statement.setString(2, order.getName());
            statement.setDate(3, order.getOrderDate());
            statement.setDate(4, order.getShippedDate());
            statement.setString(5, order.getNotes());
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
