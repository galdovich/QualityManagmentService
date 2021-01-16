package com.galdovich.qulity.util;

/**
 * The enum Page manager.
 * <p>
 * An enumeration that describes all pages used by the application to
 * provide forwarding as a result of request processing
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public enum PageManager {

    INDEX("/index.jsp"),
    MAIN("/jsp/welcome.jsp"),
    LOGIN("/jsp/login.jsp"),
    REGISTER("/jsp/register.jsp"),
    ERROR("/error"),
    CLIENT_MENU("/jsp/client_menu.jsp"),
    ADMIN_MENU("/jsp/admin_menu.jsp"),
    ADMIN_PATH("/jsp/path/admin_menu_path.jsp"),
    PROFILE("/jsp/user_profile.jsp"),
    PRODUCT("/jsp/product.jsp"),
    UPLOAD("/jsp/path/upload_product_path.jsp"),
    USER_MENU("/WEB-INF/jsp/user_menu.jsp");

    private String path;

    PageManager(String path) {
        this.path = path;
    }

    /**
     * Returns the path of the page
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }
}
