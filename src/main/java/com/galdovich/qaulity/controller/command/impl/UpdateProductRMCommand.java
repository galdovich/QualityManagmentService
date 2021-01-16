package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.Product;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

import static com.galdovich.qaulity.controller.command.AttributeKey.*;

/**
 * The class represents command of updating a product route map {@code RouteMap}.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
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

            if (product.getProductionMap().values().stream()
                    .filter(Objects::nonNull).count() == product.getProductionMap().keySet().size()) {
                request.setAttribute(AttributeKey.PRODUCT_STATE, true);
            }
            router = new Router(PageManager.PRODUCT.getPath());
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while updating route map", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
