package com.kuzin.service.dao;

import com.kuzin.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao implements Dao<Person> {


    @Override
    public Person get(long id) {
        return null;
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person save(Person person) {
        return null;
    }

    @Override
    public void update(Person person, String[] params) {

    }

    @Override
    public void delete(long t) {

    }

    public List<Person> getPersonUnit(long id) {

        return null;
    }

}
