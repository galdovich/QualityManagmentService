package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.entity.Product;
import com.galdovich.qaulity.service.CustomerService;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.galdovich.qaulity.controller.command.AttributeKey.*;

/**
 * The class represents command to create product page by a {@code User} with the {@code Role.ADMIN} role.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        try {
            Product product = PRODUCT_SERVICE.findById(Integer.valueOf(request.getParameter(PRODUCT_ID))).get();
            session.setAttribute(PRODUCT_ID, product.getId());
            request.setAttribute(PRODUCT_NAME, product.getFullName());
            request.setAttribute(CUSTOMER_NAME, product.getOrder().getCustomer().getName());
            request.setAttribute(ORDER_NAME, product.getOrder().getName());
            request.setAttribute(PRODUCT_MODEL_LINK, product.getModelLink());
            request.setAttribute(PRODUCT_SHIPPED_DATE, product.getOrder().getShippedDate());
            request.setAttribute(PRODUCT_AMOUNT, product.getAmount());
            request.setAttribute(PRODUCT_PRIORITY, product.getPriority().getName());
            request.setAttribute(PRODUCT_MODEL_ICON, product.getModelIcon());
            request.setAttribute(PRODUCTION_MAP, product.getProductionMap());
            request.setAttribute(CUSTOMER_LIST, CUSTOMER_SERVICE.findAll());
            request.setAttribute(ORDER_LIST, CUSTOMER_SERVICE.findAllOrders(product.getOrder().getCustomer().getId()));
            router = new Router(PageManager.PRODUCT.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Finding user error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
