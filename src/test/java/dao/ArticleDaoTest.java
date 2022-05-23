package dao;

import com.kuzin.entity.Article;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.ArticleDao;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = RepairApplication.class)
@DataJdbcTest
class ArticleDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    ArticleDao articleDao;


    @BeforeEach
    void setUp() {
        articleDao = new ArticleDao(jdbcTemplate);
    }

    Article article = new Article("1.3.4.2.4.1", 1, "RTM", 1);

    @Test
    void shouldReturnArticleById() {
        assertEquals(article, articleDao.get(1));
    }

    @Test
    void shouldReturnExceptionIfArticleNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> articleDao.get(12));
    }

    @Test
    void shouldReturnListOfArticle() {
        assertEquals(new ArrayList<>(List.of(article)), articleDao.getAll());
    }

    @Test
    void shouldReturnIntAfterUpdate() {
        assertEquals(1, articleDao.update(article, 1L));
    }

    @Test
    void shouldReturnListOfArticleForUnit() {
        assertEquals(new ArrayList<>(List.of(article)), articleDao.getForUnit(1L));
    }

    @Test
    void shouldReturnIntAfterDelete() {
        assertEquals(1, articleDao.delete(1L));
    }


}

