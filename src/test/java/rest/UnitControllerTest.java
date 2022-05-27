package rest;

import com.google.gson.Gson;
import com.kuzin.entity.Unit;
import com.kuzin.service.service.UnitService;
import com.kuzin.web.rest.UnitController;
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
@WebMvcTest(UnitController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = UnitController.class)
class UnitControllerTest {
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
    void shouldReturnUnits() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/units/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(unitService.getAll())
                .thenReturn(new ArrayList<>(List.of(unit)));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUnit() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/units/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(unitService.get(1))
                .thenReturn(unit);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldSaveUnit() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(unit);


        MockHttpServletRequestBuilder builder =
                post("/units/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(unitService.save(unit)).thenReturn(unit);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteUnit() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/units/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(unitService.delete(1)).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAfterDeleteUnitReturnError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/units/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(unitService.delete(1)).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUpdateUnit() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(unit);


        MockHttpServletRequestBuilder builder =
                patch("/units/api/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

}
