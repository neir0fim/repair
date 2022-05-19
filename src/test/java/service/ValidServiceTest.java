package service;

import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.ValidDao;
import com.kuzin.service.service.ValidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.expression.AccessException;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class ValidServiceTest {

    @Mock
    ValidDao validDao;

    @InjectMocks
    ValidService service;

    String value = "1.2.3.4";
    String type = "RTM";

    @Test
    void shouldAwokeFilter() throws AccessException {
        service.filter(value);

        verify(validDao).filter(value);
    }

    @Test
    void shouldReturnType() {
        Mockito.when(validDao.getType(1)).thenReturn(value);

        assertEquals(value, service.getType(1));
    }

    @Test
    void shouldReturnValueByType() {
        Mockito.when(validDao.getTypeByValue(value)).thenReturn(type);

        assertEquals(type, service.getTypeByValue(value));
    }

    @Test
    void shouldReturnTypeById() {
        Mockito.when(validDao.getRepairType(1)).thenReturn(type);

        assertEquals(type, service.getRepairType(1));
    }


}
