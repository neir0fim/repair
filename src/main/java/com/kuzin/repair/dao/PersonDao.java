package com.kuzin.repair.dao;

import java.util.List;
import java.util.Optional;

public class PersonDao implements Dao{

    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(long o) {

    }
}
