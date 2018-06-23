package com.cobcap.service.dealAjax;

import com.cobcap.util.GenerateUuid;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getUuid")
public class GetUuid extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        System.out.println("server get a name: " + name);

        String uuid = GenerateUuid.getUuid();

        // TODO: 6/23/18 根据userName从数据库拿到user
        // TODO: 插入uuid    返回包装了uuid的json到前端
    }
}
