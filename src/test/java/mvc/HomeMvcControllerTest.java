package mvc;

import com.kuzin.entity.Article;
import com.kuzin.service.service.ArticleService;
import com.kuzin.service.service.MaterialService;
import com.kuzin.service.service.UnitService;
import com.kuzin.web.mvc.HomeMvcController;
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
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeMvcController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = HomeMvcController.class)
class HomeMvcControllerTest {

    @MockBean
    ArticleService articleService;

    @MockBean
    UnitService unitService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    Article result = new Article("1.2.2.2", 1, "RTM", 1);


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/getLogin"))
                .andExpect(view().name("/home/loginPage"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnHomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnLogoutPage() throws Exception {
        this.mockMvc.perform(get("/logoutPage"))
                .andExpect(view().name("logoutPage"));
    }



    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnDownloadPage() throws Exception {
        this.mockMvc.perform(get("/download"))
                .andExpect(view().name("/download"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnAdminPage() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andExpect(view().name("/admin/admin"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnSuppPage() throws Exception {
        this.mockMvc.perform(get("/supp"))
                .andExpect(view().name("supp/menu"));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnUserPage() throws Exception {
        when(articleService.getAll()).thenReturn(new ArrayList<>(List.of(result)));

        this.mockMvc.perform(get("/user"))
                .andExpect(view().name("/user/user"))
                .andExpect(model().attributeExists("articles"));
    }
}
