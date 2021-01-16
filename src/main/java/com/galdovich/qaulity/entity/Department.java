package com.galdovich.qaulity.entity;

import java.util.Arrays;

/**
 * The enum Department.
 * Defines the production departments at the firm.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public enum Department {
    ETW("ИТР"),
    CPU("ЦЕХ, ЧПУ"),
    TCD("Отдел технического контроля"),
    PACKAGIN("Упаковка"),
    LAZER("Лазерная резка"),
    WATERJET("Гидроабразивная резка"),
    LOCKSMITH("Слесарный участок"),
    WELDING("Сварочный участок"),
    FLEXIBLE("Гибка");

    private String name;

    Department(String name) {
        this.name = name;
    }

    /**
     * Gets department name.
     *
     * @return department name
     */
    public String getName() {
        return name;
    }

    /**
     * Method returns enum constant by the name of department.
     *
     * @param department name of department
     * @return department enum constant
     */
    public static Department getDepartment(String department) {
        return Arrays.stream(Department.values())
                .filter(d -> d.getName().equals(department))
                .findAny().orElse(null);
    }

    /**
     * Method returns enum constant by the ordinal index.
     *
     * @param index index that corresponds to ordinal number
     * @return department enum constant
     */
    public static Department getUserDepartment(int index) {
        return Arrays.stream(Department.values()).filter(r -> r.ordinal() == index).findFirst().get();
    }
}
