package com.kuzin.service.mapper;

import com.kuzin.entity.Material;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/** material mapper class.*/
public class MaterialsMapper implements RowMapper<Material> {
    @Override
    public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
        Material material = new Material();

        material.setCod(rs.getInt(1));
        material.setName(rs.getString(2));
        material.setCodDk(rs.getString(3));
        material.setUom(rs.getString(4));
        material.setValue(rs.getInt(5));

        return material;
    }
}
