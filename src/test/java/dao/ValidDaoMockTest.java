package dao;

import com.kuzin.entity.enums.ApplicationUserRole;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.ValidDao;
import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.expression.AccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class ValidDaoMockTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    ValidDao validDao;

    private static final String GET_USER = "SELECT type from users where username = ?";


//    @Test
//    void filterAllowAccess() {
//        Authentication auth = mock(Authentication.class);
//        SecurityContextHolder securityContextHolder = mock(SecurityContextHolder.class);
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
//        when(securityContext.getAuthentication()).thenReturn(auth);
//        when(auth.getName()).thenReturn("Oleg");
//        when(jdbcTemplate.queryForObject(GET_USER, String.class, "Oleg"))
//                .thenReturn("RTM");
//
//        assertDoesNotThrow(() -> validDao.filter("RTM"));
//
//    }
}
