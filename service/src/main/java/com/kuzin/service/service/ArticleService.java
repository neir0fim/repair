package com.kuzin.service.service;

import com.kuzin.service.dao.ArticleDao;
import com.kuzin.entity.Article;
import com.kuzin.service.service.addition.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements ServiceEntity<Article> {

    ArticleDao articleDao;

    @Autowired
    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
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

    @Override
    public Article save(Article article) {
        return articleDao.save(article);
    }

    @Override
    public void delete(long t) {
        articleDao.delete(t);
    }
}
