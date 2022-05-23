package com.kuzin.entity;


import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Unit unit = (Unit) o;
        return id == unit.id && Objects.equals(kind, unit.kind) && Objects.equals(articleList, unit.articleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kind, articleList);
    }

    @Override
    public String toString() {
        return "Unit{"
                + "type=" + kind
                + ", articleList=" + articleList + "}";
    }
}
