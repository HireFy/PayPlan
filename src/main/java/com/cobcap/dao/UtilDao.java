package com.cobcap.dao;

import java.sql.*;

public class UtilDao {
    private String dbUrl = "localhost:3306";
    private String dbName = "/PayPlan";
    private String dbUser = "root";
    private String dbPassword = "linuxvimf";

    public UtilDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + dbUrl + dbName, dbUser, dbPassword);
    }

    public boolean isExistMail(String mail) throws SQLException {
        ResultSet rs = null;
        String sql = "select * from USER where MAIL = ?;";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mail);

            rs = ps.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}
