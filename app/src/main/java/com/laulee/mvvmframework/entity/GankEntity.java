package com.laulee.mvvmframework.entity;

import java.util.List;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class GankEntity {


    /**
     * _id : 5a7cf9e7421aa90d21aa114b
     * createdAt : 2018-02-09T09:31:19.687Z
     * desc : 开源的 markdown 编辑器
     * images : ["http://img.gank.io/760970ea-daae-4b98-9f87-deecdd3fe1f7","http://img.gank.io/ea49dc41-3435-4126-ab5b-d7b3357ab517"]
     * publishedAt : 2018-02-22T08:24:35.209Z
     * source : chrome
     * type : Android
     * url : https://github.com/zeleven/mua
     * used : true
     * who : 蒋朋
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
