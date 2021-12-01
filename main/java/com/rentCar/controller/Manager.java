package com.rentCar.controller;

import com.rentCar.DAO.OrderDAO;
import com.rentCar.DAO.UserDao;
import com.rentCar.DAO.impl.OrderDAOImpl;
import com.rentCar.DAO.impl.UserDaoImpl;
import com.rentCar.entity.order.Order;
import com.rentCar.entity.order.Status;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/manager")
public class Manager extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Manager.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        OrderDAO orderDAO = new OrderDAOImpl();
        String message = null;

        long orderId = 0;

        if (req.getParameter("orderId") != null) {
            orderId = Long.parseLong(req.getParameter("orderId"));
            Order order = orderDAO.getFindById(orderId);
            if (order.getOrderId() == 0) {
                message = "error.orderDoesNotExist";
                session.setAttribute("message", message);
            } else {
                req.setAttribute("order", order);
                req.setAttribute("allStatusOrder", new ArrayList<>(Arrays.asList(Status.values())));
                req.getRequestDispatcher("/manager/manager_order.jsp").forward(req, resp);
                return;
            }
        }

        List<Order> orders = orderDAO.getAllOrder();

        if (session.getAttribute("filter") != null && !session.getAttribute("filter").equals("none")) {
            orders = orders.stream().filter(order -> order.getStatusOrder().equals(session.getAttribute("filter"))).collect(Collectors.toList());
        }

        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfPages  = (int) Math.ceil(orders.size() * 1.0 / recordsPerPage);
        orders = orders.stream().skip((page - 1) * recordsPerPage).limit(recordsPerPage).collect(Collectors.toList());
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.setAttribute("orders", orders);
        req.setAttribute("allStatusOrder", new ArrayList<>(Arrays.asList(Status.values())));
        req.getRequestDispatcher("/manager/manager.jsp").forward(req, resp);
        session.removeAttribute("message");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String message = null;

        String formType = req.getParameter("formType");

        if (req.getParameter("orderId") != null) {
            long orderId = Long.parseLong(req.getParameter("orderId"));

            OrderDAO orderDAO = new OrderDAOImpl();
            UserDao userDao = new UserDaoImpl();

            Order order = orderDAO.getFindById(orderId);

            if (order.getOrderId() == 0 && order.getOrderId() != orderId) {
                message = "error.orderDoesNotExist";
            } else if (formType.equals("changeStatusOrder")) {
                String statusOrder = Status.valueOf(req.getParameter("allStatusOrder")).toString();
                if (statusOrder.equals("CLOSE_ORDER")) {
                    if (order.getDamagePaid() <= 0.01 || order.getIsPaidDamage()) {
                        orderDAO.changeStatusOrder(order.getOrderId(), statusOrder);
                        logger.info("Order closed order.id: " + order.getOrderId());

                    } else {
                        message = "error.userDidNotPayForDamage";
                    }
                } else if (statusOrder.equals("REJECT_ORDER")) {
                    double totalCost = order.getTotalCost();
                    double account = order.getUser().getAccount();
                    long userId = order.getUser().getUserId();
                    userDao.changeAccount(totalCost + account, userId);
                    logger.info("Change account user.id: " + userId);
                    orderDAO.changeStatusOrder(order.getOrderId(), statusOrder);
                    logger.info("Change status order.id: " + order.getOrderId() + " , status: " + statusOrder);
                    orderDAO.changeTotalCost(order.getOrderId(), 0);
                    logger.info("Change total cost order.id: " + order.getOrderId() + " , sum: " + totalCost);
                    logger.info("Order reject order.id: " + order.getOrderId());

                } else {
                    orderDAO.changeStatusOrder(orderId, statusOrder);
                    logger.info("Change status order.id: " + order.getOrderId() + " , status: " + statusOrder);
                }
                String statusMessage = req.getParameter("statusMessage");
                if (statusMessage != null && statusMessage != "") {
                    orderDAO.changeStatusMessage(orderId, statusMessage);
                    logger.info("Change status message order.id: " + order.getOrderId());
                }
            } else if (formType.equals("changeDamagePaid")) {
                double damagePaid = Double.parseDouble(req.getParameter("damagePaid"));
                orderDAO.changeDamagePaid(orderId, damagePaid);
                logger.info("Change pay for damage order.id: " + order.getOrderId() + " , sum: " + damagePaid);
            }
        } else if (formType.equals("filterOrder")) {
            session.setAttribute("filter", req.getParameter("typeOrder"));
        }

        session.setAttribute("message", message);
        resp.sendRedirect("/RentCar/manager");
    }
}
