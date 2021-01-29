package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
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

import static com.galdovich.qaulity.controller.command.AttributeKey.*;

/**
 * The class represents command to create product page by a {@code User} with the {@code Role.WORKER} role.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ClientProductCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ClientProductCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String decimalNumber = request.getParameter(ParameterKey.PRODUCT_DECIMAL);
        Router router;
        try {
            if (PRODUCT_SERVICE.findIdByDecimal(decimalNumber) != null) {
                Product product = PRODUCT_SERVICE.findById(PRODUCT_SERVICE.findIdByDecimal(decimalNumber)).get();
                session.setAttribute(PRODUCT_ID, product.getId());
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
                request.setAttribute(PRODUCT_ERROR_TAG, true);
                router = new Router(PageManager.CLIENT_MENU.getPath());
            }
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while searching for a product", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
