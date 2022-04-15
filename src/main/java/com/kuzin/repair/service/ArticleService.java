package com.kuzin.repair.service;

import com.kuzin.repair.dao.ArticleDao;
import com.kuzin.repair.entity.Article;
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
        return articleDao.getAll();
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
