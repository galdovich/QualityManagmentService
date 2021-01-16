package com.galdovich.qulity.util;

/**
 * The enum upload type.
 * <p>
 * Defines the upload type.
 *
 * @author Galdovich Alexander
 * @version 1.0
 */
public enum UploadType {
    /**
     * The name of product icon
     */
    ICON("icon_name"),
    /**
     * The name of 3d product model name
     */
    MODEL("model_name");

    private String name;

    UploadType(String name) {
        this.name = name;
    }

    /**
     * Gets upload type name.
     *
     * @return upload type name
     */
    public String getName() {
        return name;
    }
}
