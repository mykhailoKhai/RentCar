package com.rentCar.DAO;

import com.rentCar.DAO.impl.UserDaoImpl;
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
import java.util.List;
import java.util.Properties;

public class UserDAOTest {
    private static final Logger logger = Logger.getLogger(UserDAOTest.class);
//
//    private static final String URL_FOR_TESTING = "jdbc:mysql://localhost:3306/rent_car_test?serverTimezone=UTC";
//
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
//            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS user(\n" +
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
//            statement.executeUpdate(sqlCreateTable);
//
//            UserDao userDao = new UserDaoImpl();
//
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
//
//            userDao.createUser(user);
//
//        } catch (SQLException e) {
//            logger.error(e);
//        }
//
//    }
//
//    @Test
//    public void createUserTest() {
//        UserDao userDao = new UserDaoImpl();
//
//        User user = new User();
//        user.setLogin("8888");
//        user.setPassword(MD5Util.codingToMD5("8888"));
//        user.setLastName("Khai8888");
//        user.setFirstName("Mike8888");
//        user.setPhoneNum(380981122333L);
//        user.setEmail("mail.mini@gmail.com");
//        user.setRole(Role.CUSTOMER.toString());
//        user.setIsActive(true);
//        user.setAccount(30);
//
//        boolean accept = userDao.createUser(user);
//
//        Assert.assertTrue(accept);
//    }
//
//    @Test
//    public void getAllUsersTest() {
//        UserDao userDao = new UserDaoImpl();
//        List<User> users = userDao.getAllUsers();
//        User user = users.get(0);
//
//        Assert.assertNotNull(user);
//    }
//
//    @Test
//    public void getFindByLoginAndPasswordTest() {
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.getFindByLoginAndPassword("4444", MD5Util.codingToMD5("4444"));
//        Assert.assertNotNull(user.getLogin());
//        Assert.assertNotNull(user.getEmail());
//    }
//
//    @Test
//    public void existUserByLoginTest() {
//        UserDao userDao = new UserDaoImpl();
//        boolean accept = userDao.existUserByLogin("4444");
//        Assert.assertTrue(accept);
//    }
//
//    @Test
//    public void existUserByPasswordTest() {
//        UserDao userDao = new UserDaoImpl();
//        boolean accept = userDao.existUserByPassword(MD5Util.codingToMD5("4444"));
//        Assert.assertTrue(accept);
//    }
//
//    @Test
//    public void changeActiveUserTest() {
//        UserDao userDao = new UserDaoImpl();
//        List<User> users = userDao.getAllUsers();
//        userDao.changeActiveUser(users.get(0).getUserId(), false);
//        List<User> allUsers = userDao.getAllUsers();
//
//        Assert.assertFalse(allUsers.get(0).getIsActive());
//    }
//
//    @Test
//    public void updateUserTest() {
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.getAllUsers().get(0);
//
//        user.setLogin(userDao.getFindById(user.getUserId()).getLogin());
//        user.setPassword(userDao.getPasswordUserById(user.getUserId()));
//        user.setFirstName("Oleg");
//        user.setLastName("Prutyla");
//        user.setDocumentNum(987654L);
//        userDao.updateUser(user);
//
//        User user1 = userDao.getAllUsers().get(0);
//        Assert.assertEquals(user.getFirstName(), user1.getFirstName());
//        Assert.assertEquals(user.getLastName(), user1.getLastName());
//        Assert.assertEquals(user.getDocumentNum(), user1.getDocumentNum());
//    }
//
//    @Test
//    public void getFindByIdTest() {
//        UserDao userDao = new UserDaoImpl();
//        long userId1 = userDao.getAllUsers().get(0).getUserId();
//        User findById = userDao.getFindById(userId1);
//        Assert.assertEquals(userId1, findById.getUserId());
//    }
//
//    @Test
//    public void changeAccountTest() {
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.getAllUsers().get(0);
//        userDao.changeAccount(100, user.getUserId());
//        User findById = userDao.getFindById(user.getUserId());
//
//        int account = (int) findById.getAccount();
//        Assert.assertEquals(100,account);
//    }
//
//    @Test
//    public void getPasswordUserById() {
//        UserDao userDao = new UserDaoImpl();
//        User user = userDao.getAllUsers().get(0);
//        String passwordUserById = userDao.getPasswordUserById(user.getUserId());
//        Assert.assertEquals(MD5Util.codingToMD5("4444"), passwordUserById);
//    }
}
