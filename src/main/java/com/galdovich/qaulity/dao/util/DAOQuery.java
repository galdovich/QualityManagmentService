package com.galdovich.qaulity.dao.util;

/**
 * The class represents sql queries.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class DAOQuery {

    /**
     * Sql queries for users table
     */
    public static final String USER_FIND_ALL =
            "SELECT users.id, users.name, users.login, users.email, users.department, users.role FROM users";
    public static final String USER_FIND_BY_ID =
            "SELECT users.id, users.name, users.login, users.email, users.department, users.role " +
                    "FROM users " +
                    "WHERE users.id = ?";
    public static final String USER_FIND_BY_LOGIN =
            "SELECT users.id, users.name, users.login, users.email, users.department, users.role " +
                    "FROM users " +
                    "WHERE users.login = ?";
    public static final String USER_FIND_BY_LOGIN_PASSWORD =
            "SELECT users.id, users.name, users.login, users.email, users.department, users.role " +
                    "FROM users " +
                    "WHERE (users.login = ? AND users.password = ?)";
    public static final String USER_ADD =
            "INSERT INTO users (name, login, password, email, department, role) VALUES (?,?,?,?,?,?)";
    public static final String USER_FIND_PASSWORD_BY_LOGIN =
            "SELECT users.password FROM users WHERE login = ?";
    public static final String USER_UPDATE =
            "UPDATE users SET name=?, email=?, department=? WHERE id=?";

    /**
     * Sql queries for customer table
     */
    public static final String CUSTOMER_FIND_ALL =
            "SELECT id, name, address, phone, fax, email FROM customers";
    public static final String CUSTOMER_ADD =
            "INSERT INTO customers (name, address, phone, fax, email) VALUES (?,?,?,?,?)";
    public static final String CUSTOMER_FIND_BY_ID =
            "SELECT id, name, address, phone, fax, email FROM customers WHERE id=?";

    /**
     * Sql queries for orders table
     */
    public static final String ORDER_FIND_ID_BY_NAME = "SELECT DISTINCT orders.id FROM orders WHERE orders.name=?";
    public static final String ORDER_FIND_ALL = "SELECT DISTINCT orders.name FROM orders";
    public static final String ORDER_FIND_BY_CUSTOMER_ID = "SELECT DISTINCT orders.name FROM orders WHERE customer_id=?";
    public static final String ORDER_ADD =
            "INSERT INTO orders (customer_id, name, order_date, shipped_date, notes) VALUES (?,?,?,?,?)";


    /**
     * Sql queries for products table
     */
    public static final String PRODUCT_FIND_ALL =
            "SELECT products.id, orders.id, customers.id, customers.name, customers.address, customers.phone, " +
                    "customers.fax, customers.email, orders.name, orders.order_date, orders.shipped_date, orders.notes, " +
                    "products.name, products.decimal_number, products.amount, products.priority, products.model_link, " +
                    "products.model_icon " +
                    "FROM products " +
                    "LEFT JOIN orders ON products.order_id=orders.id " +
                    "LEFT JOIN customers ON orders.customer_id=customers.id";
    public static final String PRODUCT_ID = "SELECT products.id FROM products WHERE products.decimal_number=?";
    public static final String PRODUCT_FIND_BY_ID =
            "SELECT products.id, orders.id, orders.name, customers.id, customers.name, orders.shipped_date, " +
                    "products.name, products.decimal_number, products.amount, products.priority, " +
                    "products.model_link, products.model_icon " +
                    "FROM products " +
                    "LEFT JOIN orders ON products.order_id=orders.id " +
                    "LEFT JOIN customers ON orders.customer_id=customers.id " +
                    "WHERE products.id=?";
    public static final String PRODUCT_ROUTE_MAP_BY_ID =
            "SELECT route_maps.queue, processes.name, users.name, route_maps.start_time, route_maps.comment " +
                    "FROM route_maps " +
                    "LEFT JOIN processes ON route_maps.process_id=processes.id " +
                    "LEFT JOIN users ON route_maps.user_id=users.id " +
                    "WHERE route_maps.product_id=?";
    public static final String PRODUCT_UPDATE =
            "UPDATE products SET name=?, decimal_number=?, amount=?, priority=?, model_link=?, " +
                    "model_icon=?, order_id=(SELECT id FROM orders " +
                    "WHERE customer_id=(SELECT id FROM customers WHERE name=?) AND name=?) " +
                    "WHERE id=?";
    public static final String PRODUCT_UPDATE_ROUTE_MAP =
            "UPDATE route_maps SET user_id=(SELECT users.id FROM users WHERE users.name=?), start_time=?, comment=? " +
                    "WHERE product_id=? AND queue=?";
    public static final String PRODUCT_ADD =
            "INSERT INTO products (order_id, name, decimal_number, amount, priority) VALUES (?,?,?,?,?)";
    public static final String PRODUCT_ADD_ROUTE_MAP =
            "INSERT INTO products (order_id, name, decimal_number, amount, priority) VALUES (?,?,?,?,?)";

    private DAOQuery() {
    }
}
