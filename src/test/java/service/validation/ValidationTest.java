package service.validation;

import com.kuzin.repair.RepairApplication;
import com.kuzin.service.service.addition.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static com.kuzin.service.service.addition.Validation.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class ValidationTest {

    @Test
    void shouldReturnExceptionIfIdInvalid() {
        assertThrows(IllegalArgumentException.class, () -> validId(-12));
    }

    @Test
    void shouldReturnExceptionIfObjInvalid() {
        assertThrows(NullPointerException.class, () -> validateObject(null));
    }

    @Test
    void doesNotThrowIfIdValid() {
        assertDoesNotThrow(() -> validId(12));
    }

    @Test
    void doesNotThrowIfObjValid() {
        assertDoesNotThrow(() -> validateObject(new Object()));
    }

    @Test
    void shouldThrowIfListEmpty() {
        List<Integer> check = new ArrayList<>();

        assertThrows(NoSuchElementException.class, () -> validateList(check));
    }

    @Test
    void doesNotThrowIfListNotEmpty() {
        List<Integer> check = new ArrayList<>();
        check.add(1);

        assertDoesNotThrow(() -> validateList(check));
    }

    @Test
    void shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> validateString(""));
    }

    @Test
    void doesNotThrowIfStringValid() {
        assertDoesNotThrow(() -> validateString("test"));
    }


}
