package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * Address represents a physical address that includes the street, zipcode, district, city, and state.
 */
public class Address implements Serializable {

    /**
     * The street of the address.
     */
    private String street;
    /**
     * The zipcode of the address.
     */
    private int zipcode;
    /**
     * The district of the address.
     */
    private District district;
    /**
     * The city of the address.
     */
    private City city;
    /**
     * The state of the address.
     */
    private State state;


    /**
     * Creates a new instance of Address with the given parameters.
     *
     * @param street   the street of the address.
     * @param zipcode  the zipcode of the address.
     * @param district the district of the address.
     * @param city     the city of the address.
     * @param state    the state of the address.
     */
    public Address(String street, int zipcode, District district, City city, State state) {
        this.street = street;
        this.zipcode = zipcode;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    /**
     * Instantiates a new Address.
     *
     * @param street  the street
     * @param zipcode the zipcode
     * @param city    the city
     * @param state   the state
     */
    public Address(String street, int zipcode, City city, State state) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
    }


    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets zipcode.
     *
     * @return the zipcode
     */
    public int getZipcode() {
        return zipcode;
    }

    /**
     * Sets zipcode.
     *
     * @param zipcode the zipcode
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Gets district.
     *
     * @return the district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * Sets district.
     *
     * @param district the district
     */
    public void setDistrict(District district) {
        this.district = district;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Returns a string representation of the address in the format "street,city,district,state,zipcode".
     *
     * @return a string representation of the address.
     */
    public String toString() {
        String toString = "";
        if (district != null) {
            toString = String.format("%s, %s, %s, %s, %s", street, city, district, state, zipcode);
        } else {
            toString = String.format("%s, %s, %s, %s", street, city, state, zipcode);
        }
        return toString;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the object to compare this address against.
     * @return true if the given object represents an Address equivalent to this address, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipcode == address.zipcode && street.equals(address.street) && district.equals(address.district) && city.equals(address.city) && state.equals(address.state);
    }

    /**
     * Returns a hash code value for the address.
     *
     * @return a hash code value for the address.
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, zipcode, district, city, state);
    }
}