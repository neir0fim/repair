package mvc;

import com.kuzin.entity.Article;
import com.kuzin.service.service.ArticleService;
import com.kuzin.service.service.UnitService;
import com.kuzin.web.mvc.MvcController;
import com.kuzin.web.mvc.RepairMvcController;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RepairMvcController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RepairMvcController.class)
class RepairMvcControllerTest {
    @MockBean
    ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    Article result = new Article("1.2.2.2", 1, "RTM", 1);

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnRepairArticlePage() throws Exception {
        when(articleService.getAll()).thenReturn(new ArrayList<>(List.of(result)));

        this.mockMvc.perform(get("/repair"))
                .andExpect(view().name("repair/article"))
                .andExpect(model().attributeExists("articles"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnRepairPage() throws Exception {
        this.mockMvc.perform(get("/repair/get/{id}", 1))
                .andExpect(view().name("repair/repair"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnNewRepairPage() throws Exception {
        this.mockMvc.perform(get("/repair/new", 1))
                .andExpect(view().name("repair/new"))
                .andExpect(model().attributeExists("articles"));
    }

}
