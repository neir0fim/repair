package rest;

import com.google.gson.Gson;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.entity.enums.MaterialPost;
import com.kuzin.service.service.WorksMaterialService;
import com.kuzin.web.rest.WorksMaterialController;
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


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WorksMaterialController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = WorksMaterialController.class)
class WorksMaterialControllerTest {
    @MockBean
    WorksMaterialService worksMaterialService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    WorksMaterial worksMaterial = new WorksMaterial(123, "pipe", "1222",
            "uom", 1.1, 1.1, 1);
    MaterialPost materialPost = new MaterialPost(1, 2, 12);

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldSaveMaterial() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(materialPost);


        MockHttpServletRequestBuilder builder =
                post("/works/material/api/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(worksMaterialService.save(materialPost)).thenReturn(worksMaterial);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldDeleteMaterial() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/works/material/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(worksMaterialService.deleteMaterial(1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldAfterDeleteMaterialReturnError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/works/material/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(worksMaterialService.deleteMaterial(1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldDeleteMaterials() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/works/material/api/all/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(worksMaterialService.deleteMaterials(1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldAfterDeleteMaterialsReturnError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/works/material/api/all/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(worksMaterialService.deleteMaterials(1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateMaterial() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(worksMaterial);


        MockHttpServletRequestBuilder builder =
                patch("/works/material/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());

        when(worksMaterialService.updateMaterial(worksMaterial)).thenReturn(1);


        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAfterUpdateMaterialErrorCase() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(worksMaterial);


        MockHttpServletRequestBuilder builder =
                patch("/works/material/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());

        when(worksMaterialService.updateMaterial(worksMaterial)).thenReturn(0);


        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }
}
