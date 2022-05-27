package rest;


import com.google.gson.Gson;
import com.kuzin.entity.Person;
import com.kuzin.entity.enums.ApplicationUserRole;
import com.kuzin.service.service.PersonService;
import com.kuzin.web.rest.PersonController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = PersonController.class)
class PersonControllerTest {
    @MockBean
    PersonService personService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    Person person = new Person("Oleg", "pass", false, "GUEST");
    UserDetails userDetails = new User("Oleg", "pass",
            ApplicationUserRole.ADMIN.getGrantedAuth());


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUser() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/persons/api/{username}", "Oleg")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(personService.getUser("Oleg")).thenReturn(userDetails);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnUsers() throws Exception {
        MockHttpServletRequestBuilder builder =
                get("/persons/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(personService.getUsers())
                .thenReturn(new ArrayList<>(List.of(userDetails)));

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "SUPP")
    void shouldSavePerson() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(person);


        MockHttpServletRequestBuilder builder =
                post("/persons/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        when(personService.addUser(person)).thenReturn(person);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteUser() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/persons/api/{username}", "Sergej")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(personService.delete("Sergej")).thenReturn(1);

        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeletePersonAndThrowError() throws Exception {
        MockHttpServletRequestBuilder builder =
                delete("/persons/api/{username}", "Sergej")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        when(personService.delete("Sergej")).thenReturn(0);

        this.mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldUnBlockPerson() throws Exception {
        MockHttpServletRequestBuilder builder =
                patch("/persons/api/unlock/{username}", "Sergej")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldBlockPerson() throws Exception {
        MockHttpServletRequestBuilder builder =
                patch("/persons/api/block/{username}", "Sergej")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf());


        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SUPP")
    void shouldUpdatePerson() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(person);


        MockHttpServletRequestBuilder builder =
                patch("/persons/api/{username}", "Sergej")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf());


        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }

}
