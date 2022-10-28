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

    private PersonMapper mapper;

    @Autowired
    public PersonService(PersonMapper personMapper) {
        this.mapper = personMapper;
    }

    @GZQ("计数器")
    public int decrePersonNum(int id){
        return mapper.deletePerson(id);
    }

    public int changePersonInfo(Person person){
        return mapper.updatePerson(person);
    }
}
