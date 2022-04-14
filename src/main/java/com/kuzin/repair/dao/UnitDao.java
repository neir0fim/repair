package com.kuzin.repair.dao;

import com.kuzin.repair.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;



public class UnitDao implements Dao<Unit> {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UnitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String SELECT_UNIT_BY_ID = "";



    @Override
    public Unit get(long id) {
        return null;
    }

    @Override
    public List<Unit> getAll() {
        return null;
    }

    @Override
    public Unit save(Unit unit) {
        return null;
    }

    @Override
    public void update(Unit unit, String[] params) {

    }

    @Override
    public void delete(long unit) {

    }

}
