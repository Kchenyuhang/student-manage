package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CClassDAOImplTest {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();

    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass.getClassName()));
    }

    @Test
    public void insertClass() {
        CClass cClass = new CClass();
        cClass.setDepartmentId(2);
        cClass.setClassName("测试1811");
        try {
            int n = cClassDAO.insertClass(cClass);
            assertEquals(1,n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteClassById() {
        int id = 39;
        try {
            cClassDAO.deleteClassById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(CClass -> System.out.println(CClass));
    }
}