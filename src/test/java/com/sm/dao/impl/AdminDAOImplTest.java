package com.sm.dao.impl;

import com.sm.dao.AdminDAO;
import com.sm.entity.Admin;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDAOImplTest {
    private AdminDAO adminDAO = DAOFactory.getAdminDAOInstance();

    @Test
    public void getAdminByAccount() {
        try {
            Admin admin = adminDAO.getAdminByAccount("123456@qq.com");
            if (admin != null) {
                System.out.println(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}