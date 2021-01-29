package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.Product;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import com.galdovich.qaulity.util.ParameterKey;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * The class represents command to edit {@code Product} values.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class EditProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(EditProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> editData = new HashMap<>();
        HttpSession session = request.getSession();
        Router router;
        editData.put(ParameterKey.CUSTOMER_NAME, request.getParameter(ParameterKey.CUSTOMER_NAME));
        editData.put(ParameterKey.ORDER_NAME, request.getParameter(ParameterKey.ORDER_NAME));
        editData.put(ParameterKey.PRODUCT_SHIPPED_NAME, request.getParameter(ParameterKey.PRODUCT_SHIPPED_NAME));
        editData.put(ParameterKey.PRODUCT_AMOUNT, request.getParameter(ParameterKey.PRODUCT_AMOUNT));
        editData.put(ParameterKey.PRODUCT_PRIORITY, request.getParameter(ParameterKey.PRODUCT_PRIORITY));
        int productId = (int) session.getAttribute(AttributeKey.PRODUCT_ID);
        try {
            Product product = PRODUCT_SERVICE.findById(productId).get();
            if (!isSameData(editData, product)) {
                if (PRODUCT_SERVICE.update(editData, productId)) {
                    request.setAttribute(AttributeKey.PRODUCT_SUCCESS_ADDING, true);
                } else {
                    request.setAttribute(AttributeKey.PRODUCT_FAIL_ADDING, true);
                }
            } else {
                request.setAttribute(AttributeKey.PRODUCT_SAME_VALUES, true);
            }
            router = new Router(PageManager.PRODUCT_PATH.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while searching for a product", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }

    /**
     * Check for identical values.
     *
     * @param editData the map with new edit product's values
     * @param product  parameter with the old product's values
     * @return true if {@code Map<String, String>} consists the same values as the {@code Product}
     */
    private boolean isSameData(Map<String, String> editData, Product product) {
        boolean result = true;
        if (!(editData.get(ParameterKey.CUSTOMER_NAME).equals(product.getOrder().getCustomer().getName())
                && editData.get(ParameterKey.ORDER_NAME).equals(product.getOrder().getName())
                && editData.get(ParameterKey.PRODUCT_SHIPPED_NAME).equals(product.getOrder().getShippedDate().toString())
                && editData.get(ParameterKey.PRODUCT_AMOUNT).equals(String.valueOf(product.getAmount()))
                && editData.get(ParameterKey.PRODUCT_PRIORITY).equals(product.getPriority().getName()))) {
            result = false;
        }
        return result;
    }
}
