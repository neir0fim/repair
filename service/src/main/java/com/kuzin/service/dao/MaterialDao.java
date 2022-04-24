package com.kuzin.service.dao;


import com.kuzin.entity.Material;
import com.kuzin.service.mapper.MaterialsMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** material dao class.*/

@Repository
public class MaterialDao implements Dao<Material> {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public MaterialDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String INSERT_INTO_MATERIAL = "INSERT INTO material "
            + "(cod, name, coddk, uom, value) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_MATERIAL = "SELECT * from material where cod = ?";
    private static final String SELECT_MATERIALS = "SELECT * from  material";
    private static final String DELETE = "DELETE from material where cod = ?";


    @Override
    public Material get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_MATERIAL,
                new MaterialsMapper(), id)).stream().findAny().orElseThrow();
    }

    @Override
    public List<Material> getAll() {
        return new ArrayList<>(jdbcTemplate.query(SELECT_MATERIALS,
                new MaterialsMapper()));
    }

    @Override
    public Material save(Material material) {
        jdbcTemplate.update(INSERT_INTO_MATERIAL, material.getCod(),
                material.getName(), material.getCodDk(), material.getUom(),
                material.getValue());

        return material;
    }

    public void update(Material material, String[] params) {

    }

    @Override
    public void delete(long t) {
        jdbcTemplate.update(DELETE, t);
    }
}
