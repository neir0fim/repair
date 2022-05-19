package service;

import com.kuzin.entity.Article;
import com.kuzin.entity.Material;
import com.kuzin.entity.Repair;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.MaterialDao;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.service.ArticleService;
import com.kuzin.service.service.ValidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.expression.AccessException;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class ArticleServiceTest {


    @Mock
    ArticleDao articleDao;
    @Mock
    RepairDao repairDao;
    @Mock
    WorksMaterialDao worksMaterialDao;
    @Mock
    MaterialDao materialDao;
    @Mock
    ValidService validService;

    @InjectMocks
    ArticleService service;

    Article check = new Article("1.23", 1, "RTM", 2);
    List<Integer> repairId = new ArrayList<>(List.of(1));
    WorksMaterial worksMaterial = new WorksMaterial(123, "pipe", "1222",
            "uom", 1.1, 1.1, 1);
    List<WorksMaterial> getMaterial = new ArrayList<>(Arrays.asList(worksMaterial, worksMaterial));
    List<WorksMaterial> expected = new ArrayList<>(Arrays.asList(
       worksMaterial, worksMaterial));
    Material material = new Material(123, "pipe", "1222",
            "uom", 1.1);
    List<Integer> repairIdExpanded = new ArrayList<>(Arrays.asList(1, 2));
    Repair repair = new Repair("pipe repair", "1.223.2");
    Repair repair2 = new Repair("repair", "1.223.2");



    @Test
    void shouldReturnArticle() throws AccessException {
        Mockito.when(articleDao.get(1)).thenReturn(check);

        assertEquals(check, service.get(1));
    }

    @Test
    void shouldReturnListArticle() {
        List<Article> check = new ArrayList<>();
        check.add(new Article());

        Mockito.when(articleDao.getAll()).thenReturn(check);

        assertEquals(check, service.getAll());
    }


    @Test
    void shouldReturnIntWhenUpdate() {
        Mockito.when(articleDao.update(check, 1)).thenReturn(1);

        assertEquals(1, service.update(check, 1));
    }

    @Test
    void shouldReturnArticleAfterSaving() {
        Mockito.when(articleDao.save(check)).thenReturn(check);

        assertEquals(check, service.save(check));
    }


    @Test
    void shouldReturnIntAfterDelete() {
        Mockito.when(articleDao.delete(1)).thenReturn(1);

        assertEquals(1, service.delete(1));
    }

    @Test
    void shouldReturnListOfMaterial() {
        Mockito.when(articleDao.get(1)).thenReturn(check);
        Mockito.when(repairDao.repairForArticle(check.getValue())).thenReturn(repairId);
        Mockito.when(worksMaterialDao.getMaterial(1)).thenReturn(getMaterial);

        assertEquals(expected, service.getList(1));

    }


    @Test
    void shouldSummingAndReturn() {
        Mockito.when(articleDao.get(1)).thenReturn(check);
        Mockito.when(repairDao.repairForArticle(check.getValue())).thenReturn(repairId);
        Mockito.when(worksMaterialDao.getMaterial(1)).thenReturn(getMaterial);
        Mockito.when(materialDao.get(worksMaterial.getCod())).thenReturn(material);

        List<WorksMaterial> expectedResult = new ArrayList<>(
                List.of(new WorksMaterial(123, "pipe", "1222",
                        "uom", 1.1, 2.2, 0)));

        assertEquals(expectedResult, service.getReport(1));
    }

    @Test
    void shouldReturnRepair() throws AccessException {
        Mockito.when(articleDao.get(1)).thenReturn(check);
        Mockito.when(repairDao.repairForArticle(check.getValue())).thenReturn(repairIdExpanded);
        Mockito.when(repairDao.get(1)).thenReturn(repair);
        Mockito.when(repairDao.get(2)).thenReturn(repair2);

        List<Repair> expectedResult = new ArrayList<>(List.of(repair));

        assertEquals(expectedResult, service.filerByDescription(1, "pipe"));
    }


    @Test
    void shouldReturnListOfArticle() {
        List<Article> result = new ArrayList<>(List.of(check));
        Mockito.when(articleDao.getForUser()).thenReturn(new ArrayList<>(List.of(check)));

        assertEquals(result, service.getForUser());
    }



}