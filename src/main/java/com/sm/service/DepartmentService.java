package com.sm.service;

import com.sm.entity.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 查询所有院系
     * @return
     */
    List<Department> selectAll();

    /**
     * 根据id删除院系
     * @param id
     */
    void deleteDepartmentById(int id);

}