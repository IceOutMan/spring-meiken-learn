package com.meiken.mapper;


import com.meiken.pojo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author glf
 * @Date 2021/3/28
 */
public class UserRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getInt("id"),rs.getString("name"),rs.getInt("age"));
        return user;
    }
}
