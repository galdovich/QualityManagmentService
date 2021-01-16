package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.MessageManager;
import com.galdovich.qulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.galdovich.qulity.controller.command.AttributeKey.*;

public class ClientProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ClientProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();
    private static final String ERROR_MESSAGE = "product.error.message";

    @Override
    public Router execute(HttpServletRequest request) {
        String decimalNumber = request.getParameter("decimal_number");
        Router router;
        try {
            if (PRODUCT_SERVICE.findIdByDecimal(decimalNumber) != null) {
                Product product = PRODUCT_SERVICE.findById(PRODUCT_SERVICE.findIdByDecimal(decimalNumber));
                request.setAttribute(PRODUCT_ID, product.getId());
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
            } else {
                request.setAttribute(PRODUCT_ERROR_TAG, MessageManager.getProperty(ERROR_MESSAGE));
                router = new Router(PageManager.CLIENT_MENU.getPath());
            }
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Finding product problem", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
