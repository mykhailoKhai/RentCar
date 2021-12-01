package com.rentCar.controller;

import com.rentCar.DAO.CarDAO;
import com.rentCar.DAO.OrderDAO;
import com.rentCar.DAO.UserDao;
import com.rentCar.DAO.impl.CarDAOImpl;
import com.rentCar.DAO.impl.OrderDAOImpl;
import com.rentCar.DAO.impl.UserDaoImpl;
import com.rentCar.entity.car.Car;
import com.rentCar.entity.order.Order;
import com.rentCar.entity.order.Status;
import com.rentCar.entity.user.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@WebServlet("/rent")
public class RentCar extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminCar.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        CarDAO carDAO = new CarDAOImpl();

        String carId = req.getParameter("carId");
        if (carId != "" && carId != null) {
            session.setAttribute("carId", carId);
        }

        Car car = carDAO.getCarById(Long.parseLong(session.getAttribute("carId").toString()));

        Date startRent = (Date) session.getAttribute("startRent");
        Date endRent =  (Date) session.getAttribute("endRent");

        if (car.getCarId() == 0) {
            session.setAttribute("message", "error.carDoesNotExist");
            resp.sendRedirect("/RentCar/main");

        } else {
            if (endRent != null && startRent != null) {
                long diff = Math.round(( endRent.getTime() - startRent.getTime() ) / (double) 86400000);
                double price = car.getPrice();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String simpleStart = dateFormat.format(session.getAttribute("startRent"));
                String simpleEnd = dateFormat.format(session.getAttribute("endRent"));
                req.setAttribute("startR", simpleStart);
                req.setAttribute("endR", simpleEnd);
                req.setAttribute("totalCost", diff * price);
            }
            req.setAttribute("car", car);
            session.setAttribute("carId", car.getCarId());
            req.getRequestDispatcher("/customer/customer_rent.jsp").forward(req, resp);
            resp.sendRedirect("/RentCar/rent");
            session.removeAttribute("message");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String message = null;

        CarDAO carDAO = new CarDAOImpl();
        Car car = carDAO.getCarById((long) session.getAttribute("carId"));
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getFindById(( (User) session.getAttribute("customer") ).getUserId());
        OrderDAO orderDAO = new OrderDAOImpl();

        Date startRent = null;
        Date endRent = null;
        long diff = 0;
        double totalPrice = 0;
        double changeAccount   = 0;
        boolean isFreeCar = false;

        if(session.getAttribute("startRent") != null && session.getAttribute("endRent") != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String simpleStart = dateFormat.format(session.getAttribute("startRent"));
            String simpleEnd = dateFormat.format(session.getAttribute("endRent"));
            try {
                startRent = new SimpleDateFormat("yyyy-MM-dd").parse(simpleStart);
                endRent = new SimpleDateFormat("yyyy-MM-dd").parse(simpleEnd);
            } catch (ParseException e) {
                logger.error(e);
            }
        }

        if (req.getParameter("startRent") != null && req.getParameter("endRent") != null) {
            if (!req.getParameter("startRent").equals("") && !req.getParameter("endRent").equals("")) {
                try {
                    startRent = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startRent"));
                    endRent = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("endRent"));
                    session.setAttribute("startRent", startRent);
                    session.setAttribute("endRent", endRent);

                } catch (ParseException e) {
                    logger.error(e);
                }
            }
        }

        if (startRent != null && endRent != null) {
            diff = Math.round((endRent.getTime() - startRent.getTime()) / (double) 86400000);
            totalPrice = diff * car.getPrice();
            changeAccount = user.getAccount() - totalPrice;

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String simpleStart = dateFormat.format(session.getAttribute("startRent"));
            String simpleEnd = dateFormat.format(session.getAttribute("endRent"));
            req.setAttribute("startR", simpleStart);
            req.setAttribute("endR", simpleEnd);
            if (totalPrice < 0) {
                req.setAttribute("totalCost", 0);
            } else {
                req.setAttribute("totalCost", totalPrice);
            }
            req.setAttribute("car", car);

            isFreeCar = true;
            List<Order> orders = orderDAO.getAllOrder();
            for (Order order : orders) {
                if (order.getCar().getCarId() == car.getCarId()) {
                    int compareEndRent = order.getRentEnd().compareTo(startRent);
                    int compareStartRent = order.getRentStart().compareTo(endRent);
                    if ((compareEndRent == 1)) {
                        if ((compareStartRent == -1)) {
                            isFreeCar = false;
                        }
                    }
                }
            }
        }



        if (startRent == null && endRent == null) {
            message = "error.youNeedWriteDates";
        } else if (startRent.compareTo(new Date(System.currentTimeMillis())) != 1 || endRent.compareTo(startRent) != 1) {
            message = "error.yourDatesAreInvalid";
        } else if (changeAccount <= -0.01) {
            message = "error.youDoNotHaveEnoughMoney";
        } else if (user.getDocumentNum() == 0 || user.getDateIssue() == null || user.getAuthority() == null) {
            message = "error.YourDocumentsAreInvalid";
        } else if (!isFreeCar) {
            message = "error.theCarIsBookedForYourDates";
        } else  if (car.getModel() == null) {
            message = "The car does not exist with an id: " + session.getAttribute("carId");
        } else {
            if (req.getParameter("formType") != null && req.getParameter("formType").equals("changeInf")) {
                resp.sendRedirect("/RentCar/rent");
                logger.info("User updated information about rent car user.id : " + user.getUserId() + " , car.id: " + car.getCarId());
                return;
            } else {
                userDao.changeAccount(changeAccount, user.getUserId());

                Order order = new Order();
                order.setRentStart(startRent);
                order.setRentEnd(endRent);
                order.setNeedDriver(Boolean.parseBoolean(req.getParameter("needDriver")));
                order.setTotalCost(totalPrice);
                order.setStatusOrder(Status.NEW_ORDER.toString());
                order.setDocumentSeries(user.getDocumentSeries());
                order.setDocumentNum(user.getDocumentNum());
                order.setDateIssue(user.getDateIssue());
                order.setAuthority(user.getAuthority());
                order.setCar(car);
                order.setUser(user);

                orderDAO.createNewOrder(order);
                logger.info("User created order user.id: " + user.getUserId() + " , order.id: " + order.getOrderId() + " , car.id: " + car.getCarId());
            }
        }

        if (message == null) {
            session.setAttribute("customer", userDao.getFindById(user.getUserId()));
            resp.sendRedirect("/RentCar/main");
        } else {
            session.setAttribute("message", message);
            resp.sendRedirect("/RentCar/rent");
        }
    }
}
