package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import com.galdovich.qaulity.util.ParameterKey;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * The class represents command to create adding a product.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class AddProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(AddProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(ParameterKey.PRODUCT_NAME, request.getParameter(AttributeKey.PRODUCT_NAME));
        dataMap.put(ParameterKey.PRODUCT_DECIMAL, request.getParameter(AttributeKey.PRODUCT_DECIMAL_NUMBER));
        dataMap.put(ParameterKey.CUSTOMER_NAME, request.getParameter(AttributeKey.CUSTOMER_NAME));
        dataMap.put(ParameterKey.ORDER_NAME, request.getParameter(AttributeKey.ORDER_NAME));
        dataMap.put(ParameterKey.PRODUCT_AMOUNT, request.getParameter(AttributeKey.PRODUCT_AMOUNT));
        dataMap.put(ParameterKey.PRODUCT_PRIORITY, request.getParameter(AttributeKey.PRODUCT_PRIORITY));
        Router router;
        try {
            PRODUCT_SERVICE.add(dataMap);
            request.setAttribute(AttributeKey.PRODUCT_DATA_MAP, dataMap);
            request.setAttribute(AttributeKey.PRODUCT_ADD_FLAG, true);
            router = new Router(PageManager.ADMIN_PATH.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while adding product", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
