package com.kuzin.service.mapper;

import com.kuzin.entity.Unit;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**unit mapper class.*/
public class UnitMapper implements RowMapper<Unit> {
    @Override
    public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
        Unit result = new Unit();
        result.setId(rs.getInt(1));
        result.setType(rs.getString(2));


        return result;
    }
}
