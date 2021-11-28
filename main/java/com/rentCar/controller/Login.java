package com.rentCar.controller;

import com.rentCar.DAO.UserDao;
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

@WebServlet("/login")
public class Login extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);

        req.getRequestDispatcher("login.jsp").forward(req, resp);

        session.removeAttribute("message");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        String message = null;
        UserDao userDao = new UserDao();

        if(!userDao.existUserByLogin(login)) {
            message = "error.loginIsInvalid";
        } else if(!userDao.existUserByPassword(MD5Util.codingToMD5(password))) {
            message = "error.passwordIsInvalid";
        } else if(userDao.getFindByLoginAndPassword(login, MD5Util.codingToMD5(password)).getUserId() == 0) {
            message = "error.loginOrPasswordIsInvalid";
        } else if(userDao.getFindByLoginAndPassword(login, MD5Util.codingToMD5(password)).getIsActive() == false) {
            message = "error.userWithThisLoginAndPasswordIsBlock";
        } else  {
            User user = userDao.getFindByLoginAndPassword(login, MD5Util.codingToMD5(password));
            if (user.getRole().equals("CUSTOMER")) {
                session.setAttribute("customer", user);
                resp.sendRedirect("/RentCar/main");
            } else if (user.getRole().equals("MANAGER")) {
                session.setAttribute("manager", user);
                resp.sendRedirect("/RentCar/manager");
            } else if (user.getRole().equals("ADMIN")) {
                session.setAttribute("admin", user);
                resp.sendRedirect("/RentCar/admin/car");
            }
        }

        if (message != null) {
            session.setAttribute("message", message);
            resp.sendRedirect("/RentCar/login");
        }
    }
}
