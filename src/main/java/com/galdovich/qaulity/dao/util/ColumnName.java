package com.galdovich.qaulity.dao.util;

/**
 * The class represents column name in database tables.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ColumnName {

    /**
     * Constants for users table
     */
    public static final String USER_ID = "users.id";
    public static final String USER_NAME = "users.name";
    public static final String USER_LOGIN = "users.login";
    public static final String USER_EMAIL = "users.email";
    public static final String USER_DEPARTMENT = "users.department";
    public static final String USER_ROLE = "users.role";

    /**
     * Constants for customers table
     */
    public static final String CUSTOMER_ID = "customers.id";
    public static final String CUSTOMER_NAME = "customers.name";
    public static final String CUSTOMER_ADDRESS = "customers.address";
    public static final String CUSTOMER_PHONE = "customers.phone";
    public static final String CUSTOMER_FAX = "customers.fax";
    public static final String CUSTOMER_EMAIL = "customers.email";

    /**
     * Constants for orders table
     */
    public static final String ORDER_ID = "orders.id";
    public static final String ORDER_NAME = "orders.name";
    public static final String ORDER_ORDER_DATE = "orders.order_date";
    public static final String ORDER_SHIPPED_DATE = "orders.shipped_date";
    public static final String ORDER_NOTES = "orders.notes";

    /**
     * Constants for products table
     */
    public static final String PRODUCTS_ID = "products.id";
    public static final String PRODUCTS_NAME = "products.name";
    public static final String PRODUCTS_DECIMAL_NUMBER = "products.decimal_number";
    public static final String PRODUCTS_AMOUNT = "products.amount";
    public static final String PRODUCTS_PRIORITY = "products.priority";
    public static final String PRODUCTS_MODEL_LINK = "products.model_link";
    public static final String PRODUCTS_MODEL_ICON = "products.model_icon";
}
