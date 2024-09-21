package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Property type.
 */
public class PropertyType implements Serializable {
    /**
     * The designation of the entity.
     */
    private String designation;

    /**
     * Constructor for PropertyType Class
     *
     * @param designation the designation of the property type
     */
    public PropertyType(String designation) {
        this.designation = designation;
    }

    /**
     * Returns the designation of the property type
     *
     * @return the designation of the property type
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets designation.
     *
     * @param designation the designation
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns a string representation of the PropertyType object
     * @return the string representation of the property type
     */
    public String toString(){
        return String.format(designation);
    }

    /**
     * Indicates whether some other object is "equal to" this one
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyType that = (PropertyType) o;
        return designation.equals(that.designation);
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    /**
     * Creates and returns a copy of this object.
     * @return a clone of this instance.
     */
    public PropertyType clone(){
        return new PropertyType(this.designation);
    }
}
