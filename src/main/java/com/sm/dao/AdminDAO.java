package com.sm.dao;

import com.sm.entity.Admin;

import java.sql.SQLException;

/**
 * AdminDAO接口
 */
public interface AdminDAO {
    /**
     * 根据账号查找管理员
     * @param account
     * @return Admin
     * @throws SQLException
     */
    Admin getAdminByAccount(String account) throws SQLException;
}
