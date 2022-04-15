package com.kuzin.repair.service;


import com.kuzin.repair.dao.UnitDao;
import com.kuzin.repair.entity.FactoryEntity;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    UnitDao unitDao;



    public FactoryEntity getFactory(FactoryEntity factory) {
//        factory.setUnits();


        return factory;
    }


}
