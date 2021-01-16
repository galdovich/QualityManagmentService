package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.Customer;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.entity.User;
import com.galdovich.qulity.service.CustomerService;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.UserService;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminMenuCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(AdminMenuCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();
    private static final UserService USER_SERVICE = ServiceFactory.getInstance().getUserService();
    private static final int START_PAGE = 1;

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        try {
            List<User> userList = USER_SERVICE.findAll();
            List<Product> productList = PRODUCT_SERVICE.findAll();
            List<Customer> customerList = CUSTOMER_SERVICE.findAll();

            session.setAttribute(AttributeKey.PRODUCT_ID, "");
            session.setAttribute(AttributeKey.PRODUCT_LIST, productList);
            session.setAttribute(AttributeKey.CUSTOMER_LIST, customerList);
            session.setAttribute(AttributeKey.USER_LIST, userList);
            session.setAttribute(AttributeKey.CUSTOMER_LIST, CUSTOMER_SERVICE.findAll());
            session.setAttribute(AttributeKey.PRODUCT_PAGE, START_PAGE);
            router = new Router(PageManager.ADMIN_MENU.getPath());
        } catch (ServiceException exc) {
            router = new Router(PageManager.ERROR.getPath());
            logger.log(Level.ERROR, "Error while finding list of products", exc);
        }
        return router;
    }
}
