package dao;

import com.kuzin.entity.Unit;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.UnitDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class UnitDaoMockTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    UnitDao unitDao;

    private static final String UPDATE = "UPDATE units set type = ? where unit_id = ?";
    private static final String ADD_UNIT = "INSERT into units (type) values (?) returning unit_id";


    Unit unit = new Unit(1, "RTM");

    @Test
    void shouldCallUpdateMethod() {
        unitDao.update(unit, 1L);

        verify(jdbcTemplate).update(UPDATE, unit.getKind(), 1L);
    }

    @Test
    void shouldReturnUnitAfterSaving() {
        Unit save = new Unit();
        save.setKind("KTC");

        when(jdbcTemplate.queryForObject(
                ADD_UNIT, Long.class, save.getKind())).thenReturn(1L);

        assertEquals(new Unit(1L, "KTC"), unitDao.save(save));
    }
}
