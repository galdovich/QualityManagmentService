package com.galdovich.qulity.dao.impl;

import com.galdovich.qulity.dao.DAOException;
import com.galdovich.qulity.dao.ProductDAO;
import com.galdovich.qulity.dao.builder.CustomBuilder;
import com.galdovich.qulity.dao.pool.ConnectionPool;
import com.galdovich.qulity.dao.util.ColumnName;
import com.galdovich.qulity.dao.util.DAOQuery;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.entity.Production;
import com.galdovich.qulity.entity.RouteMap;
import com.galdovich.qulity.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Product dao.
 * {@code ProductDAO} interface implementation
 *
 * @author Galdovich Alexander
 * @version 1.0
 * @see ProductDAO
 */
public class ProductDAOImpl implements ProductDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public List<Product> findAll() throws DAOException {
        Connection connection = pool.getConnection();
        List<Product> result = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(DAOQuery.PRODUCT_FIND_ALL);
            while (resultSet.next()) {
                Product product = CustomBuilder.createFullProduct(resultSet);
                product.setProductionMap(getProductionMap(connection, product.getId()));
                result.add(product);
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception in DAO while finding all customers", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    public Product findById(int id) throws DAOException {
        Connection connection = pool.getConnection();
        Product product;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(DAOQuery.PRODUCT_FIND_BY_ID, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            product = CustomBuilder.createProduct(resultSet);
            product.setProductionMap(getProductionMap(connection, id));
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception int DAO while finding customer by id", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return product;
    }

    @Override
    public boolean add(Product product) throws DAOException {
        boolean result = false;
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int orderId = getOrderByName(connection, product.getOrder().getName());
            statement = connection.prepareStatement(DAOQuery.PRODUCT_ADD);
            statement.setLong(1, orderId);
            statement.setString(2, product.getName());
            statement.setString(3, product.getDecimal_number());
            statement.setInt(4, product.getAmount());
            statement.setString(5, product.getPriority().name());
            statement.executeUpdate();
            result = true;
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception int DAO while adding product", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public Integer findIdByDecimal(String decimalNumber) throws DAOException {
        Connection connection = pool.getConnection();
        Integer result = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(DAOQuery.PRODUCT_ID);
            statement.setString(1, decimalNumber);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getInt(ColumnName.PRODUCTS_ID);
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception in DAO while finding customer by decimal number", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(resultSet, statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean update(Product product) throws DAOException {
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean result;
        try {
            statement = connection.prepareStatement(DAOQuery.PRODUCT_UPDATE);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDecimal_number());
            statement.setInt(3, product.getAmount());
            statement.setString(4, product.getPriority().name());
            System.out.println(product.getModelLink());
            statement.setString(5, product.getModelLink());
            statement.setString(6, product.getModelIcon());
            statement.setInt(7, product.getId());
            statement.executeUpdate();
            result = true;
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception while updating 'products' table", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean update(int id, int queue, String name, java.util.Date date, String comment) throws DAOException {
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean result;
        try {
            statement = connection.prepareStatement(DAOQuery.PRODUCT_UPDATE_ROUTE_MAP);
            statement.setString(1, name);
            statement.setTimestamp(2, new Timestamp(date.getTime()));
            statement.setString(3, comment);
            statement.setInt(4, id);
            statement.setInt(5, queue);
            statement.executeUpdate();
            result = true;
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception while updating 'route map' table", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return result;
    }

    public int getOrderByName(Connection connection, String name) throws DAOException {
        PreparedStatement statement = null;
        int orderId = 0;
        try {
            statement = connection.prepareStatement(DAOQuery.ORDER_FIND_ID_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt(ColumnName.ORDER_ID);
            }
        } catch (SQLException exc) {
            throw new DAOException("SQL Exception while finding id by name", exc);
        } finally {
            ConnectionPool.getInstance().releaseResources(statement);
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return orderId;
    }

    private Map<RouteMap, Production> getProductionMap(Connection connection, int id) throws SQLException {
        Map<RouteMap, Production> map = new TreeMap<>();
        PreparedStatement statement = connection.prepareStatement(DAOQuery.PRODUCT_ROUTE_MAP_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("users.name") != null) {
                map.put(new RouteMap(resultSet.getInt("route_maps.queue"),
                                resultSet.getString("processes.name")),
                        new Production(
                                new User(resultSet.getString("users.name")),
                                resultSet.getTimestamp("route_maps.start_time"),
                                resultSet.getString("route_maps.comment")));
            } else {
                map.put(new RouteMap(resultSet.getInt("route_maps.queue"),
                                resultSet.getString("processes.name")),
                        null);
            }
        }
        return map;
    }

    private void createProductionMap() {

    }
}
