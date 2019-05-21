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
}