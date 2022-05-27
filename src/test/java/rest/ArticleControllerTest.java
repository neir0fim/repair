package rest;

import com.google.gson.Gson;
import com.kuzin.entity.Article;
import com.kuzin.entity.Repair;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.service.service.ArticleService;
import com.kuzin.web.rest.ArticleController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.List;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ArticleController.class)
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
    WorksMaterial worksMaterial = new WorksMaterial(1232,
            "Pipe 420x8 st.3 sp.5", "31100000-7", "r.m.",
            48900, 132, 1);

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnArticles() throws Exception {
        when(articleService.getAll()).thenReturn(new ArrayList<>(List.of(article)));

        this.mockMvc.perform(get("/articles/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].value").value("1.3.4.2.4.1"));

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnArticle() throws Exception {
        when(articleService.get(1)).thenReturn(article);

        this.mockMvc.perform(get("/articles/api/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("1.3.4.2.4.1"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldSaveArticle() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(article);

        MockHttpServletRequestBuilder builder =
                post("/articles/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                                .with(csrf());


        when(articleService.save(article)).thenReturn(article);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnOkAfterDeleteSuccess() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/articles/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(articleService.delete(1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnBadAfterDeleteError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/articles/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(articleService.delete(1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnOkAfterUpdate() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(article);

        MockHttpServletRequestBuilder builder =
                patch("/articles/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(articleService.update(article, 1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnBackAfterUpdate() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(article);

        MockHttpServletRequestBuilder builder =
                patch("/articles/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(articleService.update(article, 1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnReport() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/articles/api/report/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                            .with(csrf());

        when(articleService.getReport(1))
                .thenReturn(new ArrayList<>(List.of(worksMaterial)));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnReportRepair() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/articles/api/description/{id}/{filter}",
                        1, "check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());

        when(articleService.filerByDescription(1, "check"))
                .thenReturn(new ArrayList<>(List.of(
                        new Repair()
                )));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnReportReportFilter() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/articles/api/repair/export/{id}/{filter}",
                        1, "check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());

        when(articleService.filerByDescription(1, "check"))
                .thenReturn(new ArrayList<>(List.of(
                        new Repair()
                )));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnReportReport() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/articles/api/report/export/{id}",
                        1, "check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());

        when(articleService.getReport(1))
                .thenReturn(new ArrayList<>(List.of(
                        new WorksMaterial()
                )));

        when(articleService.get(1))
                .thenReturn(new Article("value", 1, "rtm", 2));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }
}
