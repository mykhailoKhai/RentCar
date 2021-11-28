package com.rentCar.entity.car;

import java.util.HashMap;
import java.util.Map;

public enum BodyType {
    SEDAN(1), HATCHBACK(2), WAGON(3), SUV(4), CROSSOVER(5), MPV(6), VAN(7), COUPE(8), LIMOUSINE(9), ROADSTER(10), PICKUP(11);

    public final int bodyTypeId;
    private static final Map<Integer, BodyType> map;

    static {
        map = new HashMap<>();
        for (BodyType value: BodyType.values()) {
            map.put(value.bodyTypeId, value);
        }
    }

    BodyType(int bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public static BodyType findByKey(int key) {
        return map.get(key);
    }

    public int getBodyTypeId() {
        return bodyTypeId;
    }
}
