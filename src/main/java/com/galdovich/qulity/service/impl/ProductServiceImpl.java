package com.galdovich.qulity.service.impl;

import com.galdovich.qulity.dao.CustomerDAO;
import com.galdovich.qulity.dao.DAOException;
import com.galdovich.qulity.dao.OrderDAO;
import com.galdovich.qulity.dao.ProductDAO;
import com.galdovich.qulity.dao.factory.DAOFactory;
import com.galdovich.qulity.entity.*;
import com.galdovich.qulity.service.ProductService;
import com.galdovich.qulity.service.ServiceException;
import com.galdovich.qulity.service.validator.ProductValidator;
import com.galdovich.qulity.util.ParameterKey;
import com.galdovich.qulity.util.UploadType;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.galdovich.qulity.controller.command.AttributeKey.PRODUCT_MESSAGE;

/**
 * The class represents product service implementation.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
    private static ProductDAO PRODUCT_DAO = DAOFactory.getInstance().getProductDAO();
    private static CustomerDAO CUSTOMER_DAO = DAOFactory.getInstance().getCustomerDAO();
    private static OrderDAO ORDER_DAO = DAOFactory.getInstance().getOrderDAO();

    @Override
    public List<Product> findAll() throws ServiceException {
        List<Product> productList;
        try {
            productList = PRODUCT_DAO.findAll();
            calculateProgress(productList);
        } catch (DAOException exc) {
            throw new ServiceException("Error while finding list of products", exc);
        }
        return productList;
    }

    @Override
    public Product findById(int id) throws ServiceException {
        Product product;
        try {
            product = PRODUCT_DAO.findById(id);
        } catch (DAOException exc) {
            throw new ServiceException("", exc);
        }
        return product;
    }

    @Override
    public Integer findIdByDecimal(String decimalNumber) throws ServiceException {
        Integer productId = null;
        // TODO: 18.11.2020
        if (ProductValidator.isCorrectFullName(decimalNumber)) {
            try {
                productId = PRODUCT_DAO.findIdByDecimal(decimalNumber);
            } catch (DAOException exc) {
                throw new ServiceException("", exc);
            }
        }
        return productId;
    }

    @Override
    public boolean add(Map<String, String> requestData) throws ServiceException {
        boolean result = false;
        try {
            List<Customer> customerList = CUSTOMER_DAO.findAll();
            List<Order> orderList = ORDER_DAO.findAll();
            if (ProductValidator.validateRequestData(requestData, customerList, orderList)) {
                if(result = PRODUCT_DAO.add(createProduct(requestData))){
                    requestData.put(PRODUCT_MESSAGE, "Added!");
                }
            }
        } catch (DAOException exc) {
            throw new ServiceException("", exc);
        }
        return result;
    }


    @Override
    public boolean update(Map<UploadType, String> requestData, Product product) throws ServiceException {
        boolean result = false;
        if (ProductValidator.validateRequestData(requestData)) {
            try {
                updateProduct(requestData, product);
                result = PRODUCT_DAO.update(product);
            } catch (DAOException exc) {
                throw new ServiceException("", exc);
            }
        }
        return result;
    }

    @Override
    public boolean update(int id, String name, Date date, String comment) throws ServiceException {
        boolean result;
        try {
            Map<RouteMap, Production> map = PRODUCT_DAO.findById(id).getProductionMap();
            int queue = (int) map.values().stream().filter(Objects::nonNull).count() + 1;
            result = PRODUCT_DAO.update(id, queue, name, date, comment);
        } catch (DAOException exc) {
            throw new ServiceException("", exc);
        }
        return result;
    }

    /**
     * The method calculates the percentage of work that is completed for each product in the input list.
     * The calculation is carried out according to the formula:
     * 100 - (operations performed / total number of operations).
     *
     * @param productList the list of products
     */
    private void calculateProgress(List<Product> productList) {
        productList.stream().filter(p -> !p.getProductionMap().isEmpty())
                .forEach(product -> product.setProgress(
                        (100 - (product.getProductionMap().values().stream()
                                .filter(Objects::isNull).count() * 100) / product.getProductionMap().keySet().size())));
    }

    private void updateProduct(Map<UploadType, String> requestData, Product product) {
        String icon = requestData.get(UploadType.ICON);
        String model = requestData.get(UploadType.MODEL);
        if (icon == null || icon.equals(null)) {
            product.setModelLink(model);
        } else {
            product.setModelIcon(icon);
        }
    }

    private Product createProduct(Map<String, String> requestMap) {
        Product product = new Product();
        product.setName(requestMap.get(ParameterKey.PRODUCT_NAME));
        product.setDecimalNumber(requestMap.get(ParameterKey.PRODUCT_DECIMAL));
        product.setOrder(new Order(requestMap.get(ParameterKey.ORDER_NAME)));
        product.setAmount(Integer.valueOf(requestMap.get(ParameterKey.PRODUCT_AMOUNT)));
        product.setPriority(Priority.valueOf(requestMap.get(ParameterKey.PRODUCT_PRIORITY)));
        return product;
    }
}
