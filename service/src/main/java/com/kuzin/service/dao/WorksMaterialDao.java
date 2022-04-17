package com.kuzin.service.dao;

import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WorksMaterialDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public WorksMaterialDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public static final String ADD_MATERIAL = "INSERT INTO works"
            + " (cod, name, codDk, uom, value, amount, repair_id) VALUES (?, ?, ?, ?, ?, ?, ?) ";
    public static final String GET_MATERIAL = "";


    public void save(long id, List<Material> materialList) {

    }

    public List<WorksMaterial> getMaterial (long id)  {


        return null;
    }

    public void addMaterial(Material material, long amount, long id) {
        jdbcTemplate.update(ADD_MATERIAL,
                material.getCod(),
                material.getName(),
                material.getCodDk(),
                material.getUom(),
                material.getValue(),
                amount, id);
    }

}
