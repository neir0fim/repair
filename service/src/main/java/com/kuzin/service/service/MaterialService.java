package com.kuzin.service.service;

import com.kuzin.entity.Material;
import com.kuzin.service.dao.MaterialDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**material service class.*/

@Service
public class MaterialService implements ServiceEntity<Material> {

    MaterialDao materialDao;

    @Autowired
    public MaterialService(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    @Override
    public Material get(long id) {
        return null;
    }

    @Override
    public List<Material> getAll() {
        return null;
    }

    @Override
    public Material save(Material material) {
        return null;
    }

    @Override
    public void delete(long t) {

    }
}
