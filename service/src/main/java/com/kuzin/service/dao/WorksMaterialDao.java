package com.kuzin.service.dao;


import com.kuzin.entity.WorksMaterial;
import com.kuzin.service.mapper.WorksMapper;
import java.util.ArrayList;
import java.util.List;
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


    public static final String ADD_MATERIAL = "INSERT INTO works"
            + " (cod, name, codDk, uom, value, amount, repair_id) VALUES (?, ?, ?, ?, ?, ?, ?) "
            + "returning id";
    public static final String GET_ALL = "SELECT * from works";
    public static final String GET_MATERIAL = "SELECT * from works where repair_id = ?";
    public static final String DELETE = "DELETE from works where id = ?";
    public static final String UPDATE = "UPDATE works set amount = ? where id = ?";
    public static final String GET_COD = "SELECT cod from works where repair_id = ?";


    public void deleteMaterial(long id) {
        jdbcTemplate.update(DELETE, id);
    }

    public List<WorksMaterial> getMaterials() {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL, new WorksMapper()));
    }

    public List<WorksMaterial> getMaterial(long id)  {
        return new ArrayList<>(jdbcTemplate.query(GET_MATERIAL, new WorksMapper(), id));
    }

    public void addMaterial(List<WorksMaterial> materials) {
        for (WorksMaterial worksMaterial : materials) {
            jdbcTemplate.update(ADD_MATERIAL,
                            worksMaterial.getCod(),
                            worksMaterial.getName(),
                            worksMaterial.getCodDk(),
                            worksMaterial.getUom(),
                            worksMaterial.getValue(),
                            worksMaterial.getAmount(),
                            worksMaterial.getRepairId());
        }
    }

    public void updateMaterial(double value, long id) {
        jdbcTemplate.update(UPDATE, value, id);
    }




}
