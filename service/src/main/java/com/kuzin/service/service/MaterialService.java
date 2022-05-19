package com.kuzin.service.service;

import static com.kuzin.service.service.addition.Validation.*;

import com.kuzin.entity.Material;
import com.kuzin.entity.Report;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.service.dao.MaterialDao;
import java.io.File;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**material service class.*/

@Service
public class MaterialService {

    MaterialDao materialDao;

    @Autowired
    public MaterialService(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }


    public Material get(long id) {
        validId(id);

        return materialDao.get(id);
    }

    public List<Material> getAll() {
        List<Material> result = materialDao.getAll();
        validateList(result);

        return result;
    }

    public Material save(Material material) {
        validateObject(material);

        return materialDao.save(material);
    }

    public int delete(long t) {
        validId(t);

        return materialDao.delete(t);
    }

    public int update(Material material, long cod) {
        validateObject(material);
        validId(cod);

        return materialDao.update(material, cod);
    }

    public Report download(File file) {
        return materialDao.download(file);
    }

    public List<WorksMaterial> getMaterials() {
        List<WorksMaterial> result = new ArrayList<>();


        for (Material material : materialDao.getAll()) {
            result.add(new WorksMaterial(material));
        }

        return result;
    }
}
