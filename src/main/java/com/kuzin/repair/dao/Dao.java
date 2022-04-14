package com.kuzin.repair.dao;

import java.util.Optional;
import java.util.List;


public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    T save(T t);

    void update(T t, String[] params);

    void delete(long t);


}
