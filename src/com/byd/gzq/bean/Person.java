package com.byd.gzq.bean;

import com.byd.gzq.utils.GZQ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;


//@Component(value = "person2")
public class Person implements Serializable {
    private final Logger logger = LogManager.getLogger(Person.class);
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
        logger.warn("person全参数构造方法被调用");
    }

    private void writeObject(ObjectOutputStream o){
        try {
            o.write(this.getName().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream o){
        try {
            String s = (String) o.readObject();
            name = s;
            //            byte[] buf = new byte[1024];
//            StringBuilder sb = new StringBuilder();
//            while(o.available()){
//                o.read();
//            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
//
    public Person() {
//        this("_$#"+(int)(Math.random()*13000000));
    }

    private transient String l;

    public Person(String name) {
        this.name = name;
        logger.warn("person单参数构造方法被调用");
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
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", age=" + age +
                '}';
    }
}
