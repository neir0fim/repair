package com.kuzin.service.service;

import com.kuzin.entity.Unit;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.UnitDao;
import com.kuzin.service.service.addition.Validation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**unit service class.*/

@Service
public class UnitService implements ServiceEntity<Unit> {

    UnitDao dao;
    ArticleDao articleDao;


    @Autowired
    public UnitService(UnitDao dao, ArticleDao articleDao) {
        this.dao = dao;
        this.articleDao = articleDao;
    }

    @Override
    public Unit get(long id) {
        Unit result = dao.get(id);
        result.setArticleList(articleDao.getForUnit(id));

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

    public void update(Unit unit, long id) {
        dao.update(unit, id);
    }
}
