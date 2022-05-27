package rest;

import com.google.gson.Gson;
import com.kuzin.entity.Material;
import com.kuzin.entity.Report;
import com.kuzin.service.service.MaterialService;
import com.kuzin.web.rest.MaterialController;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MaterialController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = MaterialController.class)
class MaterialControllerTest {
    @MockBean
    MaterialService materialService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    Material material = new Material(4321343, "electrodes",
            "39141000-2", "kg", 45);

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnMaterial() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/materials/api/{cod}", 12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(materialService.get(12)).thenReturn(material);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldReturnMaterials() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/materials/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(materialService.getAll()).thenReturn(new ArrayList<>(List.of(material)));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "SUPP")
    void shouldSaveMaterial() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(material);

        MockHttpServletRequestBuilder builder =
                post("/materials/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(materialService.save(material)).thenReturn(material);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldSaveArticle() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(material);

        MockHttpServletRequestBuilder builder =
                patch("/materials/api/{cod}",12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(materialService.update(material, 12)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldDeleteMaterial() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/materials/api/{cod}", 12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(materialService.delete(12)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldDeleteMaterialAndThrowError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/materials/api/{cod}", 12)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(materialService.delete(12)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

}
