package com.meiken.dao;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

/**
 * @Author glf
 * @Date 2020/8/18
 */
@Table(name = "Teacher")
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tid;

    private String tname;

    private String tPhone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }
}
