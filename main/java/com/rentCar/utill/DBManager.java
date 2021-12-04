package com.rentCar.utill;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import java.beans.PropertyVetoException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBManager {

    private static final Logger logger = Logger.getLogger(DBManager.class);
    private static String url, user, pass, driver;
    private static DBManager instance = null;

    private DBManager() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
            url = properties.getProperty("db.connection");
            user = properties.getProperty("user");
            pass = properties.getProperty("password");
            driver = properties.getProperty("driver");
        }catch(IOException e) {
            logger.error(e);
        }
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

//    public  Connection getConnection() {
//        Connection connection = null;
//        ComboPooledDataSource cpds = new ComboPooledDataSource();
//        try {
//            cpds.setDriverClass(driver);
//            cpds.setJdbcUrl    (url);
//            cpds.setUser       (user);
//            cpds.setPassword   (pass);
//            cpds.setMaxStatements             (180);
//            cpds.setMaxStatementsPerConnection(180);
//            cpds.setMinPoolSize               ( 5);
//            cpds.setAcquireIncrement          ( 10);
//            cpds.setMaxPoolSize               ( 20);
//            cpds.setMaxIdleTime               ( 10);
//            connection = cpds.getConnection();
//        } catch (PropertyVetoException | SQLException e) {
//            logger.error(e);
//        }
//        return connection;
//    }


    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/dbPool");
            c = ds.getConnection();
        } catch (NamingException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        }
        return c;
    }



//    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection("jdbc:mysql://localhost:3306/rent_car?serverTimezone=UTC",
//                "root",
//                "Khajmike4958"
//        );
//    }
}
