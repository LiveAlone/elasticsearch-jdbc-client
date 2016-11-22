package com.qingqing.search.demo.domain;

import org.omg.CORBA.DoubleHolder;
import org.omg.CORBA.StringHolder;

/**
 * Created by yaoqijun.
 * Date:2016-11-14
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class Student {

    private Integer id;

    private String name;

    private Integer age;

    private Double grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(" id : ").append(id)
                .append(" name : ").append(name)
                .append(" age : ").append(age)
                .append(" grode : ").append(grade)
                .toString();
    }
}
