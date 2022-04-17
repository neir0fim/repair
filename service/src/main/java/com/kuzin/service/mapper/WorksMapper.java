package com.kuzin.service.mapper;

import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorksMapper implements RowMapper<List<WorksMaterial>> {


    @Override
    public List<WorksMaterial> mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
