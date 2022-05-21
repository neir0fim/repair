package com.kuzin.service.mapper;

import com.kuzin.entity.Article;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/** article mapper class.*/
@Component
public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Article(rs.getString(3), rs.getInt(1),
                rs.getString(2), rs.getInt(4));
    }
}
