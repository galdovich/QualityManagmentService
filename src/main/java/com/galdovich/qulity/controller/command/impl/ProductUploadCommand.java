package com.galdovich.qulity.controller.command.impl;

import com.galdovich.qulity.controller.Router;
import com.galdovich.qulity.controller.command.ActionCommand;
import com.galdovich.qulity.controller.command.AttributeKey;
import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.service.CustomerService;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.factory.ServiceFactory;
import com.galdovich.qulity.util.PageManager;
import com.galdovich.qulity.util.UploadType;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.galdovich.qulity.controller.command.AttributeKey.*;

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
            Product product = PRODUCT_SERVICE.findById((int) session.getAttribute(AttributeKey.PRODUCT_ID));
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
                request.setAttribute("message", "");
                router = new Router(PageManager.PRODUCT.getPath());
            }
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, "", exc);
            router = new Router(PageManager.ERROR.getPath());
        }
        return router;
    }
}
