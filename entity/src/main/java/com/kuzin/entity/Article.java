package com.kuzin.entity;


import java.util.List;
import java.util.Objects;

/**entity class.*/
public class Article {
    private String value;
    private long unitId;
    private String type;
    private long id;
    private List<Repair> repairList;


    public Article(String value, long unitId, String type, long id) {
        this.value = value;
        this.unitId = unitId;
        this.type = type;
        this.id = id;
    }

    public Article() {

    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
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

    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Article article1 = (Article) o;
        return id == article1.id && Objects.equals(value, article1.value)
                && Objects.equals(type, article1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id, type);
    }

    @Override
    public String toString() {
        return "Article{" +
                "value='" + value + '\'' +
                ", unitId=" + unitId +
                ", type='" + type + '\'' +
                ", id=" + id +
                ", repairList=" + repairList +
                '}';
    }
}
