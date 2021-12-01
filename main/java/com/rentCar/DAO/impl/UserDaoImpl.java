package com.rentCar.DAO.impl;

import com.rentCar.DAO.UserDao;
import com.rentCar.entity.order.Order;
import com.rentCar.entity.order.Status;
import com.rentCar.entity.user.Role;
import com.rentCar.entity.user.User;
import com.rentCar.utill.DBManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection manager = DBManager.getInstance().getConnection();
            Statement statement = manager.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT user_id, login, password, last_name, first_name, phone_num, " +
                    "email, role, is_active, document_series, document_num, document_issue, document_authority, account FROM user")) {

            while (resultSet.next()) {
                User user = new User();

                user.setUserId(resultSet.getLong("user_id"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setPhoneNum(resultSet.getLong("phone_num"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.findById(resultSet.getInt("role")).toString());
                user.setIsActive(Boolean.parseBoolean(String.valueOf(resultSet.getBoolean("is_active"))));
                user.setDocumentSeries(resultSet.getString("document_series"));
                user.setDocumentNum(resultSet.getLong("document_num"));
                user.setDateIssue(resultSet.getDate("document_issue"));
                user.setAuthority(resultSet.getString("document_authority"));
                user.setAccount(resultSet.getDouble("account"));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return users;
    }

    @Override
    public User getFindByLoginAndPassword(String login, String password) {
        User user = new User();
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id, login, last_name," +
                     " first_name, phone_num, email, role, is_active, document_series, document_num, document_issue," +
                     " document_authority, account FROM user WHERE login = ? AND password = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                user.setUserId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setPhoneNum(resultSet.getLong("phone_num"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.findById(resultSet.getInt("role")).toString());
                user.setIsActive(resultSet.getBoolean("is_active"));
                user.setDocumentSeries(resultSet.getString("document_series"));
                user.setDocumentNum(resultSet.getLong("document_num"));
                user.setDateIssue(resultSet.getDate("document_issue"));
                user.setAuthority(resultSet.getString("document_authority"));
                user.setAccount(resultSet.getDouble("account"));

            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public boolean existUserByLogin(String login) {
        boolean isExist = false;
        ResultSet resultSet;
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM user WHERE login = ?")) {
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return isExist;
    }

    @Override
    public boolean existUserByPassword(String password) {
        boolean isExist = false;
        ResultSet resultSet;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id FROM user WHERE password = ?")) {
            preparedStatement.setString(1, password);
            resultSet = preparedStatement.executeQuery();
            isExist = resultSet.next();
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return isExist;
    }

    @Override
    public boolean createUser(User user) {
        BasicConfigurator.configure();
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setLong(5, user.getPhoneNum());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setInt(7, Role.valueOf(user.getRole()).getRoleId());
            preparedStatement.setBoolean(8, user.getIsActive());
            preparedStatement.setDouble(9, user.getAccount());
            preparedStatement.setString(10, user.getDocumentSeries());
            preparedStatement.setLong(11, user.getDocumentNum());
            if (user.getDateIssue() != null) {
                preparedStatement.setObject(12, new Timestamp(user.getDateIssue().getTime()));
            } else {
                preparedStatement.setDate(12, null);
            }
            preparedStatement.setString(13, user.getAuthority());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long idField = resultSet.getLong(1);
                user.setUserId(idField);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public void changeActiveUser(long userId, boolean isActive) {
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET is_active = ? WHERE user_id = ?")) {
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET login = ?, password = ?, last_name = ?, " +
                    "first_name = ?, phone_num = ?, email = ?, document_series = ?, document_num = ?, document_issue = ?, document_authority = ? WHERE user_id = ?")) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setLong(5, user.getPhoneNum());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setString(7, user.getDocumentSeries());
            preparedStatement.setLong(8, user.getDocumentNum());
            if (user.getDateIssue() != null) {
                preparedStatement.setObject(9, new Timestamp(user.getDateIssue().getTime()));
            } else {
                preparedStatement.setDate(9, null);
            }
            preparedStatement.setString(10, user.getAuthority());
            preparedStatement.setLong(11, user.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public User getFindById(long userId) {
        User user = new User();
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_id, login, last_name," +
                     " first_name, phone_num, email, role, is_active, document_series, document_num, document_issue," +
                     " document_authority, account FROM user WHERE user_id = ?")) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                user.setUserId(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setPhoneNum(resultSet.getLong("phone_num"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.findById(resultSet.getInt("role")).toString());
                user.setIsActive(resultSet.getBoolean("is_active"));
                user.setDocumentSeries(resultSet.getString("document_series"));
                user.setDocumentNum(resultSet.getLong("document_num"));
                user.setDateIssue(resultSet.getDate("document_issue"));
                user.setAuthority(resultSet.getString("document_authority"));
                user.setAccount(resultSet.getDouble("account"));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    @Override
    public void changeAccount(double money, long userId) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET account = ? WHERE user_id = ?")) {
            preparedStatement.setDouble(1, money);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public String getPasswordUserById(long userId) {
        String pass = null;
        ResultSet resultSet;
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE user_id = ?")) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pass = resultSet.getString("password");
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return pass;
    }

    @Override
    public List<Order> getAllOrdersForUser(long userId) {
        CarDAOImpl carDAO = new CarDAOImpl();
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE user_id = ?")) {
            preparedStatement.setDouble(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderId(resultSet.getLong("order_id"));
                order.setRentStart(resultSet.getDate("rent_start"));
                order.setRentEnd(resultSet.getDate("rent_end"));
                order.setNeedDriver(resultSet.getBoolean("need_driver"));
                order.setTotalCost(resultSet.getDouble("total_cost"));
                order.setStatusOrder(Status.findById(resultSet.getInt("status_order")).toString());
                order.setStatusMessage(resultSet.getString("status_message"));
                order.setDamagePaid(resultSet.getDouble("damage_paid"));
                order.setIsPaidDamage(resultSet.getBoolean("is_paid_damage_cost"));
                order.setDocumentSeries(resultSet.getString("document_series"));
                order.setDocumentNum(resultSet.getLong("document_num"));
                order.setDateIssue(resultSet.getDate("date_issue"));
                order.setAuthority(resultSet.getString("authority"));
                order.setCar(carDAO.getCarById(resultSet.getLong("car_id")));
                orders.add(order);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return orders;
    }
}

