package com.cobcap.service;

import com.alibaba.fastjson.JSONObject;
import com.cobcap.bean.User;
import com.cobcap.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        System.out.println("name: " + name);
        System.out.println("pass: " + password);

        User user = new User(name, password);
        UserDao userDao = new UserDao();


        try {
            if (userDao.isExistUser(user)) {
                HttpSession session = req.getSession(true);
                /*重新从数据库里获得该登录用户的信息*/
                user = userDao.getUserByName(user.getUserName());
                /*添加用户名和用户uuid到session*/
                // TODO: 6/23/18 这里可能还需要传递user.mail, user.paytime
                session.setAttribute("userName", user.getUserName());
                session.setAttribute("uuid", user.getUuid());
                resp.sendRedirect("/index");
            } else {
                req.setAttribute("errorInfo", "用户名或密码错误");
                req.getRequestDispatcher("bang.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
