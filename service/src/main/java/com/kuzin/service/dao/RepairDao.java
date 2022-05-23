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
    private static final String INSERT_INTO_REPAIR = "INSERT INTO repair "
            + "(description, article, type) values (?, ?, ?) returning repair_id";
    private static final String SELECT_FROM_TYPE = "Select type from "
            + "article where article.article = ?";
    private static final String DELETE_REPAIR = "DELETE from repair where repair_id = ?";
    private static final String SELECT_FOR_ARTICLE = "SELECT repair_id from "
            + "repair where article = ?";
    private static final String UPDATE = "UPDATE repair set description = ?, article = ?,"
            + " type = ? where repair_id = ?";
    private static final String GET_REPAIR_FOR_ARTICLE = "SELECT * from repair where article = ?";
    private static final String GET_REPAIR_TYPE = "SELECT type from repair where repair_id = ?";
    private static final String GET_ARTICLE_TYPE = "SELECT article.article"
            + " from article where id = ?";

    public Repair get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_REPAIR_USING_ID,
                new RepairMapper(), id)).stream().findAny().orElseThrow();
    }

    public List<Repair> getRepairForArticle(long id) {
        String type = getArticleType(id);

        return new ArrayList<>(jdbcTemplate.query(GET_REPAIR_FOR_ARTICLE,
                new RepairMapper(), type));
    }

    public Repair save(Repair repair) {
        String type = getType(repair.getArticle());

        repair.setId(Optional.ofNullable(jdbcTemplate.queryForObject(INSERT_INTO_REPAIR,
                Long.class, repair.getDescription(),
                repair.getArticle(), type)).stream().findAny().orElseThrow());

        return repair;
    }

    public int update(Repair repair, long id) {
        return jdbcTemplate.update(UPDATE, repair.getDescription(),
                repair.getArticle(), getTypeById(id), id);
    }

    public int delete(long id) {
        return jdbcTemplate.update(DELETE_REPAIR, id);
    }

    public List<Integer> repairForArticle(String article) {
        return new ArrayList<>(jdbcTemplate.queryForList(SELECT_FOR_ARTICLE,
                Integer.class, article));
    }

    public String getType(String article) {
        return jdbcTemplate.queryForObject(SELECT_FROM_TYPE, String.class,
                article);
    }

    public String getTypeById(long id) {
        return jdbcTemplate.queryForObject(GET_REPAIR_TYPE, String.class, id);
    }

    public String getArticleType(long id) {
        return jdbcTemplate.queryForObject(GET_ARTICLE_TYPE, String.class, id);
    }
}
