package com.sm.dao;

import com.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * 院系DAO接口
 */
public interface DepartmentDAO {
    /**
     * 查询所有院系
     * @return List<Department>
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    /**
     * 根据id删除院系
     * @param id
     * @return
     * @throws SQLException
     */
    int deleDepartmentById(long id) throws SQLException;
}

