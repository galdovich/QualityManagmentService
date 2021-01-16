package com.galdovich.qulity.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class represents production entity.
 * <p>
 * It is class containing the time at which the user performed an operation on the product.
 * Production is an integral element of production Map.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class Production implements Serializable {
    /**
     * User performing unit of operation.
     */
    private User user;
    /**
     * Time of performed operation.
     */
    private Date date;
    private String comment;

    /**
     * Instantiates a new production.
     */
    public Production() {
    }

    /**
     * Instantiates a new production.
     *
     * @param user    the user performing unit of operation
     * @param date    time of performed operation
     * @param comment the comment
     */
    public Production(User user, Date date, String comment) {
        this.user = user;
        this.date = date;
        this.comment = comment;
    }

    /**
     * Instantiates a new production.
     *
     * @param user the user performing unit of operation
     * @param date time of performed operation
     */
    public Production(User user, Date date) {
        this.user = user;
        this.date = date;
    }

    /**
     * Gets user instance.
     *
     * @return the user instance
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user instance.
     *
     * @param user the user instance
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Production{");
        sb.append("user=").append(user);
        sb.append(", date=").append(date);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
