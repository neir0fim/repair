package dao;

import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.UnitDao;
import com.kuzin.service.dao.ValidDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = RepairApplication.class)
@DataJdbcTest
class ValidDaoTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    ValidDao validDao;


    @BeforeEach
    void setUp() {
        validDao = new ValidDao(jdbcTemplate);
    }

    @Test
    void shouldReturnTypeById() {
        assertEquals("RTM", validDao.getType(1));
    }

    @Test
    void shouldReturnTypeByValue() {
        assertEquals("RTM", validDao.getTypeByValue("1.3.4.2.4.1"));
    }

    @Test
    void shouldReturnTypeByRepairId() {
        assertEquals("RTM", validDao.getRepairType(1));
    }
}
