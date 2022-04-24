package com.kuzin.service.dao;

import java.util.List;


/** interface for dao class.*/
public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    T save(T t);

    void delete(long t);


}
