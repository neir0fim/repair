package com.kuzin.service.mapper;

import com.kuzin.entity.Repair;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/** repair mapper class.*/
public class RepairMapper implements RowMapper<Repair> {

    @Override
    public Repair mapRow(ResultSet rs, int rowNum) throws SQLException {
        Repair repair = new Repair();

        repair.setId(rs.getInt(1));
        repair.setDescription(rs.getString(2));
        repair.setArticle(rs.getString(3));
        repair.setType(rs.getString(4));

        return repair;
    }
}
