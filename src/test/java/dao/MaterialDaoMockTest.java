package dao;

import com.kuzin.entity.Material;
import com.kuzin.entity.Report;
import com.kuzin.repair.RepairApplication;
import com.kuzin.service.dao.MaterialDao;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = RepairApplication.class)
class MaterialDaoMockTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    MaterialDao materialDao;

    private static final String UPDATE = "UPDATE material set name = ?, coddk = ?, uom = ?,"
            + "value = ? where cod = ?";
    Material material = new Material(4321343, "electrodes",
            "39141000-2", "kg", 45);
    private static final String INSERT_INTO_MATERIAL = "INSERT INTO material "
            + "(cod, name, coddk, uom, value) VALUES (?, ?, ?, ?, ?)";

    @Test
    void shouldReturnIntAfterUpdate() {
        when(jdbcTemplate.update(UPDATE, material.getName(),
                material.getCodDk(), material.getUom(),
                material.getValue(), 4321343L)).thenReturn(1);

        assertEquals(1, materialDao.update(material, 4321343L));
    }

    @Test
    void shouldReturnMaterialAfterSaving() {
        when(jdbcTemplate.update(INSERT_INTO_MATERIAL, material.getCod(),
                material.getName(), material.getCodDk(), material.getUom(),
                material.getValue())).thenReturn(1);

        assertEquals(material, materialDao.save(material));
    }


}
