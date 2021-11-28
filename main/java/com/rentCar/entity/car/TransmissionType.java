package com.rentCar.entity.car;

import java.util.HashMap;
import java.util.Map;

public enum TransmissionType {
    AUTOMATIC(1), MANUAL(2);

    public final int transmissionId;
    private static final Map<Integer, TransmissionType> map;

    static {
        map = new HashMap<>();
        for (TransmissionType value: TransmissionType.values()) {
            map.put(value.transmissionId, value);
        }
    }

    TransmissionType(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public static TransmissionType findByKey(int transmissionId) {
        return map.get(transmissionId);
    }

    public int getTransmissionId(){
        return transmissionId;
    }
}
