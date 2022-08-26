package com.byd.gzq.utils;

import com.byd.gzq.bean.Person;

/**
 * @author 4466184
 * @date 2022/8/26 15:37
 */

public class PersonFactory {

    private String name;

    public PersonFactory(String name) {
        this.name = name;
    }

    public Person getPerson(){
        if(name!=null){
            return new Person(name);
        }else{
            return new Person();
        }
    }

}
