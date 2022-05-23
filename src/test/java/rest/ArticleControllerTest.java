package rest;

import com.kuzin.entity.Article;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.service.ArticleService;
import com.kuzin.web.rest.ArticleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(ArticleController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ArticleService.class})
class ArticleControllerTest {

    @MockBean
    ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    Article article = new Article("1.3.4.2.4.1", 1, "RTM", 1);


    @Test
    void shouldReturnArticle() {

    }

}
