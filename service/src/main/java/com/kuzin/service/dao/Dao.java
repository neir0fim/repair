package com.kuzin.service.dao;

import java.util.List;
import org.springframework.expression.AccessException;


/** interface for dao class.*/
public interface Dao<T> {

    T get(long id) throws AccessException;

    List<T> getAll();

    T save(T t);

    void delete(long t);


}
