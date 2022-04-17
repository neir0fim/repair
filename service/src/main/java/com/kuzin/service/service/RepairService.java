package com.kuzin.service.service;

import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.entity.Material;
import com.kuzin.entity.Repair;
import com.kuzin.service.service.addition.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RepairService implements ServiceEntity<Repair> {

    RepairDao repairDao;
    WorksMaterialDao materialDao;

    @Autowired
    public RepairService(RepairDao repairDao, WorksMaterialDao materialDao) {
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
        Validation.validateList(repairs);


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
