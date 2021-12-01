package com.rentCar.entity.car;

import com.rentCar.entity.car.BodyType;
import org.junit.Assert;
import org.junit.Test;

public class BodyTypeTest {
    @Test
    public void findByKeyTest() {
        String expected = "WAGON";
        String accept = BodyType.findByKey(3).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getBodyTypeId() {
        int expected = 1;
        int accept = BodyType.SEDAN.getBodyTypeId();
        Assert.assertEquals(expected, accept);
    }
}
