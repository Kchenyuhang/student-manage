package com.sm.service;

import com.sm.entity.CClass;

import java.util.List;

public interface CClassService {
    /**
     * 查询班级
     * @param departmentId
     * @return departmentId
     */
    List<CClass> selectByDepartmentId(int departmentId);

    /**
     * 新增班级
     * @param cClass
     * @return int
     */
    int insertClass(CClass cClass);

    /**
     * 根据id删除班级
     * @param id
     */
    void deleteClassById(int id);

    List<CClass> selectAll();
}