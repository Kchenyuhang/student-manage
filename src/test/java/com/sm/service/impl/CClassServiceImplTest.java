package com.sm.service.impl;

import com.sm.entity.CClass;
import com.sm.factory.ServiceFactory;
import com.sm.service.CClassService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CClassServiceImplTest {
    private CClassService cClassService = ServiceFactory.getCClassServiceInstance();

    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = cClassService.selectByDepartmentId(2);
        cClassList.forEach(cClass -> System.out.println(cClass.getClassName()));
    }

    @Test
    public void insertClass() {
        CClass cClass = new CClass();
        cClass.setDepartmentId(2);
        cClass.setClassName("测试1721");
        int n = cClassService.insertClass(cClass);
    }

    @Test
    public void setcClassService() {
        int id = 13;
        cClassService.deleteClassById(id);
    }

    @Test
    public void selectAll() {
        List<CClass> cClassList = cClassService.selectAll();
        cClassList.forEach(CClass -> System.out.println(CClass));
    }
}