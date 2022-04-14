package com.kuzin.repair.mapper;

import com.kuzin.repair.entity.Repair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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