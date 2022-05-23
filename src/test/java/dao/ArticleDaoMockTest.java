package dao;

import com.kuzin.entity.Article;
import com.kuzin.entity.Person;
import com.kuzin.entity.enums.ApplicationUserRole;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class ArticleDaoMockTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    ArticleDao articleDao;

    private static final String ADD_ARTICLE = "INSERT INTO article"
            + " (unit_id, type, article) VALUES (?, ?, ?) returning id";
    private static final String GET_ID = "Select unit_id from units where type = ?";
    private static final String GET_USER = "SELECT type from users where username = ?";
    private static final String GET_ALL_ARTICLE_BY_TYPE = "SELECT * from article where type = ?";



    @Test
    void shouldSaveArticle() {
        Article save = new Article();
        save.setValue("1.2.2.2");
        save.setType("RTM");

        Article result = new Article("1.2.2.2", 1, "RTM", 1);

        when(jdbcTemplate.queryForObject(GET_ID,
                Long.class, save.getType())).thenReturn(1L);

        when(jdbcTemplate.queryForObject(
                ADD_ARTICLE, Long.class, 1L,
                save.getType(), save.getValue())).thenReturn(1L);

        assertEquals(result, articleDao.save(save));
    }

}
