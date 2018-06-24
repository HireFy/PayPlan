package com.cobcap.service.dealAjax;

import com.alibaba.fastjson.JSONObject;
import com.cobcap.bean.User;
import com.cobcap.dao.UserDao;
import com.cobcap.util.GenerateUuid;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/getUuid")
public class GetUuid extends HttpServlet {

    public static final Logger logger = Logger.getLogger(GetUuid.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        logger.info("/getUuid: userName: " + name);

        String uuid = GenerateUuid.getUuid();

        JSONObject jsonObject = new JSONObject();

        // TODO: 6/23/18 根据userName从数据库拿到user
        // TODO: 插入uuid    返回包装了uuid的json到前端
        // 用户没有uuid才会请求得到这个servlet
        // 所以这里不是从数据库里拿到用户的uuid
        //而是生成一个uuid,插入到数据库
        UserDao userDao = new UserDao();
        try {
            if (userDao.updateUuidByName(name, uuid)) {
                jsonObject.put("uuid", uuid);
                logger.info("update uuid success: " + uuid);
            } else {
                /*这里我想在请求uuid的时候，如果服务端发生了错误
                * 可以直接返回错误的信息*/
                jsonObject.put("uuid", "error");
            }
            resp.getWriter().print(jsonObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
