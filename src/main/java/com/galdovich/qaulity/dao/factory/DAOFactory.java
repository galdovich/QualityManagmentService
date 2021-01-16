package com.galdovich.qaulity.dao.factory;

import com.galdovich.qaulity.dao.CustomerDAO;
import com.galdovich.qaulity.dao.OrderDAO;
import com.galdovich.qaulity.dao.ProductDAO;
import com.galdovich.qaulity.dao.UserDAO;
import com.galdovich.qaulity.dao.impl.CustomerDAOImpl;
import com.galdovich.qaulity.dao.impl.OrderDAOImpl;
import com.galdovich.qaulity.dao.impl.ProductDAOImpl;
import com.galdovich.qaulity.dao.impl.UserDAOImpl;

/**
 * The class represents dao factory.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    private DAOFactory() {
    }

    /**
     * Gets factory instance.
     *
     * @return the instance
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public UserDAO getUserDao() {
        return userDAO;
    }

    /**
     * Gets customer dao.
     *
     * @return the customer dao
     */
    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    /**
     * Gets order dao.
     *
     * @return the customer dao
     */
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * Gets product dao.
     *
     * @return the customer dao
     */
    public ProductDAO getProductDAO() {
        return productDAO;
    }
}

