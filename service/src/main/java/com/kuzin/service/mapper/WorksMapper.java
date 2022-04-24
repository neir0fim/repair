package com.kuzin.service.mapper;


import com.kuzin.entity.WorksMaterial;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/** works mapper class.*/
public class WorksMapper implements RowMapper<WorksMaterial> {

    @Override
    public WorksMaterial mapRow(ResultSet rs, int rowNum) throws SQLException {
        WorksMaterial result = new WorksMaterial();
        result.setId(rs.getInt(1));
        result.setCod(rs.getInt(2));
        result.setName(rs.getString(3));
        result.setCodDk(rs.getString(4));
        result.setUom(rs.getString(5));
        result.setValue(rs.getInt(6));
        result.setAmount(rs.getInt(7));
        result.setRepairId(rs.getInt(8));

        return result;
    }
}
