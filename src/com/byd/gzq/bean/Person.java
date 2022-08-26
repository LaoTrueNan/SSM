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

}
