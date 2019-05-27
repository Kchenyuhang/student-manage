package com.sm.service;

import com.sm.entity.StudentVO;

import java.util.List;

public interface StudentService {
    /**
     * 查询所有学生
     * @return
     */
    List<StudentVO> selectAll();

    /**
     * 根据院系id查询学生
     * @return
     */
    List<StudentVO> selectByDepartmentId(int id);

    /**
     * 根据班级id查询学生
     * @return
     */
    List<StudentVO> selectByClassId(int id);

    /**
     * 根据关键词查询学生
     * @return
     */
    List<StudentVO> selectByKeywords(String keywords);
}
