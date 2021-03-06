package com.rentCar.DAO;

import com.rentCar.entity.order.Order;
import java.util.List;

public interface OrderDAO {

    boolean createNewOrder(Order order);

    List<Order> getAllOrder();

    Order getFindById(long orderId);

    void changeTotalCost(long orderId, double newTotalCost);

    void changeStatusOrder(long orderId, String statusOrder);

    void changeStatusMessage(long orderId, String statusMessage);

    void changeDamagePaid(long orderId, double damagePaid);

    void changeStatusDamagePaid(long orderId, boolean b);
}
