package com.sm.dao.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDAOImplTest {
    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();

    @Test
    public void getAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        departmentList.forEach(department -> System.out.println(department));
    }

    @Test
    public void deleDepartmentById() {
        int id = 2;
        try {
            departmentDAO.deleDepartmentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insertDepartment() {
        Department department = new Department();
        department.setDepartmentName("测试院系");
        department.setLogo("\n" +
                "https://student-manage99.oss-cn-hangzhou.aliyuncs.com/logo/e2792636-7f25-4b11-b469-fe2fe12165d9.jpg");
                department.setDescription("测试院系的介绍");
        try {
            int n = departmentDAO.insertDepartment(department);
            assertEquals(1, n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}