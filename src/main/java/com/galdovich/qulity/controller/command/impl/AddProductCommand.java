package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import com.galdovich.qulity.util.ParameterKey;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qulity.controller.command.AttributeKey.*;

public class AddProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(AddProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put(ParameterKey.PRODUCT_NAME, request.getParameter(PRODUCT_NAME));
        dataMap.put(ParameterKey.PRODUCT_DECIMAL, request.getParameter(PRODUCT_DECIMAL_NUMBER));
        dataMap.put(ParameterKey.CUSTOMER_NAME, request.getParameter(CUSTOMER_NAME));
        dataMap.put(ParameterKey.ORDER_NAME, request.getParameter(ORDER_NAME));
        dataMap.put(ParameterKey.PRODUCT_AMOUNT, request.getParameter(PRODUCT_AMOUNT));
        dataMap.put(ParameterKey.PRODUCT_PRIORITY, request.getParameter(PRODUCT_PRIORITY));
        Router router;
        try {
            PRODUCT_SERVICE.add(dataMap);
            request.setAttribute(AttributeKey.PRODUCT_DATA_MAP, dataMap);
            request.setAttribute(AttributeKey.PRODUCT_ADD_FLAG, true);
            router = new Router(PageManager.ADMIN_PATH.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Add error", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
