package com.kuzin.repair.dao;

import com.kuzin.repair.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
