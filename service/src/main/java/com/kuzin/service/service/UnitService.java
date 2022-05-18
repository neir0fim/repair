package com.kuzin.service.service;

import static com.kuzin.service.service.addition.Validation.*;

import com.kuzin.entity.Unit;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.UnitDao;
import com.kuzin.service.service.addition.Validation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**unit service class.*/

@Service
public class UnitService {

    UnitDao dao;
    ArticleDao articleDao;


    @Autowired
    public UnitService(UnitDao dao, ArticleDao articleDao) {
        this.dao = dao;
        this.articleDao = articleDao;
    }

    public Unit get(long id) {
        validId(id);

        Unit result = dao.get(id);
        result.setArticleList(articleDao.getForUnit(id));

        return result;
    }

    public Unit getUnit(String input) {
        validateString(input);
        long id = Long.getLong(input);


        Unit result = dao.get(id);
        result.setArticleList(articleDao.getForUnit(id));

        return result;
    }

    public List<Unit> getAll() {
        List<Unit> result = dao.getAll();
        validateList(result);

        for (Unit unit : result) {
            unit.setArticleList(articleDao.getForUnit(unit.getId()));
        }

        return result;
    }

    public long save(Unit unit) {
        validateString(unit.getKind());

        return dao.save(unit);
    }


    public int delete(long t) {
        return dao.delete(t);
    }

    public void update(Unit unit, long id) {
        Validation.validateString(unit.getKind());

        dao.update(unit, id);
    }
}
