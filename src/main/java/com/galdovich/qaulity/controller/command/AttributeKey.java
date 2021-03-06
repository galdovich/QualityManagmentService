package com.galdovich.qaulity.controller.command;

/**
 * The class is used to store the names of the attributes placed in the {@code HttpSession}
 * or in the {@code HttpServletRequest} when processing the request.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class AttributeKey {
    public static final String CURRENT_PAGE = "current_page";
    public static final String LOCALE = "locale";
    public static final String BUNDLE_LOCALE = "text";

    public static final String USER_ID = "userId";
    public static final String USER = "user";
    public static final String USER_NAME = "user_name";
    public static final String USER_LOGIN = "user_login";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_DEPARTMENT = "user_department";
    public static final String USER_ROLE = "user_role";
    public static final String USER_LIST = "user_list";
    public static final String USER_MESSAGE = "user_message";
    public static final String USER_SAME_LOGIN_MESSAGE = "same_login_message";
    public static final String USER_EDIT_MAP = "editData";
    public static final String USER_AMOUNT = "user_amount";
    public static final String USER_SUCCESS_ADD = "user_success_flag";
    public static final String USER_FAIL_ADD = "user_fail_add";
    public static final String USER_SAME_LOGIN = "user_same_login";
    public static final String LOGIN_ERROR_TAG = "errorLoginPassMessage";
    public static final String PROFILE_VALID_MESSAGE = "validMessage";
    public static final String PROFILE_INVALID_MESSAGE = "invalidMessage";
    public static final String REGISTER_SUCCESS_TAG = "registerPassMessage";
    public static final String PRODUCT_ERROR_TAG = "productErrorMessage";
    public static final String REGISTER_DATA_MAP = "register_data";
    public static final String IS_AUTHORIZED = "isAuthorized";

    public static final String CUSTOMER_NAME = "customer_name";
    public static final String CUSTOMER_LIST = "customer_list";

    public static final String ORDER_NAME = "order_name";
    public static final String ORDER_LIST = "orders_list";

    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_PAGE = "product_page";
    public static final String PRODUCT_MODEL_LINK = "product_model_link";
    public static final String PRODUCT_MODEL_ICON = "product_model_icon";
    public static final String PRODUCT_AMOUNT = "product_amount";
    public static final String PRODUCT_STATE = "product_state";
    public static final String PRODUCT_LIST = "product_list";
    public static final String PRODUCTION_MAP = "production_map";
    public static final String PRODUCT_MESSAGE = "message";
    public static final String PRODUCT_ADD_FLAG = "add_flag";
    public static final String PRODUCT_DATA_MAP = "dataMap";
    public static final String PRODUCT_PRIORITY = "product_priority";
    public static final String PRODUCT_FAIL_ADDING = "fail_adding";
    public static final String PRODUCT_SAME_VALUES = "same_values";
    public static final String PRODUCT_FAIL_MESSAGE = "fail_message";
    public static final String PRODUCT_SHIPPED_DATE = "product_shipped_date";
    public static final String PRODUCT_DECIMAL_NUMBER = "product_decimal_number";
    public static final String PRODUCT_SUCCESS_ADDING = "success_adding";

    public static final String UPLOAD_TYPE = "upload_type";
    public static final String ICON_UPLOAD = "icon_name";
    public static final String MODEL_UPLOAD = "model_name";

    private AttributeKey() {
    }
}
