package com.cobcap.service;

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

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String number_server = session.getAttribute("number_server").toString();



        // TODO: 6/22/18 插入数据到数据库 

        // TODO: 6/22/18 验证码错误返回注册
        String number_client = req.getParameter("mailVerify");
        /*验证码正确*/
        if (number_client.equals(number_server)) {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            String mail = req.getParameter("mail");

            User user = new User(name, password, mail);

            UserDao userDao = new UserDao();
            try {
                userDao.addUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            session.setAttribute("userName", user.getUserName());

            resp.sendRedirect("index.jsp");
        } else {
            req.setAttribute("errorInfo", "验证码不正确");
            req.getRequestDispatcher("bang.jsp").forward(req, resp);
        }

        System.out.println();

    }
}
