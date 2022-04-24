package com.kuzin.service.dao;


import com.kuzin.entity.Repair;
import com.kuzin.service.mapper.RepairMapper;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**repair dao class.*/
@Repository
public class RepairDao {


    WorksMaterialDao materialDao;
    JdbcTemplate jdbcTemplate;


    @Autowired
    public RepairDao(WorksMaterialDao materialDao, JdbcTemplate jdbcTemplate) {
        this.materialDao = materialDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SELECT_REPAIR_USING_ID = "SELECT * from "
            + "repair WHERE repair_id = ? ";
    private static final String SELECT_ALL_REPAIR = "SELECT * from repair";
    private static final String INSERT_INTO_REPAIR = "INSERT INTO repair "
            + "(description, article, type) values (?, ?, ?) returning repair_id";
    private static final String SELECT_FROM_TYPE = "Select type from "
            + "article where article.article = ?";
    private static final String DELETE_REPAIR = "DELETE from repair where repair_id = ?";
    private static final String SELECT_FOR_ARTICLE = "SELECT repair_id from repair";
    private static final String UPDATE = "UPDATE repair set description = ?, article = ?,"
            + " type = ? where repair_id = ?";

    public Repair get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_REPAIR_USING_ID,
                new RepairMapper(), id)).stream().findAny().orElseThrow();
    }

    public List<Repair> getAll() {
        return new ArrayList<>(jdbcTemplate.query(SELECT_ALL_REPAIR, new RepairMapper()));
    }

    public Repair save(Repair repair) {
        Repair result = new Repair();
        String type = jdbcTemplate.queryForObject(SELECT_FROM_TYPE, String.class,
                repair.getArticle());

        result.setId(Optional.ofNullable(jdbcTemplate.queryForObject(INSERT_INTO_REPAIR,
                Long.class, repair.getDescription(),
                repair.getArticle(), type)).stream().findAny().orElseThrow());
        result.setDescription(repair.getDescription());
        result.setArticle(repair.getArticle());
        result.setType(type);

        return result;
    }

    public void update(Repair repair, long id) {
        jdbcTemplate.update(UPDATE, repair.getDescription(),
                repair.getArticle(), repair.getType(), id);
    }

    public void delete(long id) {
        jdbcTemplate.update(DELETE_REPAIR, id);
    }

    public List<Integer> repairForArticle(long id) {
        return new ArrayList<>(jdbcTemplate.queryForObject(SELECT_FOR_ARTICLE, Integer.class, id));
    }

}
