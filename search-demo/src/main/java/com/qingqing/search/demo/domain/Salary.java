package com.qingqing.search.demo.domain;

import java.io.Serializable;

/**
 * Created by yaoqijun on 2016/11/14.
 */
public class Salary implements Serializable{

    private Integer basic;

    private Integer improve;

    public Salary(Integer basic, Integer improve) {
        this.basic = basic;
        this.improve = improve;
    }

    public Salary(){}

    public Integer getBasic() {
        return basic;
    }

    public void setBasic(Integer basic) {
        this.basic = basic;
    }

    public Integer getImprove() {
        return improve;
    }

    public void setImprove(Integer improve) {
        this.improve = improve;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "basic=" + basic +
                ", improve=" + improve +
                '}';
    }
}
