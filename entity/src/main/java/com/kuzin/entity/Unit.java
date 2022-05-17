package com.kuzin.entity;


import java.util.List;

/** entity class.*/
public class Unit {

    long id;
    String kind;
    List<Article> articleList;

    public Unit(long id, String kind) {
        this.id = id;
        this.kind = kind;
    }

    public Unit() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


    @Override
    public String toString() {
        return "Unit{"
                + "type=" + kind
                + ", articleList=" + articleList + "}";
    }
}
