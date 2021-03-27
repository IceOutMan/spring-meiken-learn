package com.meiken.service.impl;

import com.meiken.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.meiken.pojo.Student;
import com.meiken.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author glf
 * @Date 2020/8/18
 */
@Service("iStudentService")
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public List<Student> getAllStudent() {
        return   studentMapper.selectAll();
    }

    @Override
    public PageInfo getStudentByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);

        List<Student> studentList = studentMapper.selectAll();

        PageInfo pageResult = new PageInfo(studentList);
        pageResult.setList(studentList);
        return pageResult;
    }
}
