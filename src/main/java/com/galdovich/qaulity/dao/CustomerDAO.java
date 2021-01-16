package com.galdovich.qaulity.dao;

import com.galdovich.qaulity.entity.Customer;

import java.util.List;

/**
 * The interface represents customer dao. Defines specific methods which interactions with Customer entities in database.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface CustomerDAO {

    /**
     * Find all customers.
     *
     * @return the List of customers
     * @throws DAOException the dao exception
     */
    List<Customer> findAll() throws DAOException;

    /**
     * Find customer by id.
     *
     * @param id customer id
     * @return the customer instance if searches were successful, otherwise null
     * @throws DAOException the dao exception
     */
    Customer findById(String id) throws DAOException;

    /**
     * Add new customer to database
     *
     * @param customer added customer
     * @return true if adding successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean add(Customer customer) throws DAOException;
}
