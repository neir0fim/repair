package com.kuzin.entity;



public class Material {

    int cod;
    String name;
    String codDk;
    String uom;
    double value;

    public Material() {

    }

    public Material(int cod, String name, String codDk, String uom, double value) {
        this.cod = cod;
        this.name = name;
        this.codDk = codDk;
        this.uom = uom;
        this.value = value;
    }


    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodDk() {
        return codDk;
    }

    public void setCodDk(String codDk) {
        this.codDk = codDk;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                '}';
    }
}
