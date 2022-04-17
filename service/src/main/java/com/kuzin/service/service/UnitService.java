package com.kuzin.service.service;

import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.PersonDao;
import com.kuzin.service.dao.UnitDao;
import com.kuzin.entity.Unit;
import com.kuzin.service.service.addition.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        List<Unit> result = dao.getAll();
        Validation.validateList(result);

        for (Unit unit : result) {
            unit.setArticleList(articleDao.getForUnit(unit.getId()));
        }

        return result;
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
