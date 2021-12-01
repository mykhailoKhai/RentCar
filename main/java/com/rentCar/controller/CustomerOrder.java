package com.rentCar.controller;

import com.rentCar.DAO.CarDAO;
import com.rentCar.DAO.OrderDAO;
import com.rentCar.DAO.UserDao;
import com.rentCar.DAO.impl.OrderDAOImpl;
import com.rentCar.DAO.impl.UserDaoImpl;
import com.rentCar.entity.order.Order;
import com.rentCar.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/customerOrder")
public class CustomerOrder extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CustomerOrder.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("customer");
        UserDao userDao = new UserDaoImpl();
        List<Order> orders = userDao.getAllOrdersForUser(user.getUserId());
        List<Order> sortList = orders.stream().sorted((o1, o2) -> (int) (o2.getOrderId() - o1.getOrderId())).collect(Collectors.toList());

        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfPages  = (int) Math.ceil(sortList.size() * 1.0 / recordsPerPage);
        sortList = sortList.stream().skip((page - 1) * recordsPerPage).limit(recordsPerPage).collect(Collectors.toList());
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.setAttribute("orders", sortList);
        req.getRequestDispatcher("/customer/customer_order.jsp").forward(req, resp);
        session.removeAttribute("message");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User customer = (User) session.getAttribute("customer");
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getFindById(customer.getUserId());
        OrderDAO orderDAO = new OrderDAOImpl();

        String formType = req.getParameter("formType");
        if (formType.equals("paidDamage")) {
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Order order = orderDAO.getFindById(orderId);

            if (user.getAccount() >= order.getDamagePaid()) {
                userDao.changeAccount(user.getAccount() - order.getDamagePaid(), user.getUserId());
                orderDAO.changeStatusDamagePaid(orderId, true);
                logger.info("User paid for damage user.id: " + user.getUserId() + " , in order.id: " + orderId);
            } else {
                String message = "error.youHaveSmallAccount";
                session.setAttribute("message", message);
            }

        }

        session.setAttribute("customer", userDao.getFindById(customer.getUserId()));
        resp.sendRedirect("/RentCar/customerOrder");
    }
}
