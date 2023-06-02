package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * A class representing a rental contract with a certain duration.
 */
public class Rent {

    /**

     The duration of the rental contract, in months.
     */
    private int durationContract;

    /**
     * Creates a new Rent object with the specified duration.
     *
     * @param durationContract the duration of the rental contract, in months.
     */
    public Rent(int durationContract) {
        this.durationContract = durationContract;
    }

    /**
     * Returns the duration of the rental contract.
     *
     * @return the duration of the rental contract, in months.
     */
    public int getDurationContract() {
        return durationContract;
    }

    /**
     * Sets duration contract.
     *
     * @param durationContract the duration contract
     */
    public void setDurationContract(int durationContract) {
        this.durationContract = durationContract;
    }

    /**

     Compares this Rent object to the specified object for equality.
     @param o the object to compare to.
     @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return durationContract == rent.durationContract;
    }
    /**

     Returns the hash code for this Rent object.
     @return the hash code for this Rent object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(durationContract);
    }
}