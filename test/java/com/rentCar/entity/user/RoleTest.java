package com.rentCar.entity.user;

import com.rentCar.entity.order.Status;
import org.junit.Assert;
import org.junit.Test;

public class RoleTest {

    @Test
    public void findByKeyTest() {
        String expected = "MANAGER";
        String accept = Role.findById(2).toString();
        Assert.assertEquals(expected, accept);
    }

    @Test
    public void getStatusId() {
        int expected = 1;
        int accept = Role.CUSTOMER.getRoleId();
        Assert.assertEquals(expected, accept);
    }
}
