package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.Priority;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.galdovich.qulity.controller.command.AttributeKey.*;

public class UpdateProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(UpdateProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int amount = Integer.valueOf(request.getParameter(AttributeKey.PRODUCT_AMOUNT));
        Priority priority = Priority.valueOf(request.getParameter(AttributeKey.PRODUCT_PRIORITY));
        Router router;
        try {
            Product product = PRODUCT_SERVICE.findById((int) session.getAttribute(AttributeKey.PRODUCT_ID));
            product.setAmount(amount);
            product.setPriority(priority);
            request.setAttribute(PRODUCT_NAME, product.getFullName());
            request.setAttribute(CUSTOMER_NAME, product.getOrder().getCustomer().getName());
            request.setAttribute(ORDER_NAME, product.getOrder().getName());
            request.setAttribute(PRODUCT_MODEL_LINK, product.getModelLink());
            request.setAttribute(PRODUCT_SHIPPED_DATE, product.getOrder().getShippedDate());
            request.setAttribute(PRODUCT_AMOUNT, product.getAmount());
            request.setAttribute(PRODUCT_PRIORITY, product.getPriority());
            request.setAttribute(PRODUCT_MODEL_ICON, product.getModelIcon());
            request.setAttribute(PRODUCTION_MAP, product.getProductionMap());
            router = new Router(PageManager.PRODUCT.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
