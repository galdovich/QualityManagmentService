package com.galdovich.qulity;

import com.galdovich.qulity.service.validator.ProductValidator;
import com.galdovich.qulity.service.validator.UserValidator;

public class Test {
    public static void main(String[] args) {
        System.out.println(UserValidator.isLoginValid("gal%%%"));
    }
}
