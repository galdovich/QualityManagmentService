package com.galdovich.qulity.service.impl;

import com.galdovich.qulity.dao.CustomerDAO;
import com.galdovich.qulity.dao.DAOException;
import com.galdovich.qulity.dao.OrderDAO;
import com.galdovich.qulity.dao.factory.DAOFactory;
import com.galdovich.qulity.entity.Customer;
import com.galdovich.qulity.entity.Order;
import com.galdovich.qulity.service.CustomerService;
import com.galdovich.qulity.service.ServiceException;

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

    public List<Order> findAllOrders(int customerId) throws ServiceException{
        List<Order> resultList;
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        try {
            resultList = orderDAO.findAll(customerId);
        } catch (DAOException exc) {
            throw new ServiceException("", exc);
        }
        return resultList;
    }

    @Override
    public Customer findById(String id) {
        Customer customer = null;
        try {
            customer = CUSTOMER_DAO.findById(id);
        } catch (DAOException e) {
            //log
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
