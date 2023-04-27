package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class PropertyType {
    private String designation;

    public PropertyType(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public String toString(){
        return String.format(designation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyType that = (PropertyType) o;
        return designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    public PropertyType clone(){
        return new PropertyType(this.designation);
    }
}
