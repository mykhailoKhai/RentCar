package com.rentCar.entity.car;

import org.junit.Assert;
import org.junit.Test;

public class CarTest {
    @Test
    public void compareCarsTest() {
        Car car1 = new Car();
        car1.setCarId(1);
        car1.setCarType(CarType.LUXURY.toString());
        car1.setBrand("Opel");
        car1.setBrand("Astra");
        car1.setBodyType(BodyType.SEDAN.toString());
        car1.setColor(Color.BROWN.toString());
        car1.setYearCreation(2012);
        car1.setTransmissionType(TransmissionType.AUTOMATIC.toString());
        car1.setEnginePower(110);
        car1.setEngineSize(1.8);
        car1.setFuelType(FuelType.LPG.toString());
        car1.setPrice(13.5);
        car1.setIsActive(true);

        Car car2 = new Car();
        car2.setCarId(1);
        car2.setCarType(CarType.LUXURY.toString());
        car2.setBrand("Opel");
        car2.setBrand("Astra");
        car2.setBodyType(BodyType.SEDAN.toString());
        car2.setColor(Color.BROWN.toString());
        car2.setYearCreation(2012);
        car2.setTransmissionType(TransmissionType.AUTOMATIC.toString());
        car2.setEnginePower(110);
        car2.setEngineSize(1.8);
        car2.setFuelType(FuelType.LPG.toString());
        car2.setPrice(13.5);
        car2.setIsActive(true);

        Assert.assertEquals(car1, car2);
    }
}
