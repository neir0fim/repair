package com.kuzin.service.dao;


import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.entity.enums.MaterialPost;
import com.kuzin.service.mapper.MaterialsMapper;
import com.kuzin.service.mapper.WorksMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/** works dao class.*/
@Repository
public class WorksMaterialDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public WorksMaterialDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String ADD_MATERIAL = "INSERT INTO works"
            + " (cod, name, codDk, uom, value, amount, repair_id) VALUES (?, ?, ?, ?, ?, ?, ?) ";
    private static final String GET_MATERIAL_BY_CODE = "SELECT * from material where cod = ?";
    private static final String GET_MATERIAL = "SELECT * from works where repair_id = ?";
    private static final String DELETE = "DELETE from works where id = ?";
    private static final String UPDATE = "UPDATE works set amount = ? where id = ?";
    private static final String DELETE_FOR_REPAIR = "DELETE from works where repair_id = ?";

    public int deleteMaterial(long id) {
        return jdbcTemplate.update(DELETE, id);
    }

    public int deleteAll(long id) {
        return jdbcTemplate.update(DELETE_FOR_REPAIR, id);
    }

    public List<WorksMaterial> getMaterial(long id)  {
        return new ArrayList<>(jdbcTemplate.query(GET_MATERIAL, new WorksMapper(), id));
    }

    public void addMaterial(MaterialPost worksMaterial) {
        Material material = Optional.ofNullable(jdbcTemplate.queryForObject(GET_MATERIAL_BY_CODE,
                new MaterialsMapper(), worksMaterial.getCode())).stream().findAny().orElseThrow();

        jdbcTemplate.update(ADD_MATERIAL,
                worksMaterial.getCode(),
                material.getName(),
                material.getCodDk(),
                material.getUom(),
                material.getValue(),
                worksMaterial.getAmount(),
                worksMaterial.getRepairId());
    }

    public int updateMaterial(WorksMaterial worksMaterial) {
        return jdbcTemplate.update(UPDATE, worksMaterial.getAmount(), worksMaterial.getId());
    }

}
