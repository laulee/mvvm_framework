package com.laulee.mvvmframework.entity;

import com.framework.core.network.retrofit.HttpResponse;

import java.util.List;

/**
 * Created by laulee on 2018/4/28.
 */

public class GankResponse extends HttpResponse {

    private List<GankEntity> results;

    public List<GankEntity> getResults() {
        return results;
    }

    public void setResults(List<GankEntity> results) {
        this.results = results;
    }
}
