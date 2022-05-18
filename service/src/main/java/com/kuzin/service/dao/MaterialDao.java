package com.kuzin.service.dao;


import com.kuzin.entity.Material;
import com.kuzin.entity.Report;
import com.kuzin.service.mapper.MaterialsMapper;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;


/** material dao class.*/

@Repository
public class MaterialDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public MaterialDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_INTO_MATERIAL = "INSERT INTO material "
            + "(cod, name, coddk, uom, value) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_MATERIAL = "SELECT * from material where cod = ?";
    private static final String SELECT_MATERIALS = "SELECT * from  material";
    private static final String DELETE = "DELETE from material where cod = ?";
    private static final String UPDATE = "UPDATE material set name = ?, coddk = ?, uom = ?,"
            + "value = ? where cod = ?";

    public Material get(long cod) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SELECT_MATERIAL,
                new MaterialsMapper(), cod)).stream().findAny().orElseThrow();
    }


    public List<Material> getAll() {
        return new ArrayList<>(jdbcTemplate.query(SELECT_MATERIALS,
                new MaterialsMapper()));
    }

    public Material save(Material material) {
        jdbcTemplate.update(INSERT_INTO_MATERIAL, material.getCod(),
                material.getName(), material.getCodDk(), material.getUom(),
                material.getValue());

        return material;
    }

    public void update(Material material, long cod) {
        jdbcTemplate.update(UPDATE, material.getName(),
                material.getCodDk(), material.getUom(), material.getValue(), cod);
    }

    public int delete(long t) {
        return jdbcTemplate.update(DELETE, t);
    }


    public Report download(File file) {
        int success = 0;
        int fail = 0;
        int failCount = 1;

        List<Integer> failRows = new ArrayList<>();

        try (FileInputStream stream = new FileInputStream(file)) {


            Workbook workbook = new XSSFWorkbook(stream);
            Sheet firstSheet = workbook.getSheetAt(0);

            for (Row row : firstSheet) {
                try {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    setMaterial(cellIterator);
                    success++;
                } catch (NonTransientDataAccessException e) {
                    fail++;
                    failRows.add(failCount);
                }
                failCount++;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Report(success, fail, failRows);
    }



    public void setMaterial(Iterator<Cell> cellIterator) {
        jdbcTemplate.update(INSERT_INTO_MATERIAL, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int counter = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (counter) {
                        case 0 -> ps.setInt(1, (int) cell.getNumericCellValue());
                        case 1 -> ps.setString(2, cell.getStringCellValue());
                        case 2 -> ps.setString(3, cell.getStringCellValue());
                        case 3 -> ps.setString(4, cell.getStringCellValue());
                        case 4 -> ps.setDouble(5, cell.getNumericCellValue());
                        default -> throw new IllegalArgumentException();
                    }
                    counter++;
                }
            }
        });
    }
}
