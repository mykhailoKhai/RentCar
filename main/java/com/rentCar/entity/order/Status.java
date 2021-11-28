package com.rentCar.entity.order;

import java.util.HashMap;
import java.util.Map;

public enum Status {
     NEW_ORDER(1), ISSUE_ORDER(2), REJECT_ORDER(3), CLOSE_ORDER(4);

    public final int statusId;
    private static final Map<Integer, Status> map;

    static {
        map = new HashMap<>();
        for (Status value: Status.values()) {
            map.put(value.statusId, value);
        }
    }

    Status(int statusId) {
        this.statusId = statusId;
    }

    public static Status findById(int statusId) {
        return map.get(statusId);
    }

    public int getStatusId() {
        return statusId;
    }
}
