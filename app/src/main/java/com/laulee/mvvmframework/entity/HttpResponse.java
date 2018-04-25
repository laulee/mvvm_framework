package com.laulee.mvvmframework.entity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class HttpResponse<T>{

    private boolean error;

    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
