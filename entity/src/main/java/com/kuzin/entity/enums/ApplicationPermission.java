package com.kuzin.entity.enums;

/**permission enum.*/
public enum ApplicationPermission {
    UNITS_ALL("units:all"),
    ARTICLE_ALL("article:all"),
    PERSON_ALL("person:all"),
    MATERIAL_ALL("material:all"),
    ARTICLE_GET("article:get"),
    PERSON_GET("person:get"),
    MATERIAL_GET("material:get"),
    UNITS_GET("units:get"),
    REPAIR_ALL("repair:all");


    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
