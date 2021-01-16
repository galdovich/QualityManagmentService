package com.galdovich.qulity.service;

import com.galdovich.qulity.entity.Product;
import com.galdovich.qulity.util.UploadType;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The interface represents product service.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface ProductService {
    /**
     * Find all products.
     *
     * @return the List of products
     * @throws ServiceException the service exception
     */
    List<Product> findAll() throws ServiceException;

    /**
     * Find product by id.
     *
     * @param id product id
     * @return the product instance if searches were successful, otherwise null
     * @throws ServiceException the service exception
     */
    Product findById(int id) throws ServiceException;

    /**
     * Find product id by decimal number.
     *
     * @param decimalNumber product decimal number
     * @return the product id if searches were successful, otherwise null
     * @throws ServiceException the service exception
     */
    Integer findIdByDecimal(String decimalNumber) throws ServiceException;

    /**
     * Add new product
     *
     * @param requestData the request data
     * @return true if adding successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean add(Map<String, String> requestData) throws ServiceException;

    /**
     * Update product fields.
     *
     * @param product updated product
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean update(Map<UploadType, String> requestData, Product product) throws ServiceException;

    /**
     * Update route map.
     *
     * @param id      product id
     * @param name    user name
     * @param date    time of work done
     * @param comment comment
     * @return true if updating successful, otherwise false
     * @throws ServiceException the service exception
     */
    boolean update(int id, String name, Date date, String comment) throws ServiceException;
}
