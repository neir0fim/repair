package com.kuzin.service.dao;


import com.kuzin.entity.Article;
import com.kuzin.service.mapper.ArticleMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

/** article dao class.*/

@Repository
public class ArticleDao {

    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ARTICLES = "SELECT * from article";
    private static final String GET_ARTICLE = "SELECT * from article where id = ?";
    private static final String ADD_ARTICLE = "INSERT INTO article"
            + " (unit_id, type, article) VALUES (?, ?, ?) returning id";
    private static final String DELETE_ARTICLE = "DELETE from article where id = ?";
    private static final String GET_ALL_ARTICLES_FOR_UNIT = "SELECT * from "
            + "article where unit_id = ?";
    private static final String UPDATE = "UPDATE article set type = ?,"
            + " article = ? where id = ?";
    private static final String GET_ID = "Select unit_id from units where type = ?";
    private static final String GET_ALL_ARTICLE_BY_TYPE = "SELECT * from article where type = ?";
    private static final String GET_USER = "SELECT type from users where username = ?";

    @Autowired
    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Article get(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(GET_ARTICLE,
                new ArticleMapper(), id)).orElseThrow();
    }

    public List<Article> getAll() {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL_ARTICLES, new ArticleMapper()));
    }

    public Article save(Article article) {
        Article result = new Article();

        long unitId = Optional.ofNullable(jdbcTemplate.queryForObject(GET_ID,
                Long.class, article.getType())).stream().findAny().orElseThrow();

        result.setId(Optional.ofNullable(jdbcTemplate.queryForObject(
                ADD_ARTICLE, Long.class, unitId,
                article.getType(), article.getValue()))
                .stream().findAny().orElseThrow());

        result.setValue(article.getValue());
        result.setUnitId(unitId);
        result.setType(article.getType());

        return result;
    }

    public void update(Article article, long id) {
        jdbcTemplate.update(UPDATE, article.getType(),
                article.getValue(), id);
    }

    public int delete(long article) {
        return jdbcTemplate.update(DELETE_ARTICLE, article);
    }

    public List<Article> getForUnit(long id) {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL_ARTICLES_FOR_UNIT,
                new ArticleMapper(), id));
    }

    public List<Article> getForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userType = jdbcTemplate.queryForObject(GET_USER, String.class, auth.getName());

        return new ArrayList<>(jdbcTemplate.query(GET_ALL_ARTICLE_BY_TYPE,
                new ArticleMapper(), userType));
    }
}
