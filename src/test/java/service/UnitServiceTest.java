package service;

import com.kuzin.entity.Unit;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.UnitDao;
import com.kuzin.service.service.UnitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class UnitServiceTest {

    @Mock
    UnitDao unitDao;

    @Mock
    ArticleDao articleDao;

    @InjectMocks
    UnitService service;

    Unit unit = new Unit(1, "RTM");
    List<Unit> result = new ArrayList<>(List.of(unit));


    @Test
    void shouldReturnUnit() {
        Mockito.when(unitDao.get(1)).thenReturn(unit);
        Mockito.when(articleDao.getForUnit(1)).thenReturn(new ArrayList<>());

        assertEquals(unit, service.get(1));
    }

    @Test
    void shouldReturnList() {
        Mockito.when(unitDao.getAll()).thenReturn(result);
        Mockito.when(articleDao.getForUnit(1)).thenReturn(new ArrayList<>());

        assertEquals(result, service.getAll());
    }

    @Test
    void shouldReturnUnitAfterSaving() {
        Mockito.when(unitDao.save(unit)).thenReturn(unit);

        assertEquals(unit, service.save(unit));
    }

    @Test
    void shouldReturnIntAfterDeleting() {
        Mockito.when(unitDao.delete(1)).thenReturn(1);

        assertEquals(1, service.delete(1));
    }

    @Test
    void shouldAwokeUpdateMethod() {
        service.update(unit, 1);
        
        verify(unitDao).update(unit, 1);
    }


}
