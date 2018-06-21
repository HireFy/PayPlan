package com.cobcap.service;

import com.alibaba.fastjson.JSONObject;
import com.cobcap.util.MailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verifyMail")
public class verifyMail extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String to = req.getParameter("mail");
        try {

            MailUtil mailUtil = new MailUtil();
            System.out.println("服务器接收到的邮箱是 " + to);
            String number = mailUtil.getAndSendVerifyNumber(to);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Number", number);
            resp.setCharacterEncoding("UTF-8");

            System.out.println("验证码是" + number);

            resp.getWriter().print(jsonObject);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
