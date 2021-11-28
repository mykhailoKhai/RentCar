package com.rentCar.entity.car;

import java.util.HashMap;
import java.util.Map;

public enum CarType {
    MINI(1), SMALL(2), MEDIUM(3), LARGE(4), EXECUTIVE(5), LUXURY(6), SUV(7), MPV(8), SPORT(9);

    public final int carTypeId;
    private static final Map<Integer, CarType> map;

    static {
        map = new HashMap<>();
        for (CarType value: CarType.values()) {
            map.put(value.carTypeId, value);
        }
    }

    CarType (int carTypeId) {
        this.carTypeId = carTypeId;
    }

    public static CarType findByKey(int carTypeId) {
        return map.get(carTypeId);
    }

    public int getCarTypeId(){
        return carTypeId;
    }
}
