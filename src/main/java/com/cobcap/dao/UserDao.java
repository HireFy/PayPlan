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

    public User getUserByName(String name) throws SQLException {
        String sql = "select * from USER where USERNAME = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                String userName = rs.getString("USERNAME");
                String mail = rs.getString("MAIL");
                String uuid = rs.getString("UUID");
                Date payTime = rs.getDate("PAYTIME");
                Date deadLine = rs.getDate("DEADLINE");
                int balance = rs.getInt("BALANCE");
                user = new User(userName, mail, uuid, payTime, deadLine, balance);
            }
            return user;
        }
    }

    public boolean isExistUser(User user) throws SQLException {
        String sql = "select * from USER where userName = ? and PASSWORD = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isExistName(String userName) throws SQLException {
        String sql = "select * from USER where USERNAME = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        }
    }

    public void addUser(User user) {
        String sql = "insert into USER (USERNAME, MAIL, PASSWORD) VALUE (?, ?, ?);";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getMail());
            ps.setString(3, user.getPassword());

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserWithUuid(User user) {
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
                Date payTime = rs.getDate("PAYTIME");
                Date deadLine = rs.getDate("DEADLINE");
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
