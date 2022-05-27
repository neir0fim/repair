package rest;

import com.google.gson.Gson;
import com.kuzin.entity.Repair;
import com.kuzin.service.service.RepairService;
import com.kuzin.web.rest.RepairController;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RepairController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = RepairController.class)
class RepairControllerTest {

    @MockBean
    RepairService repairService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    Repair repair = new Repair("pump repair in PNC-3", "1.3.4.2.4.1");

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnRepairsForArticle() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/repair/api/article/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(repairService.getRepairArticle(1))
                .thenReturn(new ArrayList<>(List.of(repair)));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnRepairs() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/repair/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(repairService.get(1))
                .thenReturn(repair);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldSaveResource() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(repair);


        MockHttpServletRequestBuilder builder =
                post("/repair/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(repairService.save(repair)).thenReturn(repair);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldDeleteRepair() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/repair/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(repairService.delete(1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldAfterDeleteRepairReturnError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/repair/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(repairService.delete(1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldUpdateRepair() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(repair);


        MockHttpServletRequestBuilder builder =
                patch("/repair/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());

        when(repairService.update(repair, 1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldAfterUpdateReturnError() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(repair);


        MockHttpServletRequestBuilder builder =
                patch("/repair/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());

        when(repairService.update(repair, 1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }
}
