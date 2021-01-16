package com.galdovich.qaulity.service.factory;

import com.galdovich.qaulity.service.CustomerService;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.impl.CustomerServiceImpl;
import com.galdovich.qaulity.service.impl.ProductServiceImpl;
import com.galdovich.qaulity.service.impl.UserServiceImpl;

/**
 * The class represents Service factory.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final CustomerService customerService = new CustomerServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets factory instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return instance;
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets customer service.
     *
     * @return the customer service
     */
    public CustomerService getCustomerService() {
        return customerService;
    }

    /**
     * Gets product service.
     *
     * @return the customer service
     */
    public ProductService getProductService() {
        return productService;
    }
}

