package com.sm.dao.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.entity.Admin;
import com.sm.entity.Department;
import com.sm.utils.JDBCUtil;

import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public List<Department> getAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_department ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Department> departmentList = new ArrayList<>();
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setDepartmentName(rs.getString("department_name"));
            department.setLogo(rs.getString("logo"));
            departmentList.add(department);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return departmentList;
    }

    @Override
    public int deleDepartmentById(long id) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE from t_department WHERE id = " + id;
        PreparedStatement pstmt = connection.prepareStatement(sql);
        int n = pstmt.executeUpdate();
        //不管返回几行数据，JDBC返回的都是ResultSet型
        pstmt.close();
        jdbcUtil.closeConnection();
        return n;
    }

//    @Override
//    public int deleDepartmrntById(long id) throws SQLException {
//        return 0;
//    }

}

