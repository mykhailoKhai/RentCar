package com.rentCar.DAO;

import com.rentCar.DAO.impl.CarDAOImpl;
import com.rentCar.DAO.impl.OrderDAOImpl;
import com.rentCar.DAO.impl.UserDaoImpl;
import com.rentCar.entity.car.*;
import com.rentCar.entity.order.Order;
import com.rentCar.entity.order.Status;
import com.rentCar.entity.user.Role;
import com.rentCar.entity.user.User;
import com.rentCar.utill.DBManager;
import com.rentCar.utill.MD5Util;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class OrderDAOTest {
    private static final Logger logger = Logger.getLogger(OrderDAOTest.class);

//    private static final String URL_FOR_TESTING = "jdbc:mysql://localhost:3306/rent_car_test?serverTimezone=UTC";
//
//    @BeforeClass
//    public static void beforeClassTest() {
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
//            String sqlCarCreateTable = "CREATE TABLE IF NOT EXISTS car(\n" +
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
//            statement.executeUpdate(sqlCarCreateTable);
//
//            String sqlUserCreateTable = "CREATE TABLE IF NOT EXISTS user(\n" +
//                    "user_id BIGINT auto_increment primary key,\n" +
//                    "login varchar(55) NOT NULL,\n" +
//                    "password varchar(55) NOT NULL, \n" +
//                    "last_name varchar(55) NOT NULL, \n" +
//                    "first_name varchar(55) NOT NULL, \n" +
//                    "phone_num bigint ,\n" +
//                    "email varchar(55) ,\n" +
//                    "role int NOT NULL,\n" +
//                    "is_active BOOLEAN NOT NULL,\n" +
//                    "account double ,\n" +
//                    "document_series varchar(55) ,\n" +
//                    "document_num bigint ,\n" +
//                    "document_issue date ,\n" +
//                    "document_authority varchar(127));";
//
//            statement.executeUpdate(sqlUserCreateTable);
//
//            String sqlOrderCreateTable = "CREATE TABLE IF NOT EXISTS orders (\n" +
//                    "   order_id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,\n" +
//                    "    rent_start DATE NOT NULL,\n" +
//                    "    rent_end DATE NOT NULL,\n" +
//                    "    need_driver BOOLEAN NOT NULL,\n" +
//                    "    total_cost DOUBLE NOT NULL,\n" +
//                    "    status_order INT,\n" +
//                    "    status_message TEXT,\n" +
//                    "    damage_paid DOUBLE,\n" +
//                    "    is_paid_damage_cost BOOLEAN,\n" +
//                    "    document_series VARCHAR(3),\n" +
//                    "    document_num BIGINT,\n" +
//                    "    date_issue DATE,\n" +
//                    "    authority VARCHAR(256),\n" +
//                    "    user_id BIGINT NOT NULL,\n" +
//                    "    car_id BIGINT NOT NULL,\n" +
//                    "    FOREIGN KEY(user_id) REFERENCES user(user_id),\n" +
//                    "    FOREIGN KEY(car_id) REFERENCES car(car_id));";
//
//            statement.executeUpdate(sqlOrderCreateTable);
//
//        } catch (SQLException e) {
//            logger.error(e);
//        }
//
//        try {
//            CarDAO carDAO = new CarDAOImpl();
//            Car car = new Car();
//            car.setCarType(CarType.LUXURY.toString());
//            car.setBrand("Opel");
//            car.setModel("Astra");
//            car.setBodyType(BodyType.SEDAN.toString());
//            car.setColor(Color.BROWN.toString());
//            car.setYearCreation(2012);
//            car.setTransmissionType(TransmissionType.AUTOMATIC.toString());
//            car.setEnginePower(110);
//            car.setEngineSize(1.8);
//            car.setFuelType(FuelType.LPG.toString());
//            car.setPrice(10);
//            car.setIsActive(true);
//            carDAO.createCar(car);
//
//            UserDao userDao = new UserDaoImpl();
//            User user = new User();
//            user.setLogin("4444");
//            user.setPassword(MD5Util.codingToMD5("4444"));
//            user.setLastName("Khai");
//            user.setFirstName("Mike");
//            user.setPhoneNum(380989898998L);
//            user.setEmail("mail@gmail.com");
//            user.setRole(Role.CUSTOMER.toString());
//            user.setIsActive(true);
//            user.setAccount(10);
//            user.setDocumentSeries("KK");
//            user.setDocumentNum(983746L);
//            Date dateIssue = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-18");
//            user.setDateIssue(dateIssue);
//            user.setAuthority("Sambir ghkd");
//            userDao.createUser(user);
//
//            OrderDAO orderDAO = new OrderDAOImpl();
//            Order order = new Order();
//            Date startRent = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-20");
//            order.setRentStart(startRent);
//            Date endRent = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-25");
//            order.setRentEnd(endRent);
//            order.setNeedDriver(true);
//            order.setTotalCost(50);
//            order.setStatusOrder(Status.NEW_ORDER.toString());
//            order.setDamagePaid(0);
//            order.setIsPaidDamage(false);
//            order.setDocumentSeries("KK");
//            order.setDocumentNum(983746L);
//            order.setDateIssue(dateIssue);
//            order.setAuthority("Sambir ghkd");
//            order.setCar(carDAO.getAllCars().get(0));
//            order.setUser(userDao.getAllUsers().get(0));
//            orderDAO.createNewOrder(order);
//        } catch (ParseException e) {
//            logger.error(e);
//        }
//    }
//
//    @Test
//    public void getAllOrderTest() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        List<Order> allOrder = orderDAO.getAllOrder();
//        Assert.assertTrue(allOrder.get(0).getOrderId() != 0);
//    }
//
//    @Test
//    public void createNewOrderTest() {
//        boolean newOrder = false;
//        try {
//            OrderDAO orderDAO = new OrderDAOImpl();
//            Order order = new Order();
//            Date startRent = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-27");
//            order.setRentStart(startRent);
//            Date endRent = new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-29");
//            order.setRentEnd(endRent);
//            order.setNeedDriver(true);
//            order.setTotalCost(50);
//            order.setStatusOrder(Status.NEW_ORDER.toString());
//            order.setDamagePaid(0);
//            order.setIsPaidDamage(false);
//            order.setDocumentSeries("KK");
//            order.setDocumentNum(983746L);
//            Date dateIssue = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-18");
//            order.setDateIssue(dateIssue);
//            order.setAuthority("Sambir ghkd");
//            order.setCar(new CarDAOImpl().getAllCars().get(0));
//            order.setUser(new UserDaoImpl().getAllUsers().get(0));
//            newOrder = orderDAO.createNewOrder(order);
//        } catch (ParseException e) {
//            logger.error(e);
//        }
//        Assert.assertTrue(newOrder);
//    }
//
//    @Test
//    public void getFindByIdTest() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getAllOrder().get(0);
//        Order findById = orderDAO.getFindById(order.getOrderId());
//        Assert.assertEquals(order, findById);
//    }
//
//    @Test
//    public void changeTotalCost() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getAllOrder().get(0);
//        order.setTotalCost(500);
//        orderDAO.changeTotalCost(order.getOrderId(), 500);
//        Order findById = orderDAO.getFindById(order.getOrderId());
//        Assert.assertEquals(order, findById);
//    }
//
//    @Test
//    public void changeStatusOrderTest() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getAllOrder().get(0);
//        order.setStatusOrder(Status.CLOSE_ORDER.toString());
//        orderDAO.changeStatusOrder(order.getOrderId(), order.getStatusOrder());
//        Order findById = orderDAO.getFindById(order.getOrderId());
//        Assert.assertEquals(order, findById);
//    }
//
//    @Test
//    public void changeStatusMessageTest() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getAllOrder().get(0);
//        order.setStatusMessage("You do not have driving license");
//        orderDAO.changeStatusMessage(order.getOrderId(), order.getStatusMessage());
//        Order findById = orderDAO.getFindById(order.getOrderId());
//        Assert.assertEquals(order, findById);
//    }
//
//    @Test
//    public void changeDamagePaidTest() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getAllOrder().get(0);
//        order.setDamagePaid(1500);
//        orderDAO.changeDamagePaid(order.getOrderId(), order.getDamagePaid());
//        Order findById = orderDAO.getFindById(order.getOrderId());
//        Assert.assertEquals(order, findById);
//    }
//
//    @Test
//    public void changeStatusDamagePaidTest() {
//        OrderDAO orderDAO = new OrderDAOImpl();
//        Order order = orderDAO.getAllOrder().get(0);
//        order.setIsPaidDamage(true);
//        orderDAO.changeStatusDamagePaid(order.getOrderId(), order.getIsPaidDamage());
//        Order findById = orderDAO.getFindById(order.getOrderId());
//        Assert.assertEquals(order, findById);
//    }
}
