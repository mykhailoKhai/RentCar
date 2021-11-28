package com.rentCar.filter;

import com.rentCar.controller.AdminCar;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "ControlRoleFilter")
public class ControlRoleFilter implements Filter {

    private static final Logger logger = Logger.getLogger(ControlRoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (path.startsWith("/main") || path.startsWith("/login")
                || path.startsWith("/registration") || path.startsWith("/logout")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String message = null;
        if (path.startsWith("/cabinet") || path.startsWith("/customerOrder") || path.startsWith("/rent")) {
            if (session.getAttribute("customer") != null){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                message = "error.youMustBeAuthenticated";
                session.setAttribute("message", message);
                resp.sendRedirect("/RentCar/logout");
            }
            return;
        }

        if (path.startsWith("/manager")) {
            if (session.getAttribute("manager") != null){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                message = "error.youMustBeAuthenticated";
                session.setAttribute("message", message);
                resp.sendRedirect("/RentCar/logout");
            }
            return;
        }

        if (path.startsWith("/admin/car") || path.startsWith("/admin/user")) {
            if (session.getAttribute("admin") != null){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                message = "error.youMustBeAuthenticated";
                session.setAttribute("message", message);
                resp.sendRedirect("/RentCar/logout");
            }
            return;
        }

        resp.sendRedirect("/RentCar/login");
        session.removeAttribute("message");
    }

    @Override
    public void destroy() {
    }
}
