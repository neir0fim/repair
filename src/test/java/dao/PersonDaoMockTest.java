package dao;

import com.kuzin.entity.Person;
import com.kuzin.entity.enums.ApplicationUserRole;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.PersonDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class PersonDaoMockTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    DataSource dataSource;


    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    PersonDao personDao;

    Person user = new Person("Oleg", "pass", true, "GUEST");
    private static final String ADD_USER = "INSERT INTO users values (?, ?, ?, ?)";
    private static final String ADD_AUTHORITY = "INSERT INTO authorities values (?, ?)";
    private static final String UPDATE_PERSON = "UPDATE users SET password = ?,"
            + "type = ? where username = ?";
    private static final String DELETE_AUTHORITY = "DELETE from authorities where username = ?";
    private static final String BLOCK = "UPDATE users SET enabled = false where username = ?";
    private static final String UNLOCK = "UPDATE users SET enabled = true where username = ?";
    private static final String DELETE_PERSON = "DELETE from users where username = ?";

    @Test
    void shouldReturnUserAfterSaving() {
        user.setRole(ApplicationUserRole.GUEST);
        String username = user.getName();
        when(jdbcTemplate.update(ADD_USER, username,
                passwordEncoder.encode(user.getPass()), true, user.getType())).thenReturn(1);


        when(jdbcTemplate.update(ADD_AUTHORITY, username, "ROLE_GUEST"))
            .thenReturn(1);

        assertEquals(user, personDao.addUser(user));
    }

    @Test
    void shouldReturnPersonObject() {
        user.setRole(ApplicationUserRole.GUEST);
        String username = user.getName();
        when(jdbcTemplate.update(ADD_USER, username,
                passwordEncoder.encode(user.getPass()), true, user.getType())).thenReturn(1);


        when(jdbcTemplate.update(ADD_AUTHORITY, username, "ROLE_GUEST"))
                .thenReturn(1);

        assertNotNull(personDao.addUser(user));
    }

    @Test
    void shouldCallUpdateMethodOnUpdate() {
        user.setRole(ApplicationUserRole.GUEST);
        personDao.update(user, "Oleg");

        verify(jdbcTemplate).update(UPDATE_PERSON, passwordEncoder.encode(user.getPass()),
                user.getType(), "Oleg");
    }

    @Test
    void shouldCallUpdateMethodOnDelete() {
        user.setRole(ApplicationUserRole.GUEST);
        personDao.update(user, "Oleg");

        verify(jdbcTemplate).update(DELETE_AUTHORITY, "Oleg");
    }

    @Test
    void shouldCallUpdateMethodOnAddAuthority() {
        user.setRole(ApplicationUserRole.GUEST);
        personDao.update(user, "Oleg");

        verify(jdbcTemplate).update(ADD_AUTHORITY, user.getName(), "ROLE_GUEST");
    }

    @Test
    void shouldCallUnBlockMethod() {
        personDao.unlock(user.getName());

        verify(jdbcTemplate).update(UNLOCK, user.getName());
    }


    @Test
    void shouldCallBlockMethod() {
        personDao.block(user.getName());

        verify(jdbcTemplate).update(BLOCK, user.getName());
    }

    @Test
    void shouldReturnInt() {
        when(jdbcTemplate.update(DELETE_PERSON, user.getName())).thenReturn(1);

        assertEquals(1, personDao.deletePerson(user.getName()));
    }

}
