package com.cobcap.service;

import com.alibaba.fastjson.JSONArray;
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
public class VerifyMail extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String to = req.getParameter("mail");



        JSONObject jsonObject = new JSONObject();
        try {

            /*发送邮箱验证码*/
            MailUtil mailUtil = new MailUtil();
            System.out.println("服务器接收到的邮箱是 " + to);
            String number_server = mailUtil.getAndSendVerifyNumber(to);


            System.out.println("服务器生成的验证码：" + number_server);


            /*返回验证码前端alert显示*/
            jsonObject.put("number_server", number_server);

            resp.setCharacterEncoding("UTF-8");
//
//            System.out.println("验证码是" + number_server);

            resp.getWriter().print(jsonObject);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
