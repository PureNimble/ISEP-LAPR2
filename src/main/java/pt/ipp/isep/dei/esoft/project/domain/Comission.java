package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents the commission charged for a business transaction.
 */
public class Comission {

    /** The amount of the commission. */
    private double comission;

    /**
     * Creates a new commission object with the specified amount.
     * @param comission the amount of the commission
     */
    public Comission(double comission) {
        this.comission = comission;
    }

    /**
     * Returns a string representation of the commission object.
     * @return a string representation of the commission object
     */
    public String toString(){
        return String.format("%s\n",comission);
    }

    /**
     * Returns the amount of the commission.
     * @return the amount of the commission
     */
    public double getComission() {
        return comission;
    }

    /**
     * Compares this commission object to the specified object.
     * Returns true if the objects are equal, false otherwise.
     * @param o the object to compare to this commission object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comission comission1 = (Comission) o;
        return Double.compare(comission1.comission, comission) == 0;
    }

    /**
     * Returns a hash code for this commission object.
     * @return a hash code for this commission object
     */
    @Override
    public int hashCode() {
        return Objects.hash(comission);
    }
}
