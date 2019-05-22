package com.sm.service.impl;

import com.sm.entity.Department;
import com.sm.factory.ServiceFactory;
import com.sm.service.DepartmentService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DepartmentServiceImplTest {

    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();

    @Test
    public void selectAll() {
        List<Department> departmentList = departmentService.selectAll();
        departmentList.forEach(department -> System.out.println(department));
    }

    @Test
    public void setDepartmentService() {
        int id = 4;
        departmentService.deleteDepartmentById(id);
    }

    @Test
    public void addDepartment() {
        Department department = new Department();
        department.setDepartmentName("测试院系");
        department.setLogo("https://student-manage99.oss-cn-hangzhou.aliyuncs.com/logo/e2792636-7f25-4b11-b469-fe2fe12165d9.jpg");
        int n = departmentService.addDepartment(department);
    }

}