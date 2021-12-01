package com.rentCar.DAO;

import com.rentCar.entity.order.Order;
import com.rentCar.entity.user.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getFindByLoginAndPassword(String login, String password);

    boolean existUserByLogin(String login);

    boolean existUserByPassword(String password);

    boolean createUser(User user);

    void changeActiveUser(long userId, boolean isActive);

    void updateUser(User user);

    User getFindById(long userId);

    void changeAccount(double money, long userId);

    String getPasswordUserById(long userId);

    List<Order> getAllOrdersForUser(long userId);
}

