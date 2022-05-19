package service;

import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.entity.enums.MaterialPost;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.service.ValidService;
import com.kuzin.service.service.WorksMaterialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class WorksMaterialServiceTest {

    @Mock
    WorksMaterialDao worksMaterialDao;

    @Mock
    ValidService validService;

    @InjectMocks
    WorksMaterialService service;

    WorksMaterial worksMaterial = new WorksMaterial(123, "pipe", "1222",
            "uom", 1.1, 1.1, 1);
    MaterialPost materialPost = new MaterialPost(123, 1, 12);

    @Test
    void shouldAwokeSaveMethod() {
        service.save(materialPost);

        verify(worksMaterialDao).addMaterial(materialPost);
    }

    @Test
    void shouldReturnIntAfterDeleting() {
        Mockito.when(worksMaterialDao.deleteMaterial(1)).thenReturn(1);

        assertEquals(1, service.deleteMaterial(1));
    }

    @Test
    void shouldReturnIntAfterDeletingAll() {
        Mockito.when(worksMaterialDao.deleteAll(1)).thenReturn(11);

        assertEquals(11, service.deleteMaterials(1));
    }

    @Test
    void shouldReturnIntAfterUpdating() {
        Mockito.when(worksMaterialDao.updateMaterial(worksMaterial)).thenReturn(1);

        assertEquals(1, service.updateMaterial(worksMaterial));
    }
}
