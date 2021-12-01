package com.rentCar.entity.car;

import org.junit.Assert;
import org.junit.Test;

public class CarTypeTest {
    @Test
    public void findByKeyTest() {
        String expected = "SMALL";
        String accept = CarType.findByKey(2).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getBodyTypeId() {
        int expected = 6;
        int accept = CarType.LUXURY.getCarTypeId();
        Assert.assertEquals(expected, accept);
    }
}
