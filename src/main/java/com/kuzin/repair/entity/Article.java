package com.kuzin.repair.entity;


import java.util.Objects;


public class Article {


    private String article;

    private int id;

    private String type;

    public Article(String article, int id, String type) {
        this.article = article;
        this.id = id;
        this.type = type;
    }

    public Article() {

    }

    public String getArticle() {
        return article;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article1 = (Article) o;
        return id == article1.id && Objects.equals(article, article1.article) && Objects.equals(type, article1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, id, type);
    }

    @Override
    public String toString() {
        return article;
    }
}
