package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.entity.Customer;
import com.galdovich.qaulity.entity.Product;
import com.galdovich.qaulity.entity.User;
import com.galdovich.qaulity.service.CustomerService;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.UserService;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * The class represents command to create admin menu page.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class AdminMenuCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(AdminMenuCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();
    private static final String EMPTY_LINE = "";
    private static final int START_PAGE = 1;

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        try {
            List<User> userList = USER_SERVICE.findAll();
            List<Product> productList = PRODUCT_SERVICE.findAll();
            List<Customer> customerList = CUSTOMER_SERVICE.findAll();

            session.setAttribute(AttributeKey.PRODUCT_ID, EMPTY_LINE);
            session.setAttribute(AttributeKey.PRODUCT_LIST, productList);
            session.setAttribute(AttributeKey.CUSTOMER_LIST, customerList);
            session.setAttribute(AttributeKey.USER_LIST, userList);
            session.setAttribute(AttributeKey.CUSTOMER_LIST, CUSTOMER_SERVICE.findAll());
            session.setAttribute(AttributeKey.PRODUCT_PAGE, START_PAGE);
            router = new Router(PageManager.ADMIN_MENU.getPath());
        } catch (ServiceException exc) {
            router = new Router(PageManager.ERROR.getPath());
            logger.log(Level.ERROR, "Error while searching a list of products", exc);
        }
        return router;
    }
}
