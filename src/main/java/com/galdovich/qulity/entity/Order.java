package com.galdovich.qulity.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * The class represents order entity.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class Order implements Serializable {
    private int id;
    private Customer customer;
    private String name;
    private Date orderDate;
    private Date shippedDate;
    private String notes;

    /**
     * Instantiates a new order.
     */
    public Order() {
    }

    /**
     * Instantiates a new order.
     *
     * @param name the order name
     */
    public Order(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new order.
     *
     * @param id          the order id
     * @param customer    the customer instance
     * @param name        the name
     * @param shippedDate the deadline of order
     */
    public Order(int id, String name, Customer customer, Date shippedDate) {
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.shippedDate = shippedDate;
    }

    /**
     * Instantiates a new order.
     *
     * @param customer    the customer instance
     * @param name        the name
     * @param orderDate   the date of ordering
     * @param shippedDate the deadline of order
     * @param notes       the order notes
     */
    public Order(Customer customer, String name, Date orderDate, Date shippedDate, String notes) {
        this.customer = customer;
        this.name = name;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.notes = notes;
    }

    /**
     * Instantiates a new order.
     *
     * @param id          the order id
     * @param customer    the customer instance
     * @param name        the name
     * @param orderDate   the date of ordering
     * @param shippedDate the deadline of order
     * @param notes       the order notes
     */
    public Order(int id, Customer customer, String name, Date orderDate, Date shippedDate, String notes) {
        this.id = id;
        this.customer = customer;
        this.name = name;
        this.orderDate = orderDate;
        this.shippedDate = shippedDate;
        this.notes = notes;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets customer id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets customer instance.
     *
     * @return the customer instance
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets customer.
     *
     * @param customer the customer instance
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets date of ordering.
     *
     * @return the order date
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets date of ordering.
     *
     * @param orderDate the date of ordering
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets shipped date.
     *
     * @return the shipped date
     */
    public Date getShippedDate() {
        return shippedDate;
    }

    /**
     * Sets shipped date.
     *
     * @param shippedDate the shipped date
     */
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    /**
     * Gets notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        if (name != null ? !name.equals(order.name) : order.name != null) return false;
        if (orderDate != null ? !orderDate.equals(order.orderDate) : order.orderDate != null) return false;
        if (shippedDate != null ? !shippedDate.equals(order.shippedDate) : order.shippedDate != null) return false;
        return notes != null ? notes.equals(order.notes) : order.notes == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (shippedDate != null ? shippedDate.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", customer=").append(customer);
        sb.append(", name='").append(name).append('\'');
        sb.append(", orderDate=").append(orderDate);
        sb.append(", shippedDate=").append(shippedDate);
        sb.append(", notes='").append(notes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
