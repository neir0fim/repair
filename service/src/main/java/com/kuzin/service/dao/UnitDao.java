package com.kuzin.service.dao;

import com.kuzin.entity.Unit;
import com.kuzin.service.mapper.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UnitDao implements Dao<Unit> {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UnitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_UNIT_BY_ID = "SELECT * from units where unit_id = ?";
    private static final String SELECT_UNITS = "SELECT * from units";
    private static final String ADD_UNIT = "INSERT into units (type) values (?) returning unit_id";
    private static final String DELETE_UNIT = "DELETE from units where unit_id = ?";



    @Override
    public Unit get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_UNIT_BY_ID,
                new UnitMapper(), id)).stream().findAny().orElseThrow();
    }

    @Override
    public List<Unit> getAll() {
        return new ArrayList<>(jdbcTemplate.query(SELECT_UNITS, new UnitMapper()));
    }

    @Override
    public Unit save(Unit unit) {
        Unit result = new Unit();
        result.setType(unit.getType());
        result.setId(Optional.ofNullable(jdbcTemplate.queryForObject(
                ADD_UNIT, Long.class, unit.getType())).stream().findAny().orElseThrow());

        return result;
    }

    @Override
    public void update(Unit unit, String[] params) {

    }

    @Override
    public void delete(long unit) {
        jdbcTemplate.update(DELETE_UNIT, unit);
    }

}
