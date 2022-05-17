package com.kuzin.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/** dao class.*/
@Repository
public class ValidDao {
    private static final String GET_USER = "SELECT type from users where username = ?";
    private static final String SELECT_FROM_TYPE = "Select type from "
            + "article where article.article = ?";
    private static final String GET_TYPE = "Select type from "
            + "article where id = ?";
    private static final String GET_REPAIR_TYPE = "SELECT type from repair where repair_id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ValidDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void filter(String type) throws AccessException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userType = jdbcTemplate.queryForObject(GET_USER, String.class, auth.getName());

        assert userType != null;
        if (!userType.equals(type)) {
            throw new AccessException("you cannot interact with an "
                    + "object that is not part of your unit");
        }
    }

    public String getType(long id) {
        return jdbcTemplate.queryForObject(GET_TYPE, String.class, id);
    }

    public String getTypeByValue(String article) {
        return jdbcTemplate.queryForObject(SELECT_FROM_TYPE, String.class,
                article);
    }

    public String getRepairType(long id) {
        return jdbcTemplate.queryForObject(GET_REPAIR_TYPE, String.class, id);
    }



}
