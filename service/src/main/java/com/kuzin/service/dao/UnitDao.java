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
public class UnitDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UnitDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_UNIT_BY_ID = "SELECT * from units where unit_id = ?";
    private static final String SELECT_UNITS = "SELECT * from units";
    private static final String ADD_UNIT = "INSERT into units (type) values (?) returning unit_id";
    private static final String DELETE_UNIT = "DELETE from units where unit_id = ?";
    private static final String UPDATE = "UPDATE units set type = ? where unit_id = ?";

    public Unit get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_UNIT_BY_ID,
                new UnitMapper(), id)).stream().findAny().orElseThrow();
    }

    public List<Unit> getAll() {
        return new ArrayList<>(jdbcTemplate.query(SELECT_UNITS, new UnitMapper()));
    }

    public long save(Unit unit) {
        unit.setId(Optional.ofNullable(jdbcTemplate.queryForObject(
                ADD_UNIT, Long.class, unit.getKind())).stream().findAny().orElseThrow());

        return unit.getId();
    }

    public void update(Unit unit, long id) {
        jdbcTemplate.update(UPDATE, unit.getKind(), id);
    }

    public int delete(long unit) {
        return jdbcTemplate.update(DELETE_UNIT, unit);
    }

}
