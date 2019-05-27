package com.sm.service.impl;

import com.sm.dao.DepartmentDAO;
import com.sm.entity.Department;
import com.sm.factory.DAOFactory;
import com.sm.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();

    @Override
    public List<Department> selectAll() {
        List<Department> departmentList = null;
        try {
            departmentList = departmentDAO.getAll();
        } catch (SQLException e) {
            System.err.print("查询院系信息出现异常");
        }
        return departmentList;
    }

    @Override
    public void deleteDepartmentById(int id) {
        try {
            departmentDAO.deleDepartmentById(id);
        } catch (SQLException e) {
            System.err.println("删除院系有异常");
        }
    }

    @Override
    public int addDepartment(Department department) {
        int n = 0;
        try {
            n = departmentDAO.insertDepartment(department);
        } catch (SQLException e) {
            System.err.println("新增院系薪资出现异常");
        }
        return n;
    }
}