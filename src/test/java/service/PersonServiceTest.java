package service;

import com.kuzin.entity.Person;
import com.kuzin.entity.enums.ApplicationUserRole;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.PersonDao;
import com.kuzin.service.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class PersonServiceTest {

    @Mock
    PersonDao personDao;

    @InjectMocks
    PersonService service;


    UserDetails userDetails = new User("Oleg", "pass",  true,
            true, true, true,
            ApplicationUserRole.ADMIN.getGrantedAuth());
    List<UserDetails> result = new ArrayList<>(List.of(userDetails));
    Person person = new Person("Oleg", "pass", true, "ADMIN");


    @Test
    void shouldReturnNewUserDetail() {
        Mockito.when(personDao.loadUserByUsername("Oleg")).thenReturn(userDetails);

        assertEquals(userDetails, service.getUser("Oleg"));
    }

    @Test
    void shouldReturnArrayOfUser() {
        Mockito.when(personDao.getUsers()).thenReturn(result);

        assertEquals(result, service.getUsers());
    }

    @Test
    void shouldReturnUserAfterSaving() {
        Mockito.when(personDao.addUser(person)).thenReturn(person);

        assertEquals(person, service.addUser(person));
    }

    @Test
    void shouldCallBlockMethod() {
        service.block("Oleg");

        verify(personDao).block("Oleg");
    }

    @Test
    void shouldCallUnlockMethod() {
        service.unlock("Oleg");

        verify(personDao).unlock("Oleg");
    }

    @Test
    void shouldReturnIntAfterDeleting() {
        Mockito.when(personDao.deletePerson("Oleg")).thenReturn(1);

        assertEquals(1, service.delete("Oleg"));
    }

    @Test
    void shouldCallUpdateMethod() {
        service.updatePerson(person, "Oleg");

        verify(personDao).update(person, "Oleg");
    }
}
