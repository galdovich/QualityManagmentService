package com.galdovich.qaulity.util;

import java.util.ResourceBundle;

/**
 * The class represents database parameters.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle = ResourceBundle.getBundle("database");

    private DBResourceManager() {
    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    /**
     * Get string value from bundle.
     *
     * @param key the key
     */
    public String getValue(String key) {
        return bundle.getString(key);
    }
}
