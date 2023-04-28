package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 Address represents a physical address that includes the street, zipcode, district, city, and state.
 */
public class Address {

    /**

     The street of the address.
     */
    private String street;
    /**

     The zipcode of the address.
     */
    private int zipcode;
    /**

     The district of the address.
     */
    private District district;
    /**

     The city of the address.
     */
    private City city;
    /**

     The state of the address.
     */
    private State state;
    /**

     Creates a new instance of Address with the given parameters.
     @param street the street of the address.
     @param zipcode the zipcode of the address.
     @param district the district of the address.
     @param city the city of the address.
     @param state the state of the address.
     */
    public Address(String street, int zipcode, District district, City city, State state) {
        this.street = street;
        this.zipcode = zipcode;
        this.district = district;
        this.city = city;
        this.state = state;
    }
    /**

     Returns a string representation of the address in the format "street,city,district,state,zipcode".
     @return a string representation of the address.
     */
    public String toString(){
        return String.format("%s,%s,%s,%s,%s",street,city,district,state,zipcode);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the object to compare this address against.
     @return true if the given object represents an Address equivalent to this address, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipcode == address.zipcode && street.equals(address.street) && district.equals(address.district) && city.equals(address.city) && state.equals(address.state);
    }
    /**

     Returns a hash code value for the address.
     @return a hash code value for the address.
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, zipcode, district, city, state);
    }
}