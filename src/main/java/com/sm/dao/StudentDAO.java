package com.sm.dao;

import com.sm.entity.StudentVO;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    /**
     * 查询所有学生（视图对象）
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectAll() throws SQLException;

    /**
     * 根据院系id查询所有学生
     * @param departmentId
     * @return List<StudentVo>
     * @throws SQLException
     */
    List<StudentVO> selectByDepartmentId(int departmentId) throws SQLException;

    /**
     * 根据班级id查询所有学生
     * @param classId
     * @return List<StudentVO>
     * @throws SQLException
     */
    List<StudentVO> selectByClassId(int classId) throws SQLException;

    /**
     * 根据关键词模糊查询
     * @param keywords
     * @return List<Student>
     * @throws SQLException
     */
    List<StudentVO> selectByKeywords(String keywords) throws SQLException;
}
