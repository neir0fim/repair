package com.kuzin.entity;



import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**entity class.*/

@Component
public class Factory {
    private final String name;
    private List<Unit> units;

    public Factory(@Value("${repair.factory.name}") String name) {
        this.name = name;
        units = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public List<Unit> getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Factory{"
                + "name='" + name + '\''
                + ", units=" + units + '}';
    }
}
