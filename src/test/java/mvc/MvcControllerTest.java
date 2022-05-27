package mvc;

import com.kuzin.entity.Unit;
import com.kuzin.service.service.ArticleService;
import com.kuzin.service.service.UnitService;
import com.kuzin.web.mvc.MvcController;
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
@WebMvcTest(MvcController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = MvcController.class)
class MvcControllerTest {
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

    Unit unit = new Unit(1, "RTM");

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUserPage() throws Exception {
        when(unitService.getAll()).thenReturn(new ArrayList<>(List.of(unit)));

        this.mockMvc.perform(get("/users"))
                .andExpect(view().name("person/new"))
                .andExpect(model().attributeExists("units"))
                .andExpect(model().attributeExists("roles"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnNewUserPage() throws Exception {
        this.mockMvc.perform(get("/units/new"))
                .andExpect(view().name("unit/new"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnNewArticlePage() throws Exception {
        when(unitService.getAll()).thenReturn(new ArrayList<>(List.of(unit)));

        this.mockMvc.perform(get("/article/new"))
                .andExpect(view().name("article/new"))
                .andExpect(model().attributeExists("units"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUnitUpdatePage() throws Exception {
        this.mockMvc.perform(get("/units/update/{id}", 1))
                .andExpect(view().name("unit/update"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUpdateArticlePage() throws Exception {
        when(unitService.getAll()).thenReturn(new ArrayList<>(List.of(unit)));

        this.mockMvc.perform(get("/articles/update/{id}", 1))
                .andExpect(view().name("article/update"))
                .andExpect(model().attributeExists("units"))
                .andExpect(model().attributeExists("id"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUpdatePersonPage() throws Exception {
        when(unitService.getAll()).thenReturn(new ArrayList<>(List.of(unit)));

        this.mockMvc.perform(get("/person/update/{name}", "Oleg"))
                .andExpect(view().name("person/update"))
                .andExpect(model().attributeExists("units"))
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("name"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUpdateRepairPage() throws Exception {
        when(unitService.getAll()).thenReturn(new ArrayList<>(List.of(unit)));

        this.mockMvc.perform(get("/repair/update/{id}", 1))
                .andExpect(view().name("repair/update"))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("articles"));
    }
}
