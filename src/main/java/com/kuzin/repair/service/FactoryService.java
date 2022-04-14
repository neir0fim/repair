package com.kuzin.repair.service;


import com.kuzin.repair.dao.UnitDao;
import com.kuzin.repair.entity.Factory;
import org.springframework.stereotype.Service;

@Service
public class FactoryService {

    UnitDao unitDao;



    public Factory getFactory(Factory factory) {
//        factory.setUnits();
//        factory.setPersons();

        return factory;
    }


}
