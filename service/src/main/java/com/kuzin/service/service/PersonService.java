package com.kuzin.service.service;

import static com.kuzin.service.service.addition.Validation.*;

import com.kuzin.entity.Person;
import com.kuzin.service.dao.PersonDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** person service class.*/

@Service
public class PersonService {

    PersonDao personDao;

    @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public UserDetails getUser(String userName) {
        validateString(userName);

        return personDao.loadUserByUsername(userName);
    }

    public List<UserDetails> getUsers() {
        return personDao.getUsers();
    }

    public void addUser(Person user) {
        validateString(user.getName());
        validateString(user.getPass());

        personDao.addUser(user);
    }


    public int delete(String name) {
        validateString(name);

        return personDao.deletePerson(name);
    }


    public void block(String name) {
        validateString(name);

        personDao.block(name);
    }

    public void unlock(String name) {
        validateString(name);

        personDao.unlock(name);
    }

    public void updatePerson(Person person, String name) {
        validateString(name);
        validateString(person.getPass());


        personDao.update(person, name);
    }

}
