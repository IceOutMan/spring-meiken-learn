package com.meiken.pojo;

public class Teacher {
    private Integer id;

    private String tid;

    private String tname;

    public Teacher(Integer id, String tid, String tname) {
        this.id = id;
        this.tid = tid;
        this.tname = tname;
    }

    public Teacher() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }
}