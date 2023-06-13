package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * City represents a city in the context of an address.
 */
public class City implements Serializable {

    /**

     The name of the city.
     */
    private String city;

    /**
     * Creates a new instance of City with the given name.
     *
     * @param city the name of the city.
     */
    public City(String city) {
        this.city = city;
    }

    /**
     * Returns the name of the city.
     *
     * @return the name of the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**

     Returns a string representation of the city.
     @return a string representation of the city.
     */
    public String toString(){
        return String.format(city);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the object to compare this city against.
     @return true if the given object represents a City equivalent to this city, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return city.equals(city1.city);
    }
    /**

     Returns a hash code value for the city.
     @return a hash code value for the city.
     */
    @Override
    public int hashCode() {
        return Objects.hash(city);
    }
    /**

     Returns a clone of this city.
     @return a clone of this city.
     */
    public City clone(){
        return new City(this.city);
    }
}
