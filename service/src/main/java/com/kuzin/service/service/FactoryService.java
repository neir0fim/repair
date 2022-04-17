package com.kuzin.service.service;


import com.kuzin.service.dao.UnitDao;
import com.kuzin.entity.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    UnitDao unitDao;
    Factory factory;

    @Autowired
    public FactoryService(UnitDao unitDao, Factory factory) {
        this.unitDao = unitDao;
        this.factory = factory;
    }

    public Factory getFactory() {
        factory.setUnits(unitDao.getAll());

        return factory;
    }


}
