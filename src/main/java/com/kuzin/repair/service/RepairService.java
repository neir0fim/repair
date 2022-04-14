package com.kuzin.repair.service;

import com.kuzin.repair.dao.MaterialDao;
import com.kuzin.repair.dao.RepairDao;
import com.kuzin.repair.entity.Material;
import com.kuzin.repair.entity.Repair;
import com.kuzin.repair.service.addition.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepairService implements ServiceEntity<Repair> {

    RepairDao repairDao;
    MaterialDao materialDao;

    @Autowired
    public RepairService(RepairDao repairDao, MaterialDao materialDao) {
        this.repairDao = repairDao;
        this.materialDao = materialDao;
    }

    @Override
    public Repair save(Repair repair) {
        Validation.validateString(repair.getDescription());
        Validation.validateString(repair.getArticle());

        return repairDao.save(repair);
    }


    @Override
    public Repair get(long id) {
        Repair result = repairDao.get(id);
        result.setMaterials(materialDao.getMaterial(id));
        // add material

        return result;
    }

    @Override
    public List<Repair> getAll() {
        List<Repair> repairs = repairDao.getAll();

        for (Repair repair: repairs) {
            repair.setMaterials(materialDao.getMaterial(repair.getId()));
        }

        return repairs;
    }


    @Override
    public void delete(long t) {
        repairDao.delete(t);
    }

    public Repair update(List<Material> materials, long amount, long id) {

        //need change
        return null;
    }
}
