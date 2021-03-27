package com.meiken.service;

import com.meiken.dao.QTeacher;
import com.meiken.dao.Teacher;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

/**
 * @Author glf
 * @Date 2020/8/18
 */

@Service("meikenDaoImpl")
public class MeikenDaoImpl {

    @Autowired
    private SessionFactory mySessionFactory;

    private QTeacher qTeacher = QTeacher.teacher;


    @Transactional(rollbackFor = Exception.class)
    public Collection getTeacher(Long id) {

        List list = this.mySessionFactory.getCurrentSession()
                .createQuery("from Teacher where id=1").getResultList();
        return list;
    }


    public void saveOne(){
        Teacher teacher = new Teacher();
        teacher.setTid("10");
        teacher.setTname("ssh");

        EntityManager entityManager = mySessionFactory.createEntityManager();
        entityManager.getCriteriaBuilder();
        entityManager.getTransaction().begin();
        entityManager.persist( teacher );
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public void queryByQueryDsl(){
        Teacher teacher = new Teacher();
        teacher.setTid("10");
        teacher.setTname("ssh");

        EntityManager entityManager = mySessionFactory.createEntityManager();

        JPAQuery query = new JPAQuery(entityManager);
        query.select(qTeacher).from(qTeacher).where(qTeacher.id.isNotNull());
        List fetch = query.fetch();

        System.out.println(fetch);

//        entityManager.getCriteriaBuilder();
//        entityManager.getTransaction().begin();
//        entityManager.persist( teacher );
//        entityManager.getTransaction().commit();
//        entityManager.close();
    }
}
