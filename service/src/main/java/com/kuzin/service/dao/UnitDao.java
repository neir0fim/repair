package com.kuzin.service.dao;

import com.kuzin.entity.Unit;
import com.kuzin.service.mapper.UnitMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**unit dao repository.*/

@Repository
public class UnitDao implements Dao<Unit> {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UnitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_UNIT_BY_ID = "SELECT * from units where unit_id = ?";
    private static final String SELECT_UNITS = "SELECT * from units";
    private static final String ADD_UNIT = "INSERT into units (unit_id, type) values (?, ?)";
    private static final String DELETE_UNIT = "DELETE from units where unit_id = ?";
    private static final String UPDATE = "UPDATE units set type = ? where unit_id = ?";


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
        jdbcTemplate.update(
                ADD_UNIT, unit.getId(), unit.getType());

        return unit;
    }

    public void update(Unit unit, long id) {
        jdbcTemplate.update(UPDATE, unit.getType(), id);
    }

    @Override
    public void delete(long unit) {
        jdbcTemplate.update(DELETE_UNIT, unit);
    }

}
