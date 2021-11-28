package com.rentCar.entity.user;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    CUSTOMER(1), MANAGER(2), ADMIN(3);

    public final int roleId;
    private static final Map<Integer, Role> map;

    static {
        map = new HashMap<>();
        for (Role value: Role.values()) {
            map.put(value.roleId, value);
        }
    }

    Role(int roleId) {
        this.roleId = roleId;
    }

    public static Role findById(int roleId) {
        return map.get(roleId);
    }

    public int getRoleId() {
        return roleId;
    }
}
