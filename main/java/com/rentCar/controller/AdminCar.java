package com.rentCar.controller;

import com.rentCar.DAO.CarDAO;
import com.rentCar.DAO.impl.CarDAOImpl;
import com.rentCar.entity.car.*;
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

@WebServlet("/admin/car")
public class AdminCar extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminCar.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        CarDAO carDAO = new CarDAOImpl();
        req.setAttribute("bodyTypes", new ArrayList<>(Arrays.asList(BodyType.values())));
        req.setAttribute("carTypes", new ArrayList<>(Arrays.asList(CarType.values())));
        req.setAttribute("transmissionTypes", new ArrayList<>(Arrays.asList(TransmissionType.values())));
        req.setAttribute("fuelTypes", new ArrayList<>(Arrays.asList(FuelType.values())));
        req.setAttribute("colors", new ArrayList<>(Arrays.asList(Color.values())));

        if (session.getAttribute("carId") != null) {
            Car car = carDAO.getCarById((long) session.getAttribute("carId"));
            req.setAttribute("car", car);
            req.getRequestDispatcher("/admin/update_car.jsp").forward(req, resp);
        } else {
            List<Car> cars = carDAO.getAllCars();

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
            req.getRequestDispatcher("/admin/management_car.jsp").forward(req, resp);
        }
        session.removeAttribute("message");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CarDAO carDAO = new CarDAOImpl();
        String formType = req.getParameter("formType");
        String message = null;

        if (formType.equals("deleteCar")) {
            long idDeleteCar = Long.parseLong(req.getParameter("carId"));
            Car carById = carDAO.getCarById(idDeleteCar);
            if (carById.getCarId() == idDeleteCar && carById.getCarId() != 0) {
                carDAO.deleteCar(idDeleteCar);
                logger.info("Car deleted car.id: " + idDeleteCar);
            } else {
                message = "error.carDoesNotExist";
            }
        } else if (formType.equals("updateCar")) {
            long idUpdateCar = Long.parseLong(req.getParameter("carId"));
            Car carById = carDAO.getCarById(idUpdateCar);
            if (carById.getCarId() == idUpdateCar && carById.getCarId() != 0) {
                session.setAttribute("carId", idUpdateCar);
            } else {
                message = "error.carDoesNotExist";
            }
        } else if (formType.equals("update") || formType.equals("createCar")) {
            Car car = new Car();
            car.setCarType(req.getParameter("carTypeName").trim());
            car.setBrand(req.getParameter("brand"));
            car.setModel(req.getParameter("model"));
            car.setBodyType(req.getParameter("bodyTypeName").trim());
            car.setColor(req.getParameter("color").trim());
            car.setYearCreation(Integer.parseInt(req.getParameter("yearCreation")));
            car.setTransmissionType(req.getParameter("transmissionTypeName").trim());
            car.setEngineSize(Double.parseDouble(req.getParameter("engineSize")));
            car.setEnginePower(Integer.parseInt(req.getParameter("enginePower")));
            car.setFuelType(req.getParameter("fuelTypeName").trim());
            car.setPrice(Double.parseDouble(req.getParameter("price")));
            car.setIsActive(Boolean.parseBoolean(req.getParameter("isActive").trim()));

            if (formType.equals("createCar")) {
                carDAO.createCar(car);
                logger.info("Car created car.id: " + car.getCarId());
            } else if (formType.equals("update")) {
                long idUpdateCar = Long.parseLong(String.valueOf(session.getAttribute("carId")));
                carDAO.updateCar(idUpdateCar, car);
                logger.info("Car updated car.id: " + car.getCarId());
                session.removeAttribute("carId");
            }
        } else if (formType.equals("mainCar")){
            session.removeAttribute("carId");
        }

        session.setAttribute("message", message);
        resp.sendRedirect("/RentCar/admin/car");
    }
}
