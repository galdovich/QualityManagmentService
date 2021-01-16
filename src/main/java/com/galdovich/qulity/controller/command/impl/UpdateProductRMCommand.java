package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.galdovich.qulity.controller.command.AttributeKey.*;

public class UpdateProductRMCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(UpdateProductRMCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(AttributeKey.PRODUCT_ID);
        String name = request.getParameter(AttributeKey.USER_NAME);
        Date date = new Date(new Date().getTime());
        String comment = request.getParameter("");
        Router router;

        try {
            PRODUCT_SERVICE.update(id, name, date, comment);
            Product product = PRODUCT_SERVICE.findById(id);
            request.setAttribute(PRODUCT_NAME, product.getFullName());
            request.setAttribute(CUSTOMER_NAME, product.getOrder().getCustomer().getName());
            request.setAttribute(ORDER_NAME, product.getOrder().getName());
            request.setAttribute(PRODUCT_MODEL_LINK, product.getModelLink());
            request.setAttribute(PRODUCT_MODEL_ICON, product.getModelIcon());
            request.setAttribute(PRODUCT_SHIPPED_DATE, product.getOrder().getShippedDate());
            request.setAttribute(PRODUCT_AMOUNT, product.getAmount());
            request.setAttribute(PRODUCT_PRIORITY, product.getPriority());
            request.setAttribute(PRODUCTION_MAP, product.getProductionMap());
            router = new Router(PageManager.PRODUCT.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
