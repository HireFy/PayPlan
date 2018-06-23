package com.cobcap.service.dealAjax;

import com.alibaba.fastjson.JSONObject;
import com.cobcap.dao.UtilDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/verifyMailExist")
public class VerifyMailExist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mail = req.getParameter("mail");
        UtilDao utilDao = new UtilDao();
        JSONObject jsonObject = new JSONObject();
        try {
            if (utilDao.isExistMail(mail)) {
                System.out.println("存在邮箱");
                jsonObject.put("isExist", "true");
            } else {
                System.out.println("不存在邮箱");
                jsonObject.put("isExist", "false");
            }
            resp.getWriter().print(jsonObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
