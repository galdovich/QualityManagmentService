package com.galdovich.qulity.dao.factory;

import com.galdovich.qulity.dao.CustomerDAO;
import com.galdovich.qulity.dao.OrderDAO;
import com.galdovich.qulity.dao.ProductDAO;
import com.galdovich.qulity.dao.UserDAO;
import com.galdovich.qulity.dao.impl.CustomerDAOImpl;
import com.galdovich.qulity.dao.impl.OrderDAOImpl;
import com.galdovich.qulity.dao.impl.ProductDAOImpl;
import com.galdovich.qulity.dao.impl.UserDAOImpl;

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

