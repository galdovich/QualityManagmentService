package com.galdovich.qulity.service;

import com.galdovich.qulity.entity.Customer;
import com.galdovich.qulity.entity.Order;

import java.util.List;

/**
 * The interface represents customer service.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface CustomerService {
    /**
     * Find all customers.
     *
     * @return the List of customers
     * @throws ServiceException the service exception
     */
    List<Customer> findAll() throws ServiceException;

    /**
     * Find all orders by customer id.
     *
     * @param customerId customer id
     * @return the list of customers
     * @throws ServiceException the service exception
     */
    List<Order> findAllOrders(int customerId) throws ServiceException;

    /**
     * Find customer by id.
     *
     * @param id customer id
     * @return the customer instance if searches were successful, otherwise null
     * @throws ServiceException the service exception
     */
    Customer findById(String id) throws ServiceException;

    /**
     * Add new customer
     *
     * @param name    the name
     * @param address the address
     * @param phone   the phone
     * @param fax     the fax
     * @param email   the email
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean add(String name, String address, String phone, String fax, String email) throws ServiceException;
}
