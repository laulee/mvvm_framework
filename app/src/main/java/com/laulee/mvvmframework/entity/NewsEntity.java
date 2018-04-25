package com.laulee.mvvmframework.entity;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class NewsEntity {
    private int idRes;
    private String title;

    public NewsEntity(int idRes, String title) {
        this.idRes = idRes;
        this.title = title;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
