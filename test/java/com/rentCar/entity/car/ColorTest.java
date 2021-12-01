package com.rentCar.entity.car;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest {
    @Test
    public void findByKeyTest() {
        String expected = "ORANGE";
        String accept = Color.findByKey(7).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getBodyTypeId() {
        int expected = 5;
        int accept = Color.RED.getColorId();
        Assert.assertEquals(expected, accept);
    }
}
