package com.kuzin.service.service;

import java.util.List;

public interface ServiceEntity<T> {

    T get(long id);

    List<T> getAll();

    T save(T t);

    void delete(long t);


}
