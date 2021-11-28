package com.rentCar.controller;

import com.rentCar.DAO.UserDao;
import com.rentCar.entity.user.User;
import com.rentCar.utill.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/cabinet")
public class Cabinet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.getRequestDispatcher("/customer/cabinet.jsp").forward(req, resp);
        session.removeAttribute("message");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User customer = (User) session.getAttribute("customer");
        String message = null;

        UserDao userDao = new UserDao();

        String formType = req.getParameter("formType");

        if (formType.equals("updateUser")) {
            User updateUser = userDao.getFindById(customer.getUserId());
            updateUser.setLogin(req.getParameter("login"));
            if (req.getParameter("oldPassword") != "" && req.getParameter("oldPassword") != "" && req.getParameter("oldPassword") != "") {
                if (userDao.getFindByLoginAndPassword(customer.getLogin(), MD5Util.codingToMD5(req.getParameter("oldPassword"))).getUserId() == 0) {
                    message = "error.loginOrOldPasswordIsInvalid";
                    session.setAttribute("message", message);
                    updateUser.setPassword(userDao.getPasswordUserById(customer.getUserId()));
                } else if (!req.getParameter("newPassword").equals(req.getParameter("repPassword"))) {
                    message = "error.newPasswordAndRepeatPasswordIsDifferent";
                    session.setAttribute("message", message);
                    updateUser.setPassword(userDao.getPasswordUserById(customer.getUserId()));
                } else {
                    updateUser.setPassword(MD5Util.codingToMD5(req.getParameter("newPassword")));
                }
            } else {
                updateUser.setPassword(userDao.getPasswordUserById(customer.getUserId()));
            }

            updateUser.setLastName(req.getParameter("lastName"));
            updateUser.setFirstName(req.getParameter("firstName"));
            updateUser.setPhoneNum(Long.parseLong(req.getParameter("phoneNum")));
            updateUser.setEmail(req.getParameter("email"));
            updateUser.setDocumentSeries(req.getParameter("documentSeries"));
            updateUser.setDocumentNum(Long.parseLong(req.getParameter("documentNum")));
            if (!req.getParameter("dateIssue").equals("")) {
                try {
                    updateUser.setDateIssue(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dateIssue")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            updateUser.setAuthority(req.getParameter("authority"));
            userDao.updateUser(updateUser);
            session.setAttribute("customer", userDao.getFindById(customer.getUserId()));
            resp.sendRedirect("/RentCar/cabinet");

        } else if (formType.equals("updateMoney")) {
            User user = userDao.getFindById(customer.getUserId());
            Double money = Double.parseDouble(req.getParameter("money"));
            userDao.changeAccount(money + user.getAccount(), customer.getUserId());
            session.setAttribute("customer", userDao.getFindById(customer.getUserId()));
            resp.sendRedirect("/RentCar/cabinet");
        }

    }
}
