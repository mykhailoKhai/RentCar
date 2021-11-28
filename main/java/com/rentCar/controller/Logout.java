package com.rentCar.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("customer");
        session.removeAttribute("admin");
        session.removeAttribute("manager");

//        session.removeAttribute("message");
        session.removeAttribute("carId");
        session.removeAttribute("startRent");
        session.removeAttribute("endRent");
        session.removeAttribute("brand");
        session.removeAttribute("carType");
        session.removeAttribute("sortPrice");
        session.removeAttribute("sortBrand");


        resp.sendRedirect("/RentCar/main");

    }
}
