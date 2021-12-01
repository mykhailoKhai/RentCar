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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@WebServlet("/admin/user")
public class AdminUser extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AdminUser.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDaoImpl();
        req.setAttribute("roles", new ArrayList<>(Arrays.asList(Role.values())));
        List<User> users = userDao.getAllUsers();

        req.setAttribute("users", users);
        req.getRequestDispatcher("/admin/management_user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String formType = req.getParameter("formType");
        UserDao userDao = new UserDaoImpl();

        if (formType.equals("createUser")) {
            User user = new User();
            user.setLogin(req.getParameter("login"));
            user.setPassword(MD5Util.codingToMD5(req.getParameter("password")));
            user.setLastName(req.getParameter("lastName"));
            user.setFirstName(req.getParameter("firstName"));
            if (req.getParameter("phoneNum") != "") {
                user.setPhoneNum(Long.parseLong(req.getParameter("phoneNum")));
            }
            user.setEmail(req.getParameter("email"));
            user.setRole(Role.MANAGER.toString());
            user.setIsActive(Boolean.parseBoolean(req.getParameter("isActive")));
            user.setDocumentSeries(req.getParameter("documentSeries"));
            if (req.getParameter("documentNum") != "") {
                user.setDocumentNum(Long.parseLong(req.getParameter("documentNum")));
            }
            if (req.getParameter("dateIssue") != "") {
                try {
                    user.setDateIssue(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dateIssue")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            user.setAuthority(req.getParameter("authority"));
            userDao.createUser(user);
            logger.info("Manager created user.id: " + user.getUserId());
        } else if (formType.equals("createActive")) {
            long userId = Long.parseLong(req.getParameter("userId"));
            boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
            userDao.changeActiveUser(userId, isActive);
            logger.info("User user.id: " + userId + " , make active: " + isActive);
        }

        resp.sendRedirect("/RentCar/admin/user");
    }
}
