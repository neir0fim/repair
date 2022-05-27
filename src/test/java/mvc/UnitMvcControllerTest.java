package mvc;

import com.kuzin.web.mvc.UnitMvcController;
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
@WebMvcTest(UnitMvcController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = UnitMvcController.class)
class UnitMvcControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUnitsPage() throws Exception {
        this.mockMvc.perform(get("/units"))
                .andExpect(view().name("unit/units"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUnitPage() throws Exception {
        this.mockMvc.perform(get("/units/get/{id}", 1))
                .andExpect(view().name("unit/unit"));
    }

}
