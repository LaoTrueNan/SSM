package com.byd.gzq.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
    private String name;

    @Autowired
    private City city;

    public Person() {
        System.out.println(111);
    }


    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
