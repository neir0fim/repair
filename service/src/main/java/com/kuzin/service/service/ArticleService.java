package com.kuzin.service.service;

import static com.kuzin.service.service.addition.Validation.*;
import static java.util.stream.Collectors.summingDouble;

import com.kuzin.entity.Article;
import com.kuzin.entity.Repair;
import com.kuzin.entity.WorksMaterial;
import com.kuzin.service.dao.ArticleDao;
import com.kuzin.service.dao.MaterialDao;
import com.kuzin.service.dao.RepairDao;
import com.kuzin.service.dao.WorksMaterialDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;



/** article service class.*/

@Service
public class ArticleService {

    ArticleDao articleDao;
    RepairDao repairDao;
    WorksMaterialDao worksMaterialDao;
    MaterialDao materialDao;
    ValidService validService;

    @Autowired
    public ArticleService(ArticleDao articleDao, RepairDao repairDao,
                          WorksMaterialDao worksMaterialDao, MaterialDao materialDao,
                          ValidService validService) {
        this.articleDao = articleDao;
        this.repairDao = repairDao;
        this.worksMaterialDao = worksMaterialDao;
        this.materialDao = materialDao;
        this.validService = validService;
    }

    public Article get(long id) throws AccessException {
        validService.filter(validService.getType(id));

        Article article = articleDao.get(id);
        article.setRepairList(repairDao.getRepairForArticle(id));

        return article;
    }

    public List<Article> getAll() {
        List<Article> result = articleDao.getAll();
        validateList(result);

        return result;
    }

    public void update(Article article, long id) {
        validateString(article.getValue());
        validateString(article.getType());

        articleDao.update(article, id);
    }

    public Article save(Article article) {
        validateString(article.getValue());


        return articleDao.save(article);
    }

    public int delete(long t) {
        validId(t);

        return articleDao.delete(t);
    }

    public List<WorksMaterial> getReport(long id) throws AccessException {
        List<WorksMaterial> materials = getList(id);

        Map<Integer, Double> such = materials.stream().collect(Collectors
                .groupingBy((WorksMaterial::getCod), summingDouble(WorksMaterial::getAmount)));

        List<WorksMaterial> result = new ArrayList<>();

        for (Map.Entry<Integer, Double> entry : such.entrySet()) {
            result.add(new WorksMaterial(materialDao.get(entry.getKey()), entry.getValue()));
        }

        return result;
    }

    public List<Repair> filerByDescription(long id, String filter) throws AccessException {
        Article article = articleDao.get(id);
        validService.filter(article.getType());
        List<Integer> index = repairDao.repairForArticle(article.getValue());
        List<Repair> repairs = new ArrayList<>();

        for (Integer integer : index) {
            repairs.add(repairDao.get(integer));
        }

        return repairs.stream().filter(repair -> repair.getDescription()
                .contains(filter)).toList();
    }


    private List<WorksMaterial> getList(long id) throws AccessException {
        List<Integer> repair = repairDao.repairForArticle(articleDao.get(id).getValue());
        List<WorksMaterial> materials = new ArrayList<>();

        for (Integer integer : repair) {
            materials.addAll(worksMaterialDao.getMaterial(integer));
        }
        return materials;
    }

    public List<Article> getForUser() {
        return articleDao.getForUser();
    }

}
