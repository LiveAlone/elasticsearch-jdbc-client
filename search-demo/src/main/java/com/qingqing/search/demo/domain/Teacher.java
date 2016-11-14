package com.qingqing.search.demo.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by yaoqijun on 2016/11/10.
 * es search bean content
 */
public class Teacher {

    private String id;

    private Name name;

    private Integer age;

    private List<Salary> salary;

    public List<Salary> getSalary() {
        return salary;
    }

    public void setSalary(List<Salary> salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("id : ").append(id).append(" name : ").append(name).append(" age : ").append(age).toString();
    }
}
