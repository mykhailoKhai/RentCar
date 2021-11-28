package com.rentCar.entity.car;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    BLACK(1), BLUE(2), GREEN(3), BEIGE(4), RED(5), VIOLET(6), ORANGE(7), YELLOW(8), WHITE(9), BROWN(10), GREY(11);

    public final int colorId;
    private static final Map<Integer, Color> map;

    static {
        map = new HashMap<>();
        for (Color value: Color.values()) {
            map.put(value.colorId, value);
        }
    }

    Color (int colorId) {
        this.colorId = colorId;
    }

    public static Color findByKey(int colorId) {
        return map.get(colorId);
    }

    public int getColorId(){
        return colorId;
    }
}
