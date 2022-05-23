package dao;

import com.kuzin.entity.Repair;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.RepairDao;
import java.util.List;
import com.kuzin.service.dao.WorksMaterialDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ContextConfiguration(classes = RepairApplication.class)
@DataJdbcTest
class RepairDaoTest {


    @Autowired
    JdbcTemplate jdbcTemplate;

    RepairDao repairDao;
    WorksMaterialDao worksMaterialDao;

    @BeforeEach
    void setUp() {
        worksMaterialDao = new WorksMaterialDao(jdbcTemplate);
        repairDao = new RepairDao(worksMaterialDao, jdbcTemplate);
    }

    Repair repair = new Repair("pump repair in PNC-3", "1.3.4.2.4.1");

    @Test
    void shouldReturnRepair() {
        repair.setId(1);
        repair.setType("RTM");

        assertEquals(repair, repairDao.get(1));
    }

    @Test
    void shouldReturnRepairByArticleId() {
        repair.setId(1);
        repair.setType("RTM");

        assertEquals(new ArrayList<>(List.of(repair)), repairDao.getRepairForArticle(1));
    }

    @Test
    void shouldReturnTypeByArticleId() {
        repair.setType("RTM");
        assertEquals(repair.getType(), repairDao.getType(repair.getArticle()));
    }

    @Test
    void shouldReturnTypeById() {
        repair.setType("RTM");
        assertEquals(repair.getType(), repairDao.getTypeById(1));
    }

    @Test
    void shouldReturnRepairForArticle() {
        assertEquals(new ArrayList<>(List.of(1)),
                repairDao.repairForArticle(repair.getArticle()));
    }

    @Test
    void shouldReturnIntAfterUpdate() {
        repair.setId(1);
        repair.setType("RTM");
        assertEquals(1, repairDao.update(repair, 1));
    }

    @Test
    void shouldReturnIntAfterDelete() {
        repair.setId(1);
        repair.setType("RTM");
        assertEquals(1, repairDao.delete(1));
    }




}
