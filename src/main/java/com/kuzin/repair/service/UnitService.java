package com.kuzin.repair.service;

import com.kuzin.repair.dao.ArticleDao;
import com.kuzin.repair.dao.PersonDao;
import com.kuzin.repair.dao.UnitDao;
import com.kuzin.repair.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UnitService implements ServiceEntity<Unit> {

    UnitDao dao;
    ArticleDao articleDao;
    PersonDao personDao;

    @Autowired
    public UnitService(UnitDao dao, ArticleDao articleDao, PersonDao personDao) {
        this.dao = dao;
        this.articleDao = articleDao;
        this.personDao = personDao;
    }

    @Override
    public Unit get(long id) {
        Unit result = dao.get(id);
        result.setArticleList(articleDao.getForUnit(id));
        result.setPersons(personDao.getPersonUnit(id));

        return result;
    }

    @Override
    public List<Unit> getAll() {
        return dao.getAll();
    }

    @Override
    public Unit save(Unit unit) {
        return dao.save(unit);
    }

    @Override
    public void delete(long t) {
        dao.delete(t);
    }


}
