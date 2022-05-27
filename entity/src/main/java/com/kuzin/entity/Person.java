package com.kuzin.entity;


import com.kuzin.entity.enums.ApplicationUserRole;
import java.util.Objects;

/**entity class.*/
public class Person {

    private String name;
    private String pass;
    private ApplicationUserRole role;
    private String type;
    private boolean enabled;

    public Person(String name, String pass, boolean enabled, String type) {
        this.name = name;
        this.pass = pass;
        this.type = type;
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


    public void setRole(ApplicationUserRole role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return enabled == person.enabled && Objects.equals(name, person.name)
                && Objects.equals(pass, person.pass) && role == person.role
                && Objects.equals(type, person.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pass, role, type, enabled);
    }
}
