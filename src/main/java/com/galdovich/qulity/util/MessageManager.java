package com.galdovich.qulity.util;

import java.util.ResourceBundle;

/**
 * The class represents message manager.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public class MessageManager {
    private static final String DEFAULT_BUNDLE_NAME = "text";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME);

    private MessageManager() {
    }

    /**
     * Gets text.
     *
     * @param key the key
     * @return the text
     */
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
