package dao;


import com.kuzin.entity.Material;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.MaterialDao;
import com.kuzin.service.dao.UnitDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = RepairApplication.class)
@DataJdbcTest
class MaterialDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    MaterialDao materialDao;

    Material material = new Material(4321343, "electrodes",
            "39141000-2", "kg", 45);

    @BeforeEach
    void setUp() {
        materialDao = new MaterialDao(jdbcTemplate);
    }

    @Test
    void shouldReturnMaterial() {
        assertEquals(material, materialDao.get(4321343));
    }

    @Test
    void shouldReturnListOfMaterial() {
        assertEquals(new ArrayList<>(List.of(material)),
                materialDao.getAll());
    }

    @Test
    void shouldReturnIntAfterDeleting() {
        assertEquals(1, materialDao.delete(4321343));
    }

}
