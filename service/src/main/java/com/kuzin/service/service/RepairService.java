package com.kuzin.service.service;

import com.kuzin.entity.Repair;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.service.addition.Validation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** repair service class.*/
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

        return result;
    }

    @Override
    public List<Repair> getAll() {
        List<Repair> repairs = repairDao.getAll();
        Validation.validateList(repairs);

        for (Repair repair : repairs) {
            repair.setMaterials(materialDao.getMaterial(repair.getId()));
        }
        return repairs;
    }


    @Override
    public void delete(long t) {
        repairDao.delete(t);
    }

    public void update(Repair repair, long id) {
        repairDao.update(repair, id);
    }
}
