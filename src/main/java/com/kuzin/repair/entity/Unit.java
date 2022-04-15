package com.kuzin.repair.entity;


import com.kuzin.repair.entity.enums.Units;
import java.util.List;


public class Unit {

    long id;
    String type;
    List<Article> articleList;
    List<Person> persons;

    public Unit(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public Unit() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String  type) {
        this.type = type;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "type=" + type +
                ", articleList=" + articleList +
                '}';
    }
}
