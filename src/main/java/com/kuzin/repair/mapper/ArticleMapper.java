package com.kuzin.repair.mapper;

import com.kuzin.repair.entity.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Article(rs.getString(3), rs.getInt(1),
                rs.getString(2));
    }
}
