package com.galdovich.qaulity.controller.command.impl;

import com.galdovich.qaulity.controller.Router;
import com.galdovich.qaulity.controller.command.ActionCommand;
import com.galdovich.qaulity.controller.command.AttributeKey;
import com.galdovich.qaulity.entity.Product;
import com.galdovich.qaulity.service.CustomerService;
import com.galdovich.qaulity.service.ProductService;
import com.galdovich.qaulity.service.ServiceException;
import com.galdovich.qaulity.service.factory.ServiceFactory;
import com.galdovich.qaulity.util.PageManager;
import com.galdovich.qaulity.util.UploadType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qaulity.controller.command.AttributeKey.*;

/**
 * The class represents command to upload {@code UploadType.ICON} or {@code UploadType.MODEL} values.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ProductUploadCommand implements ActionCommand {
    private static final Logger logger = Logger.getLogger(ProductUploadCommand.class);
    private static final ProductService PRODUCT_SERVICE = ServiceFactory.getInstance().getProductService();
    private static final CustomerService CUSTOMER_SERVICE = ServiceFactory.getInstance().getCustomerService();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<UploadType, String> requestData = new HashMap<>();
        requestData.put(UploadType.ICON, (String) session.getAttribute(ICON_UPLOAD));
        requestData.put(UploadType.MODEL, (String) session.getAttribute(MODEL_UPLOAD));
        session.removeAttribute(ICON_UPLOAD);
        session.removeAttribute(MODEL_UPLOAD);
        Router router;
        try {
            Product product = PRODUCT_SERVICE.findById((int) session.getAttribute(AttributeKey.PRODUCT_ID)).get();
            if (PRODUCT_SERVICE.update(requestData, product)) {
                request.setAttribute(PRODUCT_NAME, product.getFullName());
                request.setAttribute(CUSTOMER_NAME, product.getOrder().getCustomer().getName());
                request.setAttribute(ORDER_NAME, product.getOrder().getName());
                request.setAttribute(PRODUCT_MODEL_LINK, product.getModelLink());
                request.setAttribute(PRODUCT_SHIPPED_DATE, product.getOrder().getShippedDate());
                request.setAttribute(PRODUCT_AMOUNT, product.getAmount());
                request.setAttribute(PRODUCT_PRIORITY, product.getPriority());
                request.setAttribute(PRODUCT_MODEL_ICON, product.getModelIcon());
                request.setAttribute(PRODUCTION_MAP, product.getProductionMap());
                request.setAttribute(CUSTOMER_LIST, CUSTOMER_SERVICE.findAll());
                request.setAttribute(ORDER_LIST, CUSTOMER_SERVICE.findAllOrders(product.getOrder().getCustomer().getId()));
                router = new Router(PageManager.PRODUCT.getPath());
            } else {
                router = new Router(PageManager.PRODUCT.getPath());
            }
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "Error while upload icon or model", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
