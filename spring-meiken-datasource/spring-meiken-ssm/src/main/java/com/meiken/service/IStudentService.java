package com.meiken.service;

import com.github.pagehelper.PageInfo;
import com.meiken.pojo.Student;

import java.util.List;

/**
 * @Author glf
 * @Date 2020/8/18
 */
public interface IStudentService {


    public List<Student> getAllStudent();

    PageInfo getStudentByPage(Integer page,Integer size);
}
