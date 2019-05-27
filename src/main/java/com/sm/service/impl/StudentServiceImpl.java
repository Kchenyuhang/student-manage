package com.sm.service.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import com.sm.service.StudentService;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();
    @Override
    public List<StudentVO> selectAll() {
        List<StudentVO> students = null;
        try {
            students = studentDAO.selectAll();
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        return students;
    }

    @Override
    public List<StudentVO> selectByDepartmentId(int id) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(id);
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByClassId(int id) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByClassId(id);
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        return studentVOList;
    }

    @Override
    public List<StudentVO> selectByKeywords(String keywords) {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByKeywords(keywords);
        } catch (SQLException e) {
            System.err.println("查询出现异常");
        }
        return studentVOList;
    }
}
