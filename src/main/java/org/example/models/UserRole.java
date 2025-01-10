package org.example.models;

import java.util.Map;

public class UserRole {
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    int roleId;

    private static final Map<Integer, String> ROLES_MAP = Map.of(
            1, ADMIN,
            2, USER
    );

    public UserRole(final int roleId) {
        setRoleId(roleId);
    }

    public String getRoleName() {
        return ROLES_MAP.get(getRoleId());
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }
}
