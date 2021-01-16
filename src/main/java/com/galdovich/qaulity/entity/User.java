package com.galdovich.qaulity.entity;

import java.io.Serializable;

/**
 * The class represents user entity.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class User implements Serializable {
    private int id;
    private String name;
    private String login;
    private String email;
    private Department department;
    private Role role;

    /**
     * Instantiates a new user.
     */
    public User() {
    }

    /**
     * Instantiates a new user.
     *
     * @param name the user name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Instantiates a new User.
     *
     * @param name       the user name
     * @param login      the user login
     * @param email      the user email
     * @param department the user department
     * @param role       the user role
     */
    public User(String name, String login, String email, Department department, Role role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.department = department;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param id         the user id
     * @param name       the user name
     * @param login      the user login
     * @param email      the user email
     * @param department the user department
     * @param role       the user role
     */
    public User(int id, String name, String login, String email, Department department, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.department = department;
        this.role = role;
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
     * Sets user id.
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
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
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

    /**
     * Gets department.
     *
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets department.
     *
     * @param department the department
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (department != user.department) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", department=").append(department);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
