package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.service.CustomerService;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.galdovich.qulity.controller.command.AttributeKey.*;

public class ProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();
    private static final String ERROR_MESSAGE = "product.error.message";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router;
        try {
            Product product = PRODUCT_SERVICE.findById(Integer.valueOf(request.getParameter(PRODUCT_ID)));
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
            request.setAttribute("customer_list", CUSTOMER_SERVICE.findAll());
            request.setAttribute("orders_list", CUSTOMER_SERVICE.findAllOrders(product.getOrder().getCustomer().getId()));
            request.setAttribute("reqmessage", session.getAttribute("message"));
            router = new Router(PageManager.PRODUCT.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Finding user error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
