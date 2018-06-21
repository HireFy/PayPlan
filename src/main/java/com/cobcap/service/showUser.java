package com.cobcap.service;

import com.cobcap.bean.User;
import com.cobcap.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showUser")
public class showUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<User> users = userDao.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("showUser.jsp").forward(req, resp);
    }
}
