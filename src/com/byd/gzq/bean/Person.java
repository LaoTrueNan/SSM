package com.byd.gzq.bean;

import com.byd.gzq.utils.GZQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//@Component(value = "person2")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Value("shenzhen")
    private String name;
    private int id;
    private String text;
    private int age;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int id, String text, int age) {
        this.name = name;
        this.id = id;
        this.text = text;
        this.age = age;
    }

    public Person() {
        this("_$#"+(int)(Math.random()*13000000));
    }

    private transient String l;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String replaced(){
        return name;
    }
    @GZQ(value = "该方法返回name属性的长度")
    public int calNameLength(){
        return name.length();
    }

    @Override
    public String toString() {
        return "person's name is "+name;
    }
}
