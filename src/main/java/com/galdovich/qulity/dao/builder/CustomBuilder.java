package com.galdovich.qulity.dao.builder;

import com.galdovich.qulity.dao.util.ColumnName;
import com.galdovich.qulity.entity.Customer;
import com.galdovich.qulity.entity.Order;
import com.galdovich.qulity.entity.Priority;
import com.galdovich.qulity.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class represents builder.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class CustomBuilder {

    private CustomBuilder() {
    }

    /**
     * Build the customer instance.
     *
     * @param resultSet the customer parameters
     * @return the customer instance
     */
    public static Customer createCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt(ColumnName.CUSTOMER_ID));
        customer.setName(resultSet.getString(ColumnName.CUSTOMER_NAME));
        customer.setAddress(resultSet.getString(ColumnName.CUSTOMER_ADDRESS));
        customer.setPhone(resultSet.getString(ColumnName.CUSTOMER_PHONE));
        customer.setFax(resultSet.getString(ColumnName.CUSTOMER_FAX));
        customer.setEmail(resultSet.getString(ColumnName.CUSTOMER_EMAIL));
        return customer;
    }

    /**
     * Build the order instance.
     *
     * @param resultSet the order parameters
     * @return the order instance
     */
    public static Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(ColumnName.ORDER_ID));
        order.setCustomer(createCustomer(resultSet));
        order.setName(resultSet.getString(ColumnName.ORDER_NAME));
        order.setOrderDate(resultSet.getDate(ColumnName.ORDER_ORDER_DATE));
        order.setShippedDate(resultSet.getDate(ColumnName.ORDER_SHIPPED_DATE));
        order.setNotes(resultSet.getString(ColumnName.ORDER_NOTES));
        return order;
    }

    /**
     * Build the product instance with full fields.
     *
     * @param resultSet the product parameters
     * @return the product instance
     */
    public static Product createFullProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(ColumnName.PRODUCTS_ID));
        product.setOrder(createOrder(resultSet));
        product.setName(resultSet.getString(ColumnName.PRODUCTS_NAME));
        product.setDecimalNumber(resultSet.getString(ColumnName.PRODUCTS_DECIMAL_NUMBER));
        product.setAmount(resultSet.getInt(ColumnName.PRODUCTS_AMOUNT));
        product.setPriority(Priority.valueOf(resultSet.getString(ColumnName.PRODUCTS_PRIORITY)));
        product.setModelLink(resultSet.getString(ColumnName.PRODUCTS_MODEL_LINK));
        product.setModelIcon(resultSet.getString(ColumnName.PRODUCTS_MODEL_ICON));
        return product;
    }

    /**
     * Build the product instance with some fields.
     *
     * @param resultSet the product parameters
     * @return the product instance
     */
    public static Product createProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(ColumnName.PRODUCTS_ID));
        product.setOrder(new Order(resultSet.getInt(ColumnName.ORDER_ID), resultSet.getString(ColumnName.ORDER_NAME),
                new Customer(resultSet.getInt(ColumnName.CUSTOMER_ID), resultSet.getString(ColumnName.CUSTOMER_NAME)),
                resultSet.getDate(ColumnName.ORDER_SHIPPED_DATE)));
        product.setName(resultSet.getString(ColumnName.PRODUCTS_NAME));
        product.setDecimalNumber(resultSet.getString(ColumnName.PRODUCTS_DECIMAL_NUMBER));
        product.setAmount(resultSet.getInt(ColumnName.PRODUCTS_AMOUNT));
        product.setPriority(Priority.valueOf(resultSet.getString(ColumnName.PRODUCTS_PRIORITY)));
        product.setModelLink(resultSet.getString(ColumnName.PRODUCTS_MODEL_LINK));
        product.setModelIcon(resultSet.getString(ColumnName.PRODUCTS_MODEL_ICON));
        return product;
    }
}
