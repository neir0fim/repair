package service;

import com.kuzin.entity.Repair;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.service.RepairService;
import com.kuzin.service.service.ValidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.expression.AccessException;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class RepairServiceTest {

    @Mock
    RepairDao repairDao;

    @Mock
    WorksMaterialDao worksMaterialDao;

    @Mock
    ValidService validService;

    @InjectMocks
    RepairService service;

    Repair repair = new Repair("description", "1.2.3..1");
    List<Repair> result = new ArrayList<>(List.of(repair));
    WorksMaterial worksMaterial = new WorksMaterial(123, "pipe", "1222",
            "uom", 1.1, 1.1, 1);
    List<WorksMaterial> materialList = new ArrayList<>(List.of(worksMaterial));

    @Test
    void shouldReturnRepair() throws AccessException {
        Mockito.when(repairDao.save(repair)).thenReturn(repair);

        assertEquals(repair, service.save(repair));
    }

    @Test
    void shouldReturnRepairById() throws AccessException {
        Mockito.when(repairDao.get(1)).thenReturn(repair);
        repair.setMaterials(new ArrayList<>());

        Mockito.when(worksMaterialDao.getMaterial(1)).thenReturn(new ArrayList<>());

        assertEquals(repair, service.get(1));
    }

    @Test
    void shouldReturnListRepair() throws AccessException {
        repair.setMaterials(materialList);
        repair.setId(1);

        Mockito.when(repairDao.getRepairForArticle(1))
                .thenReturn(new ArrayList<>(List.of(repair)));

        Mockito.when(worksMaterialDao.getMaterial(1))
                .thenReturn(materialList);

        assertEquals(result, service.getRepairArticle(1));
    }

    @Test
    void shouldReturnIntAfterDeleting() throws AccessException {
        Mockito.when(repairDao.delete(1)).thenReturn(1);

        assertEquals(1, service.delete(1));
    }

    @Test
    void shouldReturnIntAfterUpdate() throws AccessException {
        Mockito.when(repairDao.update(repair, 1)).thenReturn(1);

        assertEquals(1, service.update(repair, 1));
    }
}
