package com.kuzin.service.dao;


import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**works dao class.*/

@Repository
public class WorksDao {

    JdbcTemplate jdbcTemplate;

    private static final String ADD_MATERIAL = "INSERT into works "
            + " (cod, name, coddk, uom, value, amount) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE from works where id = ?";

    @Autowired
    public WorksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public WorksMaterial get(long id) {
        return null;
    }

    public List<WorksMaterial> getForRepair(long id) {
        return null;
    }

    public List<WorksMaterial> getAll() {
        return null;
    }

    public WorksMaterial save(Material material, double amount) {
        WorksMaterial result = new WorksMaterial();

        String name = material.getName();
        int cod = material.getCod();
        String codDk = material.getCodDk();
        String uom = material.getUom();
        double value = material.getValue();

        result.setId(Optional.ofNullable(
                jdbcTemplate.queryForObject(ADD_MATERIAL, Long.class, cod, name, codDk, uom, value,
                        amount)).stream().findAny().orElseThrow());

        result.setCod(cod);
        result.setName(name);
        result.setCodDk(codDk);
        result.setUom(uom);
        result.setValue(value);
        result.setAmount(amount);

        return result;
    }

    public void update(WorksMaterial worksMaterial, String[] params) {

    }

    public void delete(long t) {
        jdbcTemplate.update(DELETE, t);
    }
}


