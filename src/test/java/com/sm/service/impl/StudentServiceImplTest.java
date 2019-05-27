package com.sm.service.impl;

import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.StudentService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceImplTest {
    private StudentService studentService = ServiceFactory.getStudentServiceInstance();

    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = studentService.selectAll();
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = studentService.selectByDepartmentId(1);
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentVOList = studentService.selectByClassId(12);
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = studentService.selectByKeywords("陈");
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
}