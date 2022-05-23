package dao;

import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.entity.enums.MaterialPost;
import com.kuzin.repair.RepairApplication;

import java.util.ArrayList;
import java.util.List;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.mapper.MaterialsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = RepairApplication.class)
@DataJdbcTest
class WorksMaterialDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    WorksMaterialDao worksMaterialDao;

    WorksMaterial worksMaterial = new WorksMaterial(1232,
            "Pipe 420x8 st.3 sp.5", "31100000-7", "r.m.",
            48900, 132, 1);
    Material material = new Material(4321343, "electrodes",
            "39141000-2", "kg", 45);
    @BeforeEach
    void setUp() {
        worksMaterialDao = new WorksMaterialDao(jdbcTemplate);
        worksMaterial.setId(1L);
        worksMaterial.setCost(48900*132);
    }

    @Test
    void shouldReturnListOfMaterial() {
        assertEquals(new ArrayList<>(List.of(worksMaterial)), worksMaterialDao.getMaterial(1));
    }

    @Test
    void shouldReturnIntAfterUpdate() {
        assertEquals(1, worksMaterialDao.updateMaterial(worksMaterial));
    }

    @Test
    void shouldReturnIntAfterDelete() {
        assertEquals(1, worksMaterialDao.deleteMaterial(1));
    }

    @Test
    void shouldReturnIntAfterDeleteAll() {
        assertEquals(1, worksMaterialDao.deleteAll(1));
    }

    @Test
    void shouldReturnMaterial() {
      assertEquals(material, worksMaterialDao.getMaterialForSave(4321343));
    }

    @Test
    void shouldReturnWorkMaterialAfterSaving() {
        WorksMaterial result = new WorksMaterial(material, 12);

        assertEquals(result, worksMaterialDao.addMaterial(
                new MaterialPost(4321343, 1, 12)));
    }


}
