package com.galdovich.qaulity.dao;

import com.galdovich.qaulity.entity.Product;

import java.util.Date;
import java.util.List;

/**
 * The interface represents product dao. Defines specific methods which interactions with Product entities in database.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public interface ProductDAO {

    /**
     * Find all products.
     *
     * @return the List of products
     * @throws DAOException the dao exception
     */
    List<Product> findAll() throws DAOException;

    /**
     * Find product by id.
     *
     * @param id product id
     * @return the product instance if searches were successful, otherwise null
     * @throws DAOException the dao exception
     */
    Product findById(int id) throws DAOException;

    /**
     * Find product id by decimal number.
     *
     * @param decimalNumber product decimal number
     * @return the product id if searches were successful, otherwise null
     * @throws DAOException the dao exception
     */
    Integer findIdByDecimal(String decimalNumber) throws DAOException;

    /**
     * Add new product
     *
     * @param product added product
     * @return true if adding successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean add(Product product) throws DAOException;

    /**
     * Update product fields.
     *
     * @param product updated product
     * @return true if updating successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean update(Product product) throws DAOException;

    /**
     * Update route map.
     *
     * @param id      product id
     * @param queue   operation number
     * @param name    user name
     * @param date    time of work done
     * @param comment comment
     * @return true if updating successful, otherwise false
     * @throws DAOException the dao exception
     */
    boolean update(int id, int queue, String name, Date date, String comment) throws DAOException;
}
