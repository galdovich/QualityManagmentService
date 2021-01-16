package com.galdovich.qaulity.entity;

import java.io.Serializable;

/**
 * The class represents customer entity.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class Customer implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String fax;
    private String email;

    /**
     * Instantiates a new customer.
     */
    public Customer() {
    }

    /**
     * Instantiates a new customer.
     *
     * @param id   the customer id
     * @param name the name
     */
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Instantiates a new customer.
     *
     * @param name    the name
     * @param address the address
     * @param phone   the phone
     * @param fax     the fax
     * @param email   the email
     */
    public Customer(String name, String address, String phone, String fax, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    /**
     * Instantiates a new customer.
     *
     * @param id      the customer id
     * @param name    the name
     * @param address the address
     * @param phone   the phone
     * @param fax     the fax
     * @param email   the email
     */
    public Customer(int id, String name, String address, String phone, String fax, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
    }

    /**
     * Gets customer id.
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets fax.
     *
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets fax.
     *
     * @param fax the fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (fax != null ? !fax.equals(customer.fax) : customer.fax != null) return false;
        return email != null ? email.equals(customer.email) : customer.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Customer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", fax='").append(fax).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
