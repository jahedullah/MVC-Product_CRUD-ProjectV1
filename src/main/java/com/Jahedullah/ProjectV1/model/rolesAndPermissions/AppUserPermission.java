package com.Jahedullah.ProjectV1.model.rolesAndPermissions;

public enum AppUserPermission {
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}