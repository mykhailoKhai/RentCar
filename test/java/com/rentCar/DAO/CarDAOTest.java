package com.rentCar.DAO;


import com.rentCar.DAO.impl.CarDAOImpl;
import com.rentCar.entity.car.*;
import com.rentCar.utill.DBManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class CarDAOTest {
    private static final Logger logger = Logger.getLogger(CarDAOTest.class);
//
//    private static final String URL_FOR_TESTING = "jdbc:mysql://localhost:3306/rent_car_test?serverTimezone=UTC";
//
//    @BeforeClass
//    public static void beforeClassTest() {
//
//        Properties properties = new Properties();
//        try {
//            InputStream inputStream = OrderDAOTest.class.getClassLoader().getResourceAsStream("db.properties");
//            properties.load(inputStream);
//            String urlFromProperties = properties.getProperty("db.connection");
//
//
/////////////////////////  security  //////////////////////////////////////////////////////////////
//            if (!urlFromProperties.equals(URL_FOR_TESTING)) {
//                logger.error("Your url is not for testing");
//                logger.error("Your need this url : " + URL_FOR_TESTING);
//                System.exit(0);
//            }
//
//        } catch (IOException e) {
//            logger.error(e);
//        }
//
//        try (Connection con = DBManager.getInstance().getConnection();
//             Statement statement = con.createStatement()) {
//
//            statement.executeUpdate("DROP TABLE IF EXISTS orders;");
//            statement.executeUpdate("DROP TABLE IF EXISTS user;");
//            statement.executeUpdate("DROP TABLE IF EXISTS car;");
//
//            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS car(\n" +
//                    "car_id bigint auto_increment primary key ,\n" +
//                    "car_type int ,\n" +
//                    "brand varchar(55), \n" +
//                    "model varchar(55) ,\n" +
//                    "body_type int ,\n" +
//                    "color int ,\n" +
//                    "year_creation int ,\n" +
//                    "transmission_type int ,\n" +
//                    "engine_size double ,\n" +
//                    "engine_power int ,\n" +
//                    "fuel_type int ,\n" +
//                    "price double ,\n" +
//                    "is_active boolean);";
//
//            statement.executeUpdate(sqlCreateTable);
//        } catch (SQLException e) {
//            logger.error(e);
//        }
//        CarDAO carDAO = new CarDAOImpl();
//
//        Car car = new Car();
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
//        carDAO.createCar(car);
//    }
//
//    @Test
//    public void createCarTest(){
//        CarDAO carDAO = new CarDAOImpl();
//
//        Car car = new Car();
//        car.setCarType(CarType.LUXURY.toString());
//        car.setBrand("Audi");
//        car.setModel("A3");
//        car.setBodyType(BodyType.SEDAN.toString());
//        car.setColor(Color.BROWN.toString());
//        car.setYearCreation(2018);
//        car.setTransmissionType(TransmissionType.AUTOMATIC.toString());
//        car.setEnginePower(130);
//        car.setEngineSize(2.2);
//        car.setFuelType(FuelType.LPG.toString());
//        car.setPrice(15);
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
//        Assert.assertNotNull(carTest);
//    }
//
//    @Test
//    public void getCarByIdTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        List<Car> cars = carDAO.getAllCars();
//        Car carTest = cars.get(0);
//        Car carById = carDAO.getCarById(carTest.getCarId());
//        Assert.assertEquals(carTest, carById);
//    }
//
//    @Test
//    public void getAllUniqueBrandTest() {
//        CarDAO carDAO = new CarDAOImpl();
//        List<String> brandsCar = carDAO.getAllUniqueBrandsCar();
//        String brand = brandsCar.get(0);
//        List<Car> cars = carDAO.getAllCars();
//        Car carTest = cars.get(0);
//
//        Assert.assertEquals(carTest.getBrand(), brand);
//    }
//
//    @Test
//    public void updateCarTest() {
//        CarDAO carDAO = new CarDAOImpl();
//
//        List<Car> cars = carDAO.getAllCars();
//        Car carTest = cars.get(0);
//
//        Car updateCar = carTest;
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
//        List<Car> cars = carDAO.getAllCars();
//        Car carTest = cars.get(0);
//        Car carTest2 = cars.get(1);
//        carDAO.deleteCar(carTest.getCarId());
//        carDAO.deleteCar(carTest2.getCarId());
//        Assert.assertEquals(0, carDAO.getCarById(carTest.getCarId()).getCarId());
//    }

}
