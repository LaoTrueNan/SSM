package com.byd.gzq.service;

import com.byd.gzq.bean.Person;
import com.byd.gzq.dao.PersonMapper;
import com.byd.gzq.utils.GZQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leonard
 * @date 2022/10/11 13:07
 */
@Service
public class PersonService {

    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @GZQ("计数器")
    public int decrePersonNum(int id){
        Person person = personMapper.selectPersonById(id);
        int a = 1/0;
        if(person!=null && person.getAge()>0){
            return personMapper.deletePerson(id);
        }
        return 0;
    }

    public int changePersonInfo(Person person){
        return personMapper.updatePerson(person);
    }
}
