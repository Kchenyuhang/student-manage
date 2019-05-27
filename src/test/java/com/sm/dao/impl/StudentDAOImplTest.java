package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();

    @Test
    public void selectAll() {
       List<StudentVO> students = null;
       try {
           students = studentDAO.selectAll();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       students.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(7);
        } catch (SQLException e) {
            System.out.println("查询出现异常");
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByClassId(12);
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByKeywords("陈");
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
}