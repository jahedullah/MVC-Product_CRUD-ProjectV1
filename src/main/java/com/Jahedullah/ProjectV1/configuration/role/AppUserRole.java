package com.Jahedullah.ProjectV1.configuration.role;

import com.Jahedullah.ProjectV1.configuration.permissions.AppUserPermission;
import com.google.common.collect.Sets;

import java.util.Set;

import static com.Jahedullah.ProjectV1.configuration.permissions.AppUserPermission.PRODUCT_READ;
import static com.Jahedullah.ProjectV1.configuration.permissions.AppUserPermission.PRODUCT_WRITE;

public enum AppUserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(PRODUCT_READ,PRODUCT_WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions){
        this.permissions = permissions;
    }
}
