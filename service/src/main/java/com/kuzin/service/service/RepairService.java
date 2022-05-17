package com.kuzin.service.service;

import com.kuzin.entity.Repair;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.service.addition.Validation;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

/** repair service class.*/
@Service
public class RepairService {

    RepairDao repairDao;
    WorksMaterialDao materialDao;
    ValidService validService;

    @Autowired
    public RepairService(RepairDao repairDao, WorksMaterialDao materialDao,
                         ValidService validService) {
        this.repairDao = repairDao;
        this.materialDao = materialDao;
        this.validService = validService;
    }

    public long save(Repair repair) throws AccessException {
        validService.filter(validService.getTypeByValue(repair.getArticle()));


        Validation.validateString(repair.getDescription());
        Validation.validateString(repair.getArticle());

        return repairDao.save(repair);
    }


    public Repair get(long id) throws AccessException {
        validService.filter(validService.getRepairType(id));

        Repair result = repairDao.get(id);
        result.setMaterials(materialDao.getMaterial(id));

        return result;
    }

    public List<Repair> getRepairArticle(long id) throws AccessException {
        validService.filter(validService.getType(id));


        List<Repair> repairs = repairDao.getRepairForArticle(id);
        Validation.validateList(repairs);

        for (Repair repair : repairs) {
            repair.setMaterials(materialDao.getMaterial(repair.getId()));
        }
        return repairs;
    }


    public int delete(long t) throws AccessException {
        validService.filter(validService.getRepairType(t));

        return repairDao.delete(t);
    }

    public int update(Repair repair, long id) throws AccessException {
        validService.filter(validService.getRepairType(id));

        return repairDao.update(repair, id);
    }

    public List<Repair> getAll() {
        return Collections.emptyList();
    }
}
