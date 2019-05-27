package com.sm.service.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import com.sm.factory.ServiceFactory;
import com.sm.service.CClassService;

import java.sql.SQLException;
import java.util.List;

public class CClassServiceImpl implements CClassService {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();

    @Override
    public List<CClass> selectByDepartmentId(int departmentId) {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            System.err.print("查询班级信息出现异常");
        }
        return  cClassList;
    }

    @Override
    public int insertClass(CClass cClass) {
        int n = 0;
        try {
            n = cClassDAO.insertClass(cClass);
        } catch (SQLException e) {
            System.err.println("新增班级出现异常");
        }
        return n;
    }

    @Override
    public void deleteClassById(int id) {
        try {
            cClassDAO.deleteClassById(id);
        } catch (SQLException e) {
            System.err.println("删除班级有异常");
        }
    }

    @Override
    public List<CClass> selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        return cClassList;
    }
}
