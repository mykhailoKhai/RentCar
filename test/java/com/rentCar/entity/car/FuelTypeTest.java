package com.rentCar.entity.car;

import org.junit.Assert;
import org.junit.Test;

public class FuelTypeTest {
    @Test
    public void findByKeyTest() {
        String expected = "HYBRID";
        String accept = FuelType.findByKey(5).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getBodyTypeId() {
        int expected = 4;
        int accept = FuelType.ELECTRICAL.getFuelTypeId();
        Assert.assertEquals(expected, accept);
    }
}
