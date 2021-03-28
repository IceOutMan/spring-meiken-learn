package com.meiken;

import com.meiken.dao.UserMapper;
import com.meiken.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author glf
 * @Date 2021/3/28
 */
public class TestMain {

    public static void main(String[] args) {
//        insertUser();
        getUser();
    }

    public static void insertUser(){
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("mybatis",  2);
            mapper.insertUser(user);
            sqlSession.commit(); //提交
        }finally {
            sqlSession.close();
        }
    }

    public static void getUser(){
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUser(2);
            System.out.println("name: " + user.getName() + " ,age: " + user.getAge());

        }finally {
            sqlSession.close();
        }
    }
}
