package com.kuzin.service.service;

import static com.kuzin.service.service.addition.Validation.*;

import com.kuzin.entity.WorksMaterial;
import com.kuzin.entity.enums.MaterialPost;
import com.kuzin.service.dao.WorksMaterialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**works service class.*/

@Service
public class WorksMaterialService {

    WorksMaterialDao worksMaterialDao;
    ValidService validService;

    @Autowired
    public WorksMaterialService(WorksMaterialDao worksMaterialDao, ValidService validService) {
        this.worksMaterialDao = worksMaterialDao;
        this.validService = validService;
    }


    public void save(MaterialPost worksMaterial) {
        validId(worksMaterial.getRepairId());

        worksMaterialDao.addMaterial(worksMaterial);
    }

    public int deleteMaterial(long t) {
        validId(t);

        return worksMaterialDao.deleteMaterial(t);
    }

    public int deleteMaterials(long id) {
        validId(id);

        return worksMaterialDao.deleteAll(id);
    }

    public int updateMaterial(WorksMaterial worksMaterial) {
        return worksMaterialDao.updateMaterial(worksMaterial);
    }
}
