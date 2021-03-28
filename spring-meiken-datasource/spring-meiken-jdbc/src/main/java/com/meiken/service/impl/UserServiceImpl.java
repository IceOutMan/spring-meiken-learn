package com.meiken.service.impl;

import com.meiken.mapper.UserRowMapper;
import com.meiken.pojo.User;
import com.meiken.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.List;

/**
 * @Author glf
 * @Date 2021/3/28
 */
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        jdbcTemplate.update("insert into users(name,age) values(?,?)",
                new Object[]{user.getName(),user.getAge()},new int[]{Types.VARCHAR,Types.INTEGER});
    }

    @Override
    public List<User> getUsers() {
        List<User> list  = jdbcTemplate.query("select * from users",new UserRowMapper());
        return  list;
    }
}
