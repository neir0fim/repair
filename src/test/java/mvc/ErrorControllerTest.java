package mvc;

import com.kuzin.web.mvc.ErrorController;
import com.kuzin.web.rest.ArticleController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.expression.AccessException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ErrorController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = ErrorController.class)
class ErrorControllerTest {

    @MockBean
    ArticleController articleController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnNumberErrorPage() throws Exception {
        when(articleController.get()).thenThrow(new NumberFormatException());


        this.mockMvc.perform(get("/articles/api"))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists(ERROR))
                .andExpect(model().attributeExists(MESSAGE));
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnNullPointerErrorPage() throws Exception {
        when(articleController.get()).thenThrow(new NullPointerException());


        this.mockMvc.perform(get("/articles/api"))
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists(ERROR))
                .andExpect(model().attributeExists(MESSAGE));
    }


    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnIllegalArgumentExceptionErrorPage() throws Exception {
        when(articleController.get()).thenThrow(new IllegalArgumentException());


        this.mockMvc.perform(get("/articles/api"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "WORKER")
    void shouldReturnAccessExceptionErrorPage() throws Exception {
        when(articleController.getById(1)).thenThrow(new AccessException(""));


        this.mockMvc.perform(get("/articles/api/1"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnEmptyResultDataAccessExceptionError() throws Exception {
        when(articleController.getById(1))
                .thenThrow(new EmptyResultDataAccessException(1));


        this.mockMvc.perform(get("/articles/api/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnDataIntegrityViolationExceptionError() throws Exception {
        when(articleController.getById(1))
                .thenThrow(new DataIntegrityViolationException(""));


        this.mockMvc.perform(get("/articles/api/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnNoSuchElementExceptionError() throws Exception {
        when(articleController.getById(1))
                .thenThrow(new NoSuchElementException(""));


        this.mockMvc.perform(get("/articles/api/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnDuplicateKeyExceptionError() throws Exception {
        when(articleController.getById(1))
                .thenThrow(new DuplicateKeyException(""));


        this.mockMvc.perform(get("/articles/api/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldReturnPsqlError() throws Exception {
        this.mockMvc.perform(get("/articles/api/error/case/1"))
                .andExpect(status().isNotFound());
    }
}
