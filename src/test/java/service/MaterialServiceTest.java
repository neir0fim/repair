package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kuzin.entity.Material;
import com.kuzin.entity.Report;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.MaterialDao;
import com.kuzin.service.service.MaterialService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
public class MaterialServiceTest {

    @Mock
    MaterialDao materialDao;

    @InjectMocks
    MaterialService service;

    Material material = new Material(123, "pipe", "1222",
            "uom", 1.1);
    List<Material> result = new ArrayList<>(List.of(material));
    Report report = new Report(1, 2, new ArrayList<>(Arrays.asList(1, 2)));
    File file = new File("/some/pass");

    @Test
    void shouldReturnMaterial() {
        Mockito.when(materialDao.get(1)).thenReturn(material);

        assertEquals(material, service.get(1));
    }

    @Test
    void shouldReturnListOfMaterials() {
        Mockito.when(materialDao.getAll()).thenReturn(result);

        assertEquals(result, service.getAll());
    }

    @Test
    void shouldReturnMaterialAfterSaving() {
        Mockito.when(materialDao.save(material)).thenReturn(material);

        assertEquals(material, service.save(material));
    }

    @Test
    void shouldReturnIntAfterDelete() {
        Mockito.when(materialDao.delete(1)).thenReturn(1);

        assertEquals(1, service.delete(1));
    }

    @Test
    void shouldReturnIntAfterUpdate() {
        Mockito.when(materialDao.update(material, 1)).thenReturn(1);

        assertEquals(1, service.update(material, 1));
    }

    @Test
    void shouldReturnReportFile() {
        Mockito.when(materialDao.download(file)).thenReturn(report);

        assertEquals(report, service.download(file));
    }

    @Test
    void shouldReturnListWorksMaterial() {
        Mockito.when(materialDao.getAll()).thenReturn(result);
        List<WorksMaterial> check = new ArrayList<>(List.of(new WorksMaterial(material)));

        assertEquals(check, service.getMaterials());
    }



}
