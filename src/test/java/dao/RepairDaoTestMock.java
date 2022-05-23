package dao;


import com.kuzin.entity.Repair;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class RepairDaoTestMock {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    WorksMaterialDao worksMaterialDao;

    @InjectMocks
    RepairDao repairDao;

    private static final String SELECT_FROM_TYPE = "Select type from "
            + "article where article.article = ?";
    private static final String INSERT_INTO_REPAIR = "INSERT INTO repair "
            + "(description, article, type) values (?, ?, ?) returning repair_id";

    Repair repair = new Repair("pump repair in PNC-3", "1.3.4.2.4.1");


    @Test
    void shouldReturnRepairAfterSaving() {
        when(jdbcTemplate.queryForObject(SELECT_FROM_TYPE, String.class,
                repair.getArticle())).thenReturn("RTM");
        when(jdbcTemplate.queryForObject(INSERT_INTO_REPAIR,
                Long.class, repair.getDescription(),
                repair.getArticle(), "RTM")).thenReturn(1L);

        Repair result = new Repair("pump repair in PNC-3", "1.3.4.2.4.1");
        result.setId(1L);


        assertEquals(result, repairDao.save(repair));
    }
}
