package com.galdovich.qaulity.dao;

import com.galdovich.qaulity.entity.Order;

import java.util.List;

/**
 * The interface represents order dao. Defines specific methods which interactions with Order entities in database.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface OrderDAO {

    /**
     * Find all orders.
     *
     * @return the List of customers
     * @throws DAOException the dao exception
     */
    List<Order> findAll() throws DAOException;

    /**
     * Find all orders that belong certain customer.
     *
     * @param customerId customer id
     * @return the List of customers
     * @throws DAOException the dao exception
     */
    List<Order> findAll(int customerId) throws DAOException;

    /**
     * Add new order.
     *
     * @param order added order
     * @return true if adding successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean add(Order order) throws DAOException;
}
