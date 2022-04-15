package com.kuzin.repair.entity;


import java.util.Objects;


public class Article {


    private String article;
    private long unit_id;
    private String type;
    private long id;


    public Article(String article, long unit_id, String type, long id) {
        this.article = article;
        this.unit_id = unit_id;
        this.type = type;
        this.id = id;
    }

    public Article() {

    }


    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public long getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(long unit_id) {
        this.unit_id = unit_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
