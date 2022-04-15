package com.kuzin.repair.dao;


import com.kuzin.repair.entity.Material;
import com.kuzin.repair.entity.WorksMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class WorksDao {

    JdbcTemplate jdbcTemplate;

    private static final String ADD_MATERIAL = "INSERT into works " +
            " (cod, name, coddk, uom, value, amount) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE from works where id = ?";

    @Autowired
    public WorksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public WorksMaterial get(long id) {
        return null;
    }

    public List<WorksMaterial> getAll() {
        return null;
    }

    public WorksMaterial save(Material material, double amount) {
        WorksMaterial result = new WorksMaterial();
        result.setId(Optional.ofNullable(
                jdbcTemplate.queryForObject(ADD_MATERIAL, Long.class,
                        material.getCod(), material.getName(), material.getCodDk(),
                        material.getUom(), material.getValue(),
                        amount)).stream().findAny().orElseThrow());
        result.setCod(material.getCod());
        result.setName(material.getName());
        result.setCodDk(material.getCodDk());
        result.setUom(material.getUom());
        result.setValue(material.getValue());
        result.setAmount(amount);

        return result;
    }

    public void update(WorksMaterial worksMaterial, String[] params) {

    }

    public void delete(long t) {
        jdbcTemplate.update(DELETE, t);
    }
}


