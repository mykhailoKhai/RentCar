package com.rentCar.DAO.impl;

import com.rentCar.DAO.OrderDAO;
import com.rentCar.entity.order.Order;
import com.rentCar.entity.order.Status;
import com.rentCar.utill.DBManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private static final Logger logger = Logger.getLogger(OrderDAOImpl.class);

    @Override
    public boolean createNewOrder(Order order) {

        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement
                     ("INSERT INTO orders VALUES (DEFAULT ,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            if (order.getRentStart() != null && order.getRentEnd() != null) {
                preparedStatement.setObject(1, new Timestamp(order.getRentStart().getTime()));
                preparedStatement.setObject(2, new Timestamp(order.getRentEnd().getTime()));
            }
            preparedStatement.setBoolean(3, order.getNeedDriver());
            preparedStatement.setDouble(4, order.getTotalCost());
            preparedStatement.setInt(5, Status.valueOf(order.getStatusOrder()).getStatusId());
            preparedStatement.setString(6, order.getStatusMessage());
            preparedStatement.setDouble(7, order.getDamagePaid());
            preparedStatement.setBoolean(8, order.getIsPaidDamage());
            preparedStatement.setString(9, order.getDocumentSeries());
            preparedStatement.setLong(10, order.getDocumentNum());
            if (order.getDateIssue() != null) {
                preparedStatement.setObject(11, new Timestamp(order.getDateIssue().getTime()));
            }
            preparedStatement.setString(12, order.getAuthority());
            preparedStatement.setLong(13, order.getUser().getUserId());
            preparedStatement.setLong(14, order.getCar().getCarId());

            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long idField = resultSet.getLong(1);
                order.setOrderId(idField);
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> orders = new ArrayList<>();
        UserDaoImpl userDao = new UserDaoImpl();
        CarDAOImpl carDAO = new CarDAOImpl();

        try (Connection connection = DBManager.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT order_id, rent_start, rent_end, need_driver, total_cost, status_order, " +
                    "status_message, damage_paid, is_paid_damage_cost, document_series, document_num, date_issue, authority, user_id, car_id FROM orders")) {

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
                order.setUser(userDao.getFindById(resultSet.getLong("user_id")));
                order.setCar(carDAO.getCarById(resultSet.getLong("car_id")));
                orders.add(order);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET status_order = ?, status_message = ?, damage_paid = ? WHERE order_id = ?")) {
            preparedStatement.setInt(1, Status.valueOf(order.getStatusOrder()).getStatusId());
            preparedStatement.setString(2, order.getStatusMessage());
            preparedStatement.setDouble(3, order.getDamagePaid());
            preparedStatement.setLong(4, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void changeStatusOrder(long orderId, String statusOrder) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET status_order = ? WHERE order_id = ?")) {
            preparedStatement.setInt(1, Status.valueOf(statusOrder).getStatusId());
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void changeStatusMessage(long orderId, String statusMessage) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET status_message = ? WHERE order_id = ?")) {
            preparedStatement.setString(1, statusMessage);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void changeDamagePaid(long orderId, double damagePaid) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET damage_paid = ? WHERE order_id = ?")) {
            preparedStatement.setDouble(1, damagePaid);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void changeStatusDamagePaid(long orderId, boolean b) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET is_paid_damage_cost = ? WHERE order_id = ?")) {
            preparedStatement.setBoolean(1, b);
            preparedStatement.setLong(2, orderId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public Order getFindById(long orderId) {
        Order order = new Order();
        UserDaoImpl userDao = new UserDaoImpl();
        CarDAOImpl carDAO = new CarDAOImpl();
        ResultSet resultSet = null;
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT order_id, rent_start, rent_end, need_driver, total_cost, status_order, " +
                     "status_message, damage_paid, is_paid_damage_cost, document_series, document_num, date_issue, authority, user_id, car_id FROM orders WHERE order_id = ?")) {

            statement.setLong(1, orderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
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
                order.setUser(userDao.getFindById(resultSet.getLong("user_id")));
                order.setCar(carDAO.getCarById(resultSet.getLong("car_id")));
            }
            resultSet.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return order;
    }

    @Override
    public void changeTotalCost(long orderId, double newTotalCost) {
        try (Connection connection = DBManager.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE orders SET total_cost = ? WHERE order_id = ?")) {
            preparedStatement.setLong(2, orderId);
            preparedStatement.setDouble(1, newTotalCost);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
