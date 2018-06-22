package com.cobcap.service;

import com.alibaba.fastjson.JSONObject;
import com.cobcap.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verifyRegisterName")
public class VerifyRegisterName extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String registerName = req.getParameter("registerName");

        UserDao userDao = new UserDao();
        JSONObject jsonObject = new JSONObject();

        try {
            if (userDao.isExistName(registerName)) {
                jsonObject.put("isExist", "true");
            } else {
                jsonObject.put("isExist", "false");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.getWriter().print(jsonObject);
    }
}
