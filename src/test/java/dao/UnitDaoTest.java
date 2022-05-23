package dao;

import com.kuzin.entity.Unit;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.UnitDao;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = RepairApplication.class)
@DataJdbcTest
class UnitDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    UnitDao unitDao;


    @BeforeEach
    void setUp() {
        unitDao = new UnitDao(jdbcTemplate);
    }

    Unit unit = new Unit(1, "RTM");

    @Test
    void shouldReturnUnitById() {
        assertEquals(unit, unitDao.get(1));
    }

    @Test
    void shouldReturnListOfUnits() {
        assertEquals(new ArrayList<>(List.of(unit)), unitDao.getAll());
    }

    @Test
    void shouldReturnIntAfterDeleting() {
        assertEquals(1, unitDao.delete(1));
    }








}
