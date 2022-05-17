package com.kuzin.service.service;

import java.util.List;
import org.springframework.expression.AccessException;

/**service interface.*/
public interface ServiceEntity<T> {

    T get(long id) throws AccessException;

    List<T> getAll();

    void save(T t) throws AccessException;

    void delete(long t) throws AccessException;


}
