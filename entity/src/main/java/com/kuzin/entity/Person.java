package com.kuzin.entity;


import com.kuzin.entity.enums.ApplicationUserRole;

/**entity class.*/
public class Person {

    private String name;
    private String pass;
    private ApplicationUserRole role;
    private long unitId;
    private boolean enabled;

    public Person(String name, String pass, boolean enabled, long unitId) {
        this.name = name;
        this.pass = pass;
        this.unitId = unitId;
        this.enabled = enabled;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ApplicationUserRole getRole() {
        return role;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }
}
