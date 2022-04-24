package com.kuzin.entity.enums;

import static com.kuzin.entity.enums.ApplicationPermission.*;

import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**user role enum.*/
public enum ApplicationUserRole {
    GUEST(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ARTICLE_ALL, PERSON_ALL, UNITS_ALL)),
    SUPP(Sets.newHashSet(MATERIAL_ALL, PERSON_GET, ARTICLE_GET, UNITS_GET)),
    WORKER(Sets.newHashSet(PERSON_GET, ARTICLE_GET, UNITS_GET, REPAIR_ALL));


    private final Set<ApplicationPermission> permissions;

    ApplicationUserRole(Set<ApplicationPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuth() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
