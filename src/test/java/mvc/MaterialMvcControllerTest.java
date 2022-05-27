package mvc;

import com.kuzin.web.mvc.MaterialMvcController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MaterialMvcController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = MaterialMvcController.class)
class MaterialMvcControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnUnitsPage() throws Exception {
        this.mockMvc.perform(get("/materials"))
                .andExpect(view().name("supp/materials"));
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnUnitPage() throws Exception {
        this.mockMvc.perform(get("/materials/get/{code}", 1))
                .andExpect(view().name("supp/material"));
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnNewUnitPage() throws Exception {
        this.mockMvc.perform(get("/materials/new"))
                .andExpect(view().name("supp/new"));
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnUpdatePage() throws Exception {
        this.mockMvc.perform(get("/materials/update/{code}", 1))
                .andExpect(view().name("supp/update"));
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnNewDownloadPage() throws Exception {
        this.mockMvc.perform(get("/materials/download"))
                .andExpect(view().name("download"));
    }
}
