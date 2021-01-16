package com.galdovich.qulity.service.validator;

import com.galdovich.qulity.entity.Customer;
import com.galdovich.qulity.entity.Order;
import com.galdovich.qulity.entity.Priority;
import com.galdovich.qulity.util.UploadType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.galdovich.qulity.util.ParameterKey.*;

/**
 * The class represents product validator.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class ProductValidator {
    /**
     * Letters, numbers, hyphens, and underscores, from 3 to 20 characters.
     */
    private static final String NAME_REGEX = "^[\\p{L}\\p{N} _-]{3,20}$";
    /**
     * Three-digit number.
     */
    private static final String AMOUNT_REGEX = "^[1-9]\\p{N}{0,3}";
    private static final String NULL_MESSAGE = "product.message.empty";

    /**
     * Check requestData data is correct.
     *
     * @param requestData  the requestData data
     * @param customerList the list of customers
     * @param orderList    the list of customers
     * @return true if requestData data is correct, otherwise false
     */
    public static boolean validateRequestData(Map<String, String> requestData, List<Customer> customerList, List<Order> orderList) {
        boolean result = true;
        if (isMapContainsNull(requestData)) {
            requestData.put(PRODUCT_MESSAGE, NULL_MESSAGE);
            return false;
        }
        if (!isNameValid(requestData.get(PRODUCT_NAME))) {
            requestData.put(PRODUCT_NAME, EMPTY_VALUE);
            result = false;
        }
        if (!isDecimalValid(requestData.get(PRODUCT_DECIMAL))) {
            requestData.put(PRODUCT_DECIMAL, EMPTY_VALUE);
            result = false;
        }
        if (!isCustomerValid(customerList, requestData.get(CUSTOMER_NAME))) {
            requestData.put(CUSTOMER_NAME, EMPTY_VALUE);
            result = false;
        }
        if (!isOrderValid(orderList, requestData.get(ORDER_NAME))) {
            requestData.put(ORDER_NAME, EMPTY_VALUE);
            result = false;
        }
        if (!isAmountValid(requestData.get(PRODUCT_AMOUNT))) {
            requestData.put(PRODUCT_AMOUNT, EMPTY_VALUE);
            result = false;
        }
        if (!isPriorityValid(requestData.get(PRODUCT_PRIORITY))) {
            requestData.put(PRODUCT_PRIORITY, EMPTY_VALUE);
            result = false;
        }
        return result;
    }

    /**
     * Check requestData data is correct. Used for upload servlet.
     *
     * @param requestData the requestData data
     * @return true if requestData data is correct, otherwise false
     */
    public static boolean validateRequestData(Map<UploadType, String> requestData) {
        boolean result = true;
        String icon = requestData.get(UploadType.ICON);
        String model = requestData.get(UploadType.MODEL);

        // icon.equals(null) - проверка на случай, если одновременно загружается два поля
        //icon == null - если отключена блокировка
        if ((icon == null || icon.equals(null)) && (model == null || model.equals(null))) {
            result = false;
        }
        return result;
    }

    /**
     * Check full name of product is correct.
     *
     * @param fullName the full name of product
     * @return true if name of product is correct, otherwise false
     */
    // TODO: 18.11.2020
    public static boolean isCorrectFullName(String fullName) {
        return fullName != null && !fullName.isEmpty();
    }

    /**
     * Check name of product is correct.
     *
     * @param name the name of product
     * @return true if name of product is correct, otherwise false
     */
    private static boolean isNameValid(String name) {
        return name.matches(NAME_REGEX);
    }

    /**
     * Check decimal Number is correct.
     *
     * @param decimalNumber the decimal Number
     * @return true if decimal Number is correct, otherwise false
     */
    private static boolean isDecimalValid(String decimalNumber) {
        return decimalNumber.length() > 3 && decimalNumber.length() < 30;
    }

    /**
     * Check customer is correct.
     *
     * @param customerList the list of customer
     * @param name         the customer amount
     * @return true if customer is correct, otherwise false
     */
    private static boolean isCustomerValid(List<Customer> customerList, String name) {
        return customerList.stream().anyMatch(c -> c.getName().equals(name));
    }

    /**
     * Check order is correct.
     *
     * @param orderList the order list
     * @param name      the order name
     * @return true if order is correct, otherwise false
     */
    private static boolean isOrderValid(List<Order> orderList, String name) {
        return orderList.stream().anyMatch(o -> o.getName().equals(name));
    }

    /**
     * Check amount is correct.
     *
     * @param amount the amount
     * @return true if amount is correct, otherwise false
     */
    private static boolean isAmountValid(String amount) {
        return amount.matches(AMOUNT_REGEX);
    }

    /**
     * Check priority is correct.
     *
     * @param priority the priority
     * @return true if priority is correct, otherwise false
     */
    private static boolean isPriorityValid(String priority) {
        return Arrays.stream(Priority.values()).anyMatch(p -> p.getName().equals(priority));
    }

    /**
     * Check dataMap is correct.
     *
     * @param dataMap the dataMap
     * @return true if dataMap is correct, otherwise false
     */
    private static boolean isMapContainsNull(Map<String, String> dataMap) {
        return dataMap.entrySet().stream().filter(p -> p.getValue() == null).count() > 0 ? true : false;
    }
}
