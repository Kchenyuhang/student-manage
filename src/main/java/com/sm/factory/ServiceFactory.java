package com.sm.factory;

import com.sm.entity.Department;
import com.sm.service.AdminService;
import com.sm.service.DepartmentService;
import com.sm.service.impl.AdminServiceImpl;
import com.sm.service.impl.DepartmentServiceImpl;

public class ServiceFactory {
    public static AdminService getAdminServiceInstance() {
        return new AdminServiceImpl();
    }

    public static DepartmentService getDepartmentServiceInstance() {
        return new DepartmentServiceImpl();
    }
}