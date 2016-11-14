package com.qingqing.search.demo.domain;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public class Name {

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public Name(){}

    private String first;

    private String last;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
