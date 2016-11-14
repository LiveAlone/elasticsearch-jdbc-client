package com.qingqing.search.demo.domain;

/**
 * Created by yaoqijun on 2016/11/10.
 * es search bean content
 */
public class Teacher {

    private String id;

    private String name;

    private Integer age;

    private String salary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("id : ").append(id).append(" name : ").append(name).append(" age : ").append(age).toString();
    }
}
