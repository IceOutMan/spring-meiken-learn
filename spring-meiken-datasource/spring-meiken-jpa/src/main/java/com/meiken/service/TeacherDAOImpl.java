package com.meiken.service;

import com.meiken.dao.QTeacher;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @Author glf
 * @Date 2020/8/19
 */
@Service("teacherDAOImpl")
public class TeacherDAOImpl {


    @Autowired
    private EntityManager entityManager;

    private QTeacher qTeacher = QTeacher.teacher;

    public void queryDsl(){

        JPAQuery query = new JPAQuery(entityManager);

        query.select(qTeacher).from(qTeacher).where(qTeacher.id.isNotNull());
        List fetch = query.fetch();

        System.out.println(fetch);
    }
}
