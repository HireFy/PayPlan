package com.cobcap.dao;

import com.cobcap.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private String dbUrl = "localhost:3306";
    private String dbName = "/PayPlan";
    private String dbUser = "root";
    private String dbPassword = "linuxvimf";

    public UserDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + dbUrl + dbName, dbUser, dbPassword);
    }


    public void addUser(User user) {
        String sql = "insert into USER (USERNAME, MAIL, PASSWORD, UUID) VALUE (?, ?, ?, ?);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getMail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUuid());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {

        List<User> users = new ArrayList<User>();

        try (Connection conn = getConnection(); Statement statement = conn.createStatement();) {
            String sql = "select * from USER";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String userName = rs.getString("USERNAME");
                String mail = rs.getString("MAIL");
                String userPassword = rs.getString("PASSWORD");
                String uuid = rs.getString("UUID");
                Timestamp payTime = rs.getTimestamp("PAYTIME");
                Timestamp deadLine = rs.getTimestamp("DEADLINE");
                int balance = rs.getInt("BALANCE");

                User user = new User(id, userName, mail, userPassword, uuid, payTime, deadLine, balance);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}
