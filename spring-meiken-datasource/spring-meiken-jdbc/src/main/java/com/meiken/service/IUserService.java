package com.meiken.service;

import com.meiken.pojo.User;

import java.util.List;

/**
 * @Author glf
 * @Date 2021/3/28
 */
public interface IUserService {
    public void save(User user);
    public List<User> getUsers();
}
