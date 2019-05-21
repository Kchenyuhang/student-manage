package com.sm.service;

import com.sm.utils.ResultEntity;

/**
 * 管理员业务逻辑接口
 */
public interface AdminService {
    /**
     * 管理员登录
     * @param account
     * @param password
     * @return ResultEntity
     */
    ResultEntity adminLogin(String account,String password);
}
