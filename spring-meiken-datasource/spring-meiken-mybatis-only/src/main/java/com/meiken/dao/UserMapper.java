package com.meiken.dao;

import com.meiken.pojo.User;

/**
 * @Author glf
 * @Date 2021/3/28
 */
public interface UserMapper {
    public void insertUser(User user);
    public User getUser(Integer id);
}
