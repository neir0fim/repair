package com.kuzin.repair.dao;


import com.kuzin.repair.entity.Article;
import com.kuzin.repair.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleDao implements Dao<Article> {

    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ARTICLES = "SELECT * from article";



    @Autowired
    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Article get(long id) {
        return null;
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL_ARTICLES, new ArticleMapper()));
    }

    @Override
    public Article save(Article article) {

        return null;
    }

    @Override
    public void update(Article article, String[] params) {

    }

    @Override
    public void delete(long article) {

    }
}
