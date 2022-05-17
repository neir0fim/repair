package com.kuzin.service.mapper;

import com.kuzin.entity.Person;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/** person mapper class.*/
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Person(
                rs.getString(1),
                rs.getString(2),
                rs.getBoolean(3),
                rs.getString(4)
        );
    }
}
