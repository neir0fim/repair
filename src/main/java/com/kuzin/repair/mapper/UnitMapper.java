package com.kuzin.repair.mapper;

import com.kuzin.repair.entity.Unit;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnitMapper implements RowMapper<Unit> {
    @Override
    public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Unit result = new Unit();
        result.setId(rs.getInt(1));
        result.setType(rs.getString(2));


        return result;
    }
}
