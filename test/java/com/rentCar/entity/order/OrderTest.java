package com.rentCar.entity.order;

import com.rentCar.entity.car.Car;
import com.rentCar.entity.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class OrderTest {
    @Test
    public void compareOrderTest() {
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setRentStart(new Date(2021,12,5));
        order1.setRentEnd(new Date(2021,12,8));
        order1.setNeedDriver(true);
        order1.setTotalCost(105.5);
        order1.setStatusOrder(Status.REJECT_ORDER.toString());
        order1.setStatusMessage("You bad boy");
        order1.setDamagePaid(0.0);
        order1.setIsPaidDamage(false);
        order1.setDocumentSeries("KK");
        order1.setDocumentNum(987654);
        order1.setDateIssue(new Date(2020,5,15));
        order1.setAuthority("Sambir TRSK");
        order1.setUser(new User());
        order1.setCar(new Car());

        Order order2 = new Order();
        order2.setOrderId(1);
        order2.setRentStart(new Date(2021,12,5));
        order2.setRentEnd(new Date(2021,12,8));
        order2.setNeedDriver(true);
        order2.setTotalCost(105.5);
        order2.setStatusOrder(Status.REJECT_ORDER.toString());
        order2.setStatusMessage("You bad boy");
        order2.setDamagePaid(0.0);
        order2.setIsPaidDamage(false);
        order2.setDocumentSeries("KK");
        order2.setDocumentNum(987654);
        order2.setDateIssue(new Date(2020,5,15));
        order2.setAuthority("Sambir TRSK");
        order2.setUser(new User());
        order2.setCar(new Car());

        Assert.assertEquals(order1, order2);
    }
}
