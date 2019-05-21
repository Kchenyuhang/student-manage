package com.sm.dao.impl;

import com.sm.dao.AdminDAO;
import com.sm.entity.Admin;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
    @Override
    public Admin getAdminByAccount(String account) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_admin WHERE account = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account);
        ResultSet rs = pstmt.executeQuery();
        Admin admin = null;
        while (rs.next()) {
            int id = rs.getInt("id");
            String adminAccount = rs.getString("account");
            String password = rs.getString("password");
            String adminName = rs.getString("admin_name");
            admin = new Admin();
            admin.setId(id);
            admin.setAccount(adminAccount);
            admin.setPassword(password);
            admin.setAdminName(adminName);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return admin;
    }
}
