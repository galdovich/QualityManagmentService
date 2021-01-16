package com.galdovich.qulity.service.validator;

import com.galdovich.qaulity.entity.Customer;
import com.galdovich.qaulity.entity.Order;
import com.galdovich.qaulity.entity.Priority;
import com.galdovich.qaulity.service.validator.ProductValidator;
import com.galdovich.qaulity.util.ParameterKey;
import com.galdovich.qaulity.util.UploadType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class ProductValidatorTest {

    @Test
    public void validateRequestDataTest() {
        Map<String, String> parameters = new HashMap<>();
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Полюс"));
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("ЛД-294"));
        parameters.put(ParameterKey.PRODUCT_NAME, "Втулка");
        parameters.put(ParameterKey.PRODUCT_DECIMAL, "em13.234.12");
        parameters.put(ParameterKey.CUSTOMER_NAME, "Полюс");
        parameters.put(ParameterKey.ORDER_NAME, "ЛД-294");
        parameters.put(ParameterKey.PRODUCT_AMOUNT, "123");
        parameters.put(ParameterKey.PRODUCT_PRIORITY, Priority.A.getName());
        assertTrue(ProductValidator.validateRequestData(parameters, customerList, orderList));
    }

    @Test
    public void validateUploadDataTest() {
        Map<UploadType, String> parameters = new HashMap<>();
        parameters.put(UploadType.MODEL, "model_link");
        parameters.put(UploadType.ICON, null);
        assertTrue(ProductValidator.validateRequestData(parameters));
    }
}