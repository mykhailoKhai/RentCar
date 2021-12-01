package com.rentCar.DAO;


import com.rentCar.DAO.impl.CarDAOImpl;
import com.rentCar.entity.car.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class CarDAOTest {

    private Car car;
//
//    @Test
//    public void createCarTest(){
//        CarDAO carDAO = new CarDAOImpl();
//
//        car = new Car();
//        car.setCarType(CarType.LUXURY.toString());
//        car.setBrand("Opel");
//        car.setModel("Astra");
//        car.setBodyType(BodyType.SEDAN.toString());
//        car.setColor(Color.BROWN.toString());
//        car.setYearCreation(2012);
//        car.setTransmissionType(TransmissionType.AUTOMATIC.toString());
//        car.setEnginePower(110);
//        car.setEngineSize(1.8);
//        car.setFuelType(FuelType.LPG.toString());
//        car.setPrice(13.5);
//        car.setIsActive(true);
//
//        boolean accept = carDAO.createCar(car);
//
//        Assert.assertTrue(accept);
//    }
//
//    @Test
//    public void getAllCarsTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        List<Car> cars = carDAO.getAllCars();
//        Car carTest = cars.get(0);
//
//        Assert.assertEquals(car.getCarType(), carTest.getCarType());
//        Assert.assertEquals(car.getBrand(), carTest.getBrand());
//        Assert.assertEquals(car.getModel(), carTest.getModel());
//        Assert.assertEquals(car.getBodyType(), carTest.getBodyType());
//        Assert.assertEquals(car.getColor(), carTest.getColor());
//        Assert.assertEquals(car.getYearCreation(), carTest.getYearCreation());
//        Assert.assertEquals(car.getTransmissionType(), carTest.getTransmissionType());
//        Assert.assertEquals(car.getEnginePower(), carTest.getEnginePower());
//        Assert.assertEquals(car.getEngineSize(), carTest.getEngineSize());
//        Assert.assertEquals(car.getFuelType(), carTest.getFuelType());
//        Assert.assertEquals(car.getPrice(), carTest.getPrice());
//        Assert.assertEquals(car.getIsActive(), carTest.getIsActive());
//
//        car = carTest;
//    }
//
//    @Test
//    public void getCarByIdTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        Car carById = carDAO.getCarById(car.getCarId());
//        Assert.assertEquals(car, carById);
//    }
//
//    @Test
//    public void getAllUniqueBrandTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        List<String> brandsCar = carDAO.getAllUniqueBrandsCar();
//        String brand = brandsCar.get(0);
//        Assert.assertEquals(car.getBrand(), brand);
//    }
//
//    @Test
//    public void updateCarTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        Car updateCar = car;
//        updateCar.setYearCreation(2007);
//        updateCar.setPrice(10.5);
//        updateCar.setColor(Color.BEIGE.toString());
//        updateCar.setIsActive(false);
//        carDAO.updateCar(updateCar.getCarId(), updateCar);
//
//        Car carById = carDAO.getCarById(updateCar.getCarId());
//
//        Assert.assertEquals(updateCar, carById);
//    }
//
//    @Test
//    public void deleteCarTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        carDAO.deleteCar(car.getCarId());
//        Car car1 = null;
//        car1 = carDAO.getCarById(car.getCarId());
//
//        Assert.assertNull(car1);
//    }

}
