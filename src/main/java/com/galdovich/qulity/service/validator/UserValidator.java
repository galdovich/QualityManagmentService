package com.galdovich.qulity.service.validator;

import com.galdovich.qulity.entity.Department;

import java.util.Map;
import java.util.regex.Pattern;

import static com.galdovich.qulity.util.ParameterKey.*;

/**
 * The class represents user validator.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class UserValidator {
    /**
     * Letters, numbers, hyphens, and underscores, from 4 to 16 characters.
     */
    private static final String LOGIN_REGEX = "^[a-z0-9_-]{4,16}$";
    /**
     * The name consists of the first name, last name and patronymic in Cyrillic.
     */
    private static final String NAME_REGEX = "([А-ЯЁ][а-яё]+[\\-\\s]?){3,}";
    /**
     * The password consists of letters and numbers from 6 to 18 characters.
     */
    private static final String PASSWORD_REGEX = "^[a-z0-9_-]{6,18}$";
    /**
     * Subdomain consists of letters, numbers, underscores, hyphens and periods.
     * The domain consists of 2 to 6 letters and dots.
     */
    private static final String EMAIL_REGEX = "^\\p{Alnum}+[._-]?\\p{Alnum}+@\\p{Alnum}+\\.\\p{Alpha}{2,4}$";

    /**
     * Check requestData data is correct.
     *
     * @param requestData the requestData data
     * @return true if requestData data is correct, otherwise false
     */
    public static boolean validateRequestData(Map<String, String> requestData) {
        boolean result = true;
        if (!isNameValid(requestData.get(NAME))) {
            requestData.put(NAME, EMPTY_VALUE);
            result = false;
        }
        if (!isLoginValid(requestData.get(LOGIN))) {
            requestData.put(LOGIN, EMPTY_VALUE);
            result = false;
        }
        if (!isPasswordValid(requestData.get(PASSWORD), requestData.get(PASSWORD_AGAIN))) {
            requestData.put(PASSWORD, EMPTY_VALUE);
            requestData.put(PASSWORD_AGAIN, EMPTY_VALUE);
            result = false;
        }
        if (!isEmailValid(requestData.get(EMAIL))) {
            requestData.put(EMAIL, EMPTY_VALUE);
            result = false;
        }
        if (!isDepartmentValid(requestData.get(DEPARTMENT))) {
            requestData.put(EMAIL, EMPTY_VALUE);
            result = false;
        }
        return result;
    }

    /**
     * Check editData data is correct.
     *
     * @param editData the editData data
     * @return true if editData data is correct, otherwise false
     */
    public static boolean validateEditDataMap(Map<String, String> editData) {
        boolean result = true;
        if (!isNameValid(editData.get(NAME))) {
            editData.put("name_message", "Invalid name!");
            result = false;
        }
        if (!isEmailValid(editData.get(EMAIL))) {
            editData.put("email_message", "Invalid email!");
            result = false;
        }
        if (!isDepartmentValid(editData.get(DEPARTMENT))) {
            result = false;
        }
        return result;
    }

    /**
     * Check name is correct.
     *
     * @param name the name
     * @return true if name is correct, otherwise false
     */
    public static boolean isNameValid(String name) {
        boolean result = false;
        if (name != null && !name.isEmpty()) {
            result = name.matches(NAME_REGEX);
        }
        return result;

    }

    /**
     * Check login is correct.
     *
     * @param login the login
     * @return true if login is correct, otherwise false
     */
    public static boolean isLoginValid(String login) {
        boolean result = false;
        if (login != null && !login.isEmpty()) {
            result = Pattern.matches(LOGIN_REGEX, login);
        }
        return result;
    }

    /**
     * Check password is correct.
     *
     * @param password      the password
     * @param passwordAgain the repeated password
     * @return true if password is correct, otherwise false
     */
    public static boolean isPasswordValid(String password, String passwordAgain) {
        boolean result = false;
        if (password != null && passwordAgain != null) {
            if (!password.isEmpty() && password.equals(passwordAgain)) {
                result = password.matches(PASSWORD_REGEX);
            }
        }
        return result;
    }

    /**
     * Check email is correct.
     *
     * @param email the email
     * @return true if email is correct, otherwise false
     */
    public static boolean isEmailValid(String email) {
        boolean result = false;
        if (email != null && !email.isEmpty()) {
            result = email.matches(EMAIL_REGEX);
        }
        return result;
    }

    /**
     * Check department is correct.
     *
     * @param department the department
     * @return true if department is correct, otherwise false
     */
    public static boolean isDepartmentValid(String department) {
        boolean result = false;
        if ((department != null && !department.isEmpty()) &&
                (Department.getDepartment(department) != null)) {
            result = true;
        }
        return result;
    }
}
