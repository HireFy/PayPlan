package com.cobcap.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (name.equals("root") && password.equals("123")) {
            req.setAttribute("result", "成功");
        } else if (name.equals("哈哈哈")) {
            req.setAttribute("otherResult", "中文读取成功");
        }

        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
