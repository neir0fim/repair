package com.kuzin.repair.entity;


import com.kuzin.repair.entity.enums.Units;
import java.util.List;


public class Unit {

    Units type;
    List<Article> articleList;

    public Unit(Units type, List<Article> articleList) {
        this.type = type;
        this.articleList = articleList;
    }

    public Units getType() {
        return type;
    }

    public void setType(Units type) {
        this.type = type;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


    @Override
    public String toString() {
        return "Unit{" +
                "type=" + type +
                ", articleList=" + articleList +
                '}';
    }
}
