package com.byd.gzq.utils;

import com.byd.gzq.bean.Person;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 4466184
 * @date 2022/8/26 15:49
 */

public class PersonFactoryBean implements FactoryBean {
    private String name ;

    public PersonFactoryBean() {
        this.name="_$#"+(int)(Math.random()*13000000);
    }

    public PersonFactoryBean(String name) {
        this.name = name;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Object getObject() throws Exception {
        if(name!=null){
            return new Person(name);
        }else{
            return new Person();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
