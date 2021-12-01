package com.rentCar.entity.order;

import com.rentCar.entity.car.BodyType;
import org.junit.Assert;
import org.junit.Test;

public class StatusTest {

    @Test
    public void findByKeyTest() {
        String expected = "REJECT_ORDER";
        String accept = Status.findById(3).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getStatusId() {
        int expected = 2;
        int accept = Status.ISSUE_ORDER.getStatusId();
        Assert.assertEquals(expected, accept);
    }
}
