package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Property {
    private int distanceFromCityCenter;
    private int area;

    public Property(int area, int distanceFromCityCenter) {
        this.distanceFromCityCenter = distanceFromCityCenter;
        this.area = area;
    }

    public String toString(){
        return String.format("Area: %s\n" +
                        "DistanceFromCityCenter:%s\n"
                ,area,distanceFromCityCenter);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return distanceFromCityCenter == property.distanceFromCityCenter && area == property.area;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distanceFromCityCenter, area);
    }

    public Property clone(){
        return new Property(this.distanceFromCityCenter,this.area);
    }
}
