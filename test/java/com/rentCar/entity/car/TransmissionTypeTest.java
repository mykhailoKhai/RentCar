package com.rentCar.entity.car;

import org.junit.Assert;
import org.junit.Test;

public class TransmissionTypeTest {
    @Test
    public void findByKeyTest() {
        String expected = "MANUAL";
        String accept = TransmissionType.findByKey(2).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getBodyTypeId() {
        int expected = 1;
        int accept = TransmissionType.AUTOMATIC.getTransmissionId();
        Assert.assertEquals(expected, accept);
    }
}
