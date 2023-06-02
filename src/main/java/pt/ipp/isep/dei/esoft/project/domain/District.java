package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The District class represents a district object that contains a name and a list of cities.
 */
public class District {
    private String district;
    private List<City> cities = new ArrayList<>();

    /**
     * Creates a new district object with a specified name and list of cities.
     *
     * @param district the name of the district
     * @param cities   the list of cities in the district
     */
    public District(String district,List<City> cities) {
        this.district = district;
        this.cities = cities;
    }

    /**
     * Creates a new district object with a specified name.
     *
     * @param district the name of the district
     */
    public District(String district) {
        this.district = district;
    }

    /**
     * Returns the name of the district.
     *
     * @return the name of the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Returns the list of cities in the district.
     *
     * @return the list of cities in the district
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Sets district.
     *
     * @param district the district
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Sets cities.
     *
     * @param cities the cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Returns the string representation of the district.
     *
     * @return the string representation of the district
     */
    public String toString(){
        return String.format(district);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district1 = (District) o;
        return district.equals(district1.district);
    }

    /**
     * Returns a hash code value for the district.
     *
     * @return a hash code value for the district
     */
    @Override
    public int hashCode() {
        return Objects.hash(district);
    }

    /**
     * Creates and returns a copy of the district.
     *
     * @return a new district object that is a copy of this instance
     */
    public District clone(){
        return new District(this.district);
    }
}