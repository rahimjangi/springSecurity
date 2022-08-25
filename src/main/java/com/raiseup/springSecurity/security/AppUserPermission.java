package com.raiseup.springSecurity.security;

public enum AppUserPermission {
    QUEST_READ("product:read"),
    QUEST_WRITE("product:write"),
    MANAGER_READ("product:read"),
    MANAGER_WRITE("product:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
