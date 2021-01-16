package com.galdovich.qulity.service.validator;

import com.galdovich.qaulity.entity.Department;
import com.galdovich.qaulity.service.validator.UserValidator;
import com.galdovich.qaulity.util.ParameterKey;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserValidatorTest {
    @Test
    public void validateRequestParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ParameterKey.NAME, "Галдович Александр Николаевич");
        parameters.put(ParameterKey.LOGIN, "boroda12");
        parameters.put(ParameterKey.PASSWORD, "admin12");
        parameters.put(ParameterKey.PASSWORD_AGAIN, "admin12");
        parameters.put(ParameterKey.EMAIL, "sacha.alex@mail.ru");
        parameters.put(ParameterKey.DEPARTMENT, Department.TCD.getName());
        assertTrue(UserValidator.validateRequestData(parameters));
    }

    @DataProvider(name = "nameData")
    public Object[][] createNameData() {
        return new Object[][]{
                {"Галдович Александр Николаевич", true},
                {"Галдович Александр", false},
                {"Галдович", false},
                {"1234", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "nameData")
    public void validateNameTest(String name, boolean expected) {
        boolean actual = UserValidator.isNameValid(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "loginData")
    public Object[][] createLoginData() {
        return new Object[][]{
                {"admin_23", true},
                {"логин12", false},
                {"232421", true},
                {"132", false},
                {null, false},
                {"", false},
        };
    }

    @Test(dataProvider = "loginData")
    public void validateLoginTest(String login, boolean expected) {
        boolean actual = UserValidator.isLoginValid(login);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "emailData")
    public Object[][] createEmailData() {
        return new Object[][]{
                {"", false},
                {null, false},
                {"1234@mail.ru", true},
                {"loginf@google.com", true},
                {"er32w<>@pof.by", false},
                {"user@domashniy.kdoko", false},
        };
    }

    @Test(dataProvider = "emailData")
    public void validateEmailTest(String email, boolean expected) {
        boolean actual = UserValidator.isEmailValid(email);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "passwordsData")
    public Object[][] createPasswordsData() {
        return new Object[][]{
                {"admin12", "admin12", true},
                {"goodpass", "badpass", false},
                {"rokslf31w", "rokslf31w", true},
                {"admin", null, false},
                {null, "admin", false},
                {"admin", "", false},
                {"", "admin", false},
        };
    }

    @Test(dataProvider = "passwordsData")
    public void validatePasswordsTest(String password, String confrimPassword, boolean expected) {
        boolean actual = UserValidator.isPasswordValid(password, confrimPassword);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "departmentData")
    public Object[][] createDepartmentData() {
        return new Object[][]{
                {Department.CPU.getName(), true},
                {Department.FLEXIBLE.getName(), true},
                {"Сварочная", false},
                {"", false},
                {null, false},
        };
    }

    @Test(dataProvider = "departmentData")
    public void validateDepartmentTest(String department, boolean expected) {
        boolean actual = UserValidator.isDepartmentValid(department);
        assertEquals(actual, expected);
    }
}