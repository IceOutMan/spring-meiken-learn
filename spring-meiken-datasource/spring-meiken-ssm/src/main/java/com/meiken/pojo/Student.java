package com.meiken.pojo;

import java.util.Date;

public class Student {
    private Integer id;

    private String sid;

    private String sname;

    private Date sage;

    private String ssex;

    public Student(Integer id, String sid, String sname, Date sage, String ssex) {
        this.id = id;
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.ssex = ssex;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Date getSage() {
        return sage;
    }

    public void setSage(Date sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex == null ? null : ssex.trim();
    }
}