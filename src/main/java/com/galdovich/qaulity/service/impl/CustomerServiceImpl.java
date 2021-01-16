package com.galdovich.qaulity.service.impl;

import com.galdovich.qaulity.dao.factory.DAOFactory;
import com.galdovich.qaulity.dao.CustomerDAO;
import com.galdovich.qaulity.dao.DAOException;
import com.galdovich.qaulity.dao.OrderDAO;
import com.galdovich.qaulity.entity.Customer;
import com.galdovich.qaulity.entity.Order;
import com.galdovich.qaulity.service.CustomerService;
import com.galdovich.qaulity.service.ServiceException;

import java.util.List;

/**
 * The class represents customer service implementation.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class CustomerServiceImpl implements CustomerService {
    private static CustomerDAO CUSTOMER_DAO = DAOFactory.getInstance().getCustomerDAO();

    @Override
    public List<Customer> findAll() throws ServiceException {
        List<Customer> list;
        try {
            list = CUSTOMER_DAO.findAll();
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding all customers", exc);
        }
        return list;
    }

    public List<Order> findAllOrders(int customerId) throws ServiceException {
        List<Order> resultList;
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            resultList = orderDAO.findAll(customerId);
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding all orders", exc);
        }
        return resultList;
    }

    @Override
    public Customer findById(String id) throws ServiceException {
        Customer customer;
        try {
            customer = CUSTOMER_DAO.findById(id);
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding customer by id", exc);
        }
        return customer;
    }

    @Override
    public boolean add(String name, String address, String phone, String fax, String email) throws ServiceException {
        try {
            return CUSTOMER_DAO.add(createCustomer(name, address, phone, fax, email));
        } catch (DAOException exc) {
            throw new ServiceException("Exception while adding new Customer", exc);
        }
    }

    Customer createCustomer(String name, String address, String phone, String fax, String email) {
        return new Customer(name, address, phone, fax, email);
    }
}
