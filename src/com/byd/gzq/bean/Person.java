package com.byd.gzq.bean;

import com.byd.gzq.utils.GZQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//@Component
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
//    @Value("shenzhen")
    private String name;

    public Person() {
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


//    @GZQ(value = "注解值")
    public int calNameLength(){
        return name.length();
    }

}
