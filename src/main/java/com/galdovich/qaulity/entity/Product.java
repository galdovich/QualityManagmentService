package com.galdovich.qaulity.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * The class represents product entity.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class Product implements Serializable {
    private int id;
    private Order order;
    private String name;
    private String decimalNumber;
    private int amount;
    private Priority priority;
    private String modelLink;
    private String modelIcon;
    private Map<RouteMap, Production> productionMap;
    private double progress;

    /**
     * Instantiates a new product.
     */
    public Product() {
    }

    /**
     * Instantiates a new product.
     *
     * @param id            the product id
     * @param order         the order instance
     * @param name          the product name
     * @param decimalNumber the product decimal number
     * @param amount        the product amount
     * @param priority      the product priority
     * @param modelLink     the product model link
     * @param modelIcon     the product model icon
     * @param productionMap the production map instance
     */
    public Product(int id, Order order, String name, String decimalNumber, int amount, Priority priority,
                   String modelLink, String modelIcon, Map<RouteMap, Production> productionMap) {
        this.id = id;
        this.order = order;
        this.name = name;
        this.decimalNumber = decimalNumber;
        this.amount = amount;
        this.priority = priority;
        this.modelLink = modelLink;
        this.modelIcon = modelIcon;
        this.productionMap = productionMap;
    }

    /**
     * Instantiates a new product.
     *
     * @param order         the order instance
     * @param name          the product name
     * @param decimalNumber the product decimal number
     * @param amount        the product amount
     * @param priority      the product priority
     * @param modelLink     the product model link
     * @param modelIcon     the product model icon
     * @param productionMap the production map instance
     */
    public Product(Order order, String name, String decimalNumber, int amount, Priority priority, String modelLink,
                   String modelIcon, Map<RouteMap, Production> productionMap) {
        this.order = order;
        this.name = name;
        this.decimalNumber = decimalNumber;
        this.amount = amount;
        this.priority = priority;
        this.modelLink = modelLink;
        this.modelIcon = modelIcon;
        this.productionMap = productionMap;
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
     * Sets id
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets order
     *
     * @param order the order
     */
    public void setOrder(Order order) {
        this.order = order;
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
     * Gets full name that consist of name and decimal number.
     *
     * @return the full name
     */
    public String getFullName() {
        return new StringBuilder(name).append(" ").append(decimalNumber).toString();
    }

    /**
     * Sets name
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets decimal number.
     *
     * @return the decimal number
     */
    public String getDecimal_number() {
        return decimalNumber;
    }

    /**
     * Sets decimal number
     *
     * @param decimalNumber the decimal number
     */
    public void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Sets priority
     *
     * @param priority the priority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Gets model Link.
     *
     * @return the model Link
     */
    public String getModelLink() {
        return modelLink;
    }

    /**
     * Sets madel Link
     *
     * @param madelLink the madel Link
     */
    public void setModelLink(String madelLink) {
        this.modelLink = madelLink;
    }

    /**
     * Gets model Icon.
     *
     * @return the model Icon
     */
    public String getModelIcon() {
        return modelIcon;
    }

    /**
     * Sets model Icon
     *
     * @param modelIcon the model Icon
     */
    public void setModelIcon(String modelIcon) {
        this.modelIcon = modelIcon;
    }

    /**
     * Gets production Map
     *
     * @return the production Map
     */
    public Map<RouteMap, Production> getProductionMap() {
        return productionMap;
    }

    /**
     * Sets production Map
     *
     * @param productionMap the production Map
     */
    public void setProductionMap(Map<RouteMap, Production> productionMap) {
        this.productionMap = productionMap;
    }

    /**
     * Gets progress
     *
     * @return the progress
     */
    public double getProgress() {
        return progress;
    }

    /**
     * Sets progress
     *
     * @param progress the progress
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (amount != product.amount) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return decimalNumber != null ? decimalNumber.equals(product.decimalNumber) : product.decimalNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (decimalNumber != null ? decimalNumber.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (modelLink != null ? modelLink.hashCode() : 0);
        result = 31 * result + (modelIcon != null ? modelIcon.hashCode() : 0);
        result = 31 * result + (productionMap != null ? productionMap.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", order=").append(order);
        sb.append(", name='").append(name).append('\'');
        sb.append(", decimal_number='").append(decimalNumber).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", priority=").append(priority);
        sb.append(", modelLink='").append(modelLink).append('\'');
        sb.append(", modelIcon='").append(modelIcon).append('\'');
        sb.append(", productionMap=").append(productionMap);
        sb.append('}');
        return sb.toString();
    }
}
