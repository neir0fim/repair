package com.kuzin.entity;



import java.util.Objects;
import java.util.List;


public class Repair {

    private long id;

    private String description;

    private String article;

    private String type;

    private List<WorksMaterial> materials;


    public Repair() {

    }

    public Repair(String description, String article) {
        this.description = description;
        this.article = article;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<WorksMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<WorksMaterial> materials) {
        this.materials = materials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return id == repair.id && Objects.equals(description, repair.description) && Objects.equals(article, repair.article) && Objects.equals(type, repair.type) && Objects.equals(materials, repair.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, article, type, materials);
    }

    @Override
    public String toString() {
        return "Repair:" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", article='" + article + '\'' +
                ", type='" + type + '\'';
    }
}
