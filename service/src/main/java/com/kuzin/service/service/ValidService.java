package com.kuzin.service.service;

import com.kuzin.service.dao.ValidDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

/** service class.*/
@Service
public class ValidService {

    ValidDao validDao;

    @Autowired
    public ValidService(ValidDao validDao) {
        this.validDao = validDao;
    }

    public void filter(String type) throws AccessException {
        validDao.filter(type);
    }


    public String getType(long id) {
        return validDao.getType(id);
    }

    public String getTypeByValue(String value) {
        return validDao.getTypeByValue(value);
    }

    public String getRepairType(long id) {
        return validDao.getRepairType(id);
    }
}
