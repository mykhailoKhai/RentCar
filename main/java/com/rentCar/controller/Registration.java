package com.rentCar.controller;

import com.rentCar.DAO.UserDao;
import com.rentCar.DAO.impl.UserDaoImpl;
import com.rentCar.entity.user.Role;
import com.rentCar.entity.user.User;
import com.rentCar.utill.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Registration.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        req.getRequestDispatcher("registration.jsp").forward(req, resp);

        session.removeAttribute("message");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String conPassword = req.getParameter("conPassword");
        UserDao userDao = new UserDaoImpl();
        String message = null;

        if (login.isEmpty() || password.isEmpty() || login == null || password == null) {
            message = "error.loginOrPasswordIsEmpty";
        } else if (!password.equals(conPassword)) {
            message = "error.passwordsAndConfirmPasswordIsDifferent";
        } else if (userDao.existUserByLogin(login)) {
            message = "error.thisLoginAlreadyUsed";
        } else if (userDao.existUserByPassword(password)) {
            message = "error.thisPasswordAlreadyUsed";
        } else {
            User user = new User();
            user.setLogin(req.getParameter("login"));
            user.setPassword(MD5Util.codingToMD5(req.getParameter("password")));
            user.setLastName(req.getParameter("lastName"));
            user.setFirstName(req.getParameter("firstName"));
            if (req.getParameter("phoneNumber") != "") {
                user.setPhoneNum(Long.parseLong(req.getParameter("phoneNumber")));
            }
            user.setEmail(req.getParameter("email"));
            user.setRole(Role.CUSTOMER.toString());
            user.setIsActive(true);
            user.setAccount(0);
            logger.info("User was register");
        }

        if (message == null) {
            resp.sendRedirect("/RentCar/login");
        } else {
            session.setAttribute("message", message);
            resp.sendRedirect("/RentCar/registration");
        }
    }
}
