package com.rentCar.controller;

import com.rentCar.DAO.CarDAO;
import com.rentCar.DAO.OrderDAO;
import com.rentCar.entity.car.Car;
import com.rentCar.entity.car.CarType;
import com.rentCar.entity.order.Order;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/main")
public class MainPage extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MainPage.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Date startRent = null;
        Date endRent = null;
        String message = null;
        CarDAO carDAO = new CarDAO();
        req.setAttribute("brands", carDAO.getAllUniqueBrandsCar());
        req.setAttribute("carTypes", new ArrayList<>(Arrays.asList(CarType.values())));
        List<Car> cars = carDAO.getAllCars();
        cars = cars.stream().filter(car -> car.getIsActive() == true).collect(Collectors.toList());

        if (req.getParameter("formType") != null && req.getParameter("formType").equals("filterDel")) {
            session.removeAttribute("startRent");
            session.removeAttribute("startRent");
            session.removeAttribute("brand");
            session.removeAttribute("carType");
            session.removeAttribute("sortPrice");
            session.removeAttribute("sortBrand");
        }

        if (session.getAttribute("startRent") != null && session.getAttribute("endRent") != null) {
            startRent = (Date) session.getAttribute("startRent");
            endRent = (Date) session.getAttribute("endRent");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String simpleStart = dateFormat.format(startRent);
            String simpleEnd = dateFormat.format(endRent);
            req.setAttribute("startRent", simpleStart);
            req.setAttribute("endRent", simpleEnd);
        }

        if (startRent != null && endRent != null) {
            if (startRent.compareTo(new Date(System.currentTimeMillis())) != 1 || endRent.compareTo(startRent) != 1) {
                message = "error.yourDatesAreInvalid";
                session.setAttribute("message", message);
            } else {
                List<Order> orders = new OrderDAO().getAllOrder();
                Iterator<Car> iterator = cars.iterator();
                while (iterator.hasNext()) {
                    Car car = iterator.next();
                    for (Order order : orders) {
                        if (order.getCar().getCarId() == car.getCarId()) {
                            int compareEndRent = order.getRentEnd().compareTo(startRent);
                            int compareStartRent = order.getRentStart().compareTo(endRent);
                            if (( compareEndRent == 1 )) {
                                if (( compareStartRent == -1 )) {
                                    iterator.remove();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (session.getAttribute("brand") != null && !session.getAttribute("brand").equals("none")) {
            cars = cars.stream().filter(car -> car.getBrand().equals(session.getAttribute("brand"))).collect(Collectors.toList());
        }
        if (session.getAttribute("carType") != null && !session.getAttribute("carType").equals("none")) {
            cars = cars.stream().filter(car -> car.getCarType().equals(session.getAttribute("carType"))).collect(Collectors.toList());
        }
        if (session.getAttribute("sortPrice") != null && Boolean.parseBoolean(session.getAttribute("sortPrice").toString())) {
            cars = cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
        }
        if (session.getAttribute("sortBrand") != null && Boolean.parseBoolean(session.getAttribute("sortBrand").toString())) {
            cars = cars.stream().sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
        }


        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        int noOfPages  = (int) Math.ceil(cars.size() * 1.0 / recordsPerPage);
        cars = cars.stream().skip((page - 1) * recordsPerPage).limit(recordsPerPage).collect(Collectors.toList());
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);


        req.setAttribute("cars", cars);
        req.getRequestDispatcher("main.jsp").forward(req, resp);

        session.removeAttribute("message");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String formType = req.getParameter("formType");
        Date startRent = null;
        Date endRent = null;

        if (formType.equals("filter")) {
            if (req.getParameter("startRent") != "" && req.getParameter("endRent") != "") {
                try {
                    startRent = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("startRent"));
                    endRent = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("endRent"));
                    session.setAttribute("startRent", startRent);
                    session.setAttribute("endRent", endRent);
                } catch (ParseException e) {
                    logger.error(e);
                }
            }

            session.setAttribute("brand", req.getParameter("brand"));
            session.setAttribute("carType", req.getParameter("carType"));
            session.setAttribute("sortPrice", req.getParameter("sortPrice"));
            session.setAttribute("sortBrand", req.getParameter("sortBrand"));
        }

        resp.sendRedirect("/RentCar/main");
    }
}
