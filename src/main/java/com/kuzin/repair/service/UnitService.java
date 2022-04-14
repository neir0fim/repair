package com.kuzin.repair.service;

import com.kuzin.repair.dao.UnitDao;
import com.kuzin.repair.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitService implements ServiceEntity<Unit> {

    UnitDao dao;

    @Autowired
    public UnitService(UnitDao dao) {
        this.dao = dao;
    }

    @Override
    public Unit get(long id) {
        return dao.get(id);
    }

    @Override
    public List<Unit> getAll() {
        return dao.getAll();
    }

    @Override
    public Unit save(Unit unit) {
        return dao.save(unit);
    }

    @Override
    public void delete(long t) {
        dao.delete(t);
    }
}
