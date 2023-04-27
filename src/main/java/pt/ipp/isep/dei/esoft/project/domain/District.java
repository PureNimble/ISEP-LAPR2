package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

public class District {
    private String district;
    private List<City> cities;
    public District(String district,List<City> cities) {
        this.district = district;
        this.cities = cities;
    }

    public District(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public List<City> getCities() {
        return cities;
    }

    public String toString(){
        return String.format(district);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district1 = (District) o;
        return district.equals(district1.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(district);
    }

    public District clone(){
        return new District(this.district);
    }
}
