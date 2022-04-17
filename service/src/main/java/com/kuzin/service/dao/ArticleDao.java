package com.kuzin.service.dao;


import com.kuzin.entity.Article;
import com.kuzin.service.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDao implements Dao<Article> {

    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ARTICLES = "SELECT * from article";
    private static final String GET_ARTICLE = "SELECT * from article where id = ?";
    private static final String ADD_ARTICLE = "INSERT INTO article"
            + " (unit_id, type, article) VALUES (?, ?, ?) returning id";
    private static final String DELETE_ARTICLE = "DELETE from article where id = ?";
    private static final String GET_ALL_ARTICLES_FOR_UNIT = "SELECT * from article where unit_id = ?";


    @Autowired
    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Article get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(GET_ARTICLE,
                new ArticleMapper(), id)).orElseThrow();
    }

    @Override
    public List<Article> getAll() {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL_ARTICLES, new ArticleMapper()));
    }

    @Override
    public Article save(Article article) {
        Article result = new Article();
        result.setId(Optional.ofNullable(jdbcTemplate.queryForObject(
                ADD_ARTICLE, Long.class, article.getUnitId(),
                article.getType(), article.getValue()))
                .stream().findAny().orElseThrow());

        result.setValue(article.getValue());
        result.setUnitId(article.getUnitId());
        result.setType(article.getType());

        return result;
    }

    @Override
    public void update(Article article, String[] params) {

    }

    @Override
    public void delete(long article) {
        jdbcTemplate.update(DELETE_ARTICLE, article);
    }

    public List<Article> getForUnit(long id) {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL_ARTICLES_FOR_UNIT,
                new ArticleMapper(), id));
    }
}
