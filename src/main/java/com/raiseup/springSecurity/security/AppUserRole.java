package com.raiseup.springSecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.raiseup.springSecurity.security.AppUserPermission.*;

public enum AppUserRole {
    GUEST(Sets.newHashSet()),
    MANAGER(Sets.newHashSet(
            MANAGER_READ,
            MANAGER_WRITE
    ))
    ,
    CUSTOMUSER(Sets.newHashSet(
            MANAGER_READ,
            QUEST_READ
    ))
    ;

    private final Set<AppUserPermission>permissions;

    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority>getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permissions;
    }
}
