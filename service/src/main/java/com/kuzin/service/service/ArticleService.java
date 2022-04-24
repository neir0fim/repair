package com.kuzin.service.service;

import static java.util.stream.Collectors.summingDouble;

import com.kuzin.entity.Article;
import com.kuzin.entity.Material;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.MaterialDao;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import com.kuzin.service.service.addition.Validation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/** article service class.*/

@Service
public class ArticleService implements ServiceEntity<Article> {

    ArticleDao articleDao;
    RepairDao repairDao;
    WorksMaterialDao worksMaterialDao;
    MaterialDao materialDao;

    @Autowired
    public ArticleService(ArticleDao articleDao, RepairDao repairDao,
                          WorksMaterialDao worksMaterialDao, MaterialDao materialDao) {
        this.articleDao = articleDao;
        this.repairDao = repairDao;
        this.worksMaterialDao = worksMaterialDao;
        this.materialDao = materialDao;
    }

    @Override
    public Article get(long id) {
        return articleDao.get(id);
    }

    @Override
    public List<Article> getAll() {
        List<Article> result = articleDao.getAll();
        Validation.validateList(result);

        return result;
    }


    public void update(Article article, long id) {
        articleDao.update(article, id);
    }

    @Override
    public Article save(Article article) {
        return articleDao.save(article);
    }

    @Override
    public void delete(long t) {
        articleDao.delete(t);
    }

    public Map<Material, Double> getReport(long id) {
        List<Integer> repair = repairDao.repairForArticle(id);
        List<WorksMaterial> materials = new ArrayList<>();

        for (Integer integer : repair) {
            materials.addAll(worksMaterialDao.getMaterial(integer));
        }

        Map<Integer, Double> such = materials.stream().collect(Collectors
                .groupingBy((WorksMaterial::getCod), summingDouble(WorksMaterial::getAmount)));

        Map<Material, Double> result = new HashMap<>();

        for (Map.Entry<Integer, Double> entry : such.entrySet()) {
            result.put(materialDao.get(entry.getKey()), entry.getValue());
        }


        return result;
    }
}
