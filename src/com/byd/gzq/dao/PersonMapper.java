package com.byd.gzq.dao;

import com.byd.gzq.bean.Person;

import java.util.List;

/**
 * @author 4466184
 * @date 2022/9/20 16:13
 */

public interface PersonMapper {
    List<Person> selectPersons();
    Person selectPersonById(int id);
    int insertPerson(Person person);

    int deletePerson(int id);
}
