package mvc;


import com.kuzin.service.service.ArticleService;
import com.kuzin.web.mvc.ArticleMvcController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleMvcController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ArticleMvcController.class)
class ArticleMvcControllerTest {

    @MockBean
    ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnArticlePage() throws Exception {
        this.mockMvc.perform(get("/articles"))
                .andExpect(view().name("article/articles"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnReportPage() throws Exception {
        this.mockMvc.perform(get("/report/Article"))
                .andExpect(view().name("article/report"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnFilterPage() throws Exception {
        this.mockMvc.perform(get("/filter/Repair"))
                .andExpect(view().name("article/filterRepair"));
    }




}


