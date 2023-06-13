package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Business class represents a business with its price.
 */
public class Business implements Serializable {
    /**
     * The price associated with the object.
     */
    private double price;

    /**
     * Constructs a business object with the given price.
     *
     * @param price The price of the business.
     */
    public Business(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the business.
     *
     * @return The price of the business.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**

     Returns a string representation of the business object.
     @return The string representation of the business object.
     */
    public String toString(){
        return String.format("%s",price);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o The reference object with which to compare.
     @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return price == business.price;
    }
    /**

     Returns a hash code value for the business object.
     @return A hash code value for the business object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
