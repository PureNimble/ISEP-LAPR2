package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Address {
    private String street;
    private int zipcode;
    private District district;
    private City city;
    private State state;


    public Address(String street, int zipcode, District district, City city, State state) {
        this.street = street;
        this.zipcode = zipcode;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public String toString(){
        return String.format("%s,%s,%s,%s,%s",street,city,district,state,zipcode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return zipcode == address.zipcode && street.equals(address.street) && district.equals(address.district) && city.equals(address.city) && state.equals(address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipcode, district, city, state);
    }
}
