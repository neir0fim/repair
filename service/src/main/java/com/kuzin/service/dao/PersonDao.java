package com.kuzin.service.dao;


import com.kuzin.entity.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


/** person dao class.*/

@Repository
public class PersonDao extends JdbcDaoImpl {

    PasswordEncoder passwordEncoder;
    JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    private static final String GET_NAMES = "SELECT username from users";
    private static final String DELETE_PERSON = "DELETE from users where username = ?";
    private static final String BLOCK = "UPDATE users SET enabled = false where username = ?";
    private static final String UNLOCK = "UPDATE users SET enabled = true where username = ?";
    private static final String ADD_USER = "INSERT INTO users values (?, ?, ?, ?)";
    private static final String ADD_AUTHORITY = "INSERT INTO authorities values (?, ?)";


    @Autowired
    public PersonDao(DataSource dataSource, PasswordEncoder passwordEncoder,
                     JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = jdbcTemplate;
        setDataSource(dataSource);
    }

    public void addUser(Person user) {
        String username = user.getName();

        jdbcTemplate.update(ADD_USER, username,
                 passwordEncoder.encode(user.getPass()), true, user.getUnitId());

        Set<SimpleGrantedAuthority> authorities = user.getRole().getGrantedAuth();

        for (SimpleGrantedAuthority authority : authorities) {
            jdbcTemplate.update(ADD_AUTHORITY, username, authority.toString());
        }
    }

    public List<UserDetails> getUsers() {
        List<UserDetails> result = new ArrayList<>();
        List<String> names = new ArrayList<>(jdbcTemplate.queryForList(GET_NAMES, String.class));

        for (String name : names) {
            result.add(loadUserByUsername(name));
        }

        return result;
    }


    public void deletePerson(String name) {
        jdbcTemplate.update(DELETE_PERSON, name);
    }

    public void block(String name) {
        jdbcTemplate.update(BLOCK, name);
    }

    public void unlock(String name) {
        jdbcTemplate.update(UNLOCK, name);
    }


}
