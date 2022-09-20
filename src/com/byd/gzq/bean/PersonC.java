package com.byd.gzq.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;

/**
 * @author 4466184
 * @date 2022/8/26 16:15
 */

public class PersonC implements BeanFactoryAware {

    private BeanFactory ioc;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        ioc = beanFactory;
    }

    private Person person;

    public Person getPerson() {
        return ioc.getBean("person",Person.class);
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}



//public class PersonC{
//    private ObjectFactory<Person> objectFactory;
//
//
//    // 使用ObjectFactoryCreatingFactoryBean的好处是隔离了用户对BeanFactory的直接引用
////    ServiceLocatorFactoryBean的使用方法和ObjectFactoryCreatingFactoryBean差不多
//    public void setObjectFactory(ObjectFactory<Person> objectFactory) {
//        this.objectFactory = objectFactory;
//    }
//
//    public Person getPerson() {
//        return objectFactory.getObject();
//    }
//}
