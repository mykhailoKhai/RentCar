package com.rentCar.entity.car;

import java.util.HashMap;
import java.util.Map;

public enum FuelType {
    PETROL(1), DIESEL(2), LPG(3), ELECTRICAL(4), HYBRID(5);

    public final int fuelTypeId;
    private static final Map<Integer, FuelType> map;

    FuelType(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    static {
        map = new HashMap<>();
        for (FuelType value: FuelType.values()) {
            map.put(value.fuelTypeId, value);
        }
    }

    public static FuelType findByKey(int fuelTypeId) {
        return map.get(fuelTypeId);
    }

    public int getFuelTypeId(){
        return fuelTypeId;
    }
}
