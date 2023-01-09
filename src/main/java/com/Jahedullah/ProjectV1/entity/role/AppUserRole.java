package com.Jahedullah.ProjectV1.entity.role;

import com.Jahedullah.ProjectV1.entity.permissions.AppUserPermission;
import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;
import static com.Jahedullah.ProjectV1.entity.permissions.AppUserPermission.PRODUCT_READ;
import static com.Jahedullah.ProjectV1.entity.permissions.AppUserPermission.PRODUCT_WRITE;

public enum AppUserRole {
    USER(Sets.newHashSet(PRODUCT_READ)), ADMIN(Sets.newHashSet(PRODUCT_READ, PRODUCT_WRITE));

    private final Set<AppUserPermission> permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
