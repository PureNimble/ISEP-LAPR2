package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Property class represents a real estate property with an area and a distance from the city center.
 */
public class Property implements Serializable {
    /**
     * The distance from the city center to the property.
     */
    private double distanceFromCityCenter;
    /**
     * The area of the property.
     */
    private double area;
    /**
     * The photos of the property.
     */
    private Photos photos;
    /**
     * The residence type of the property.
     */
    private Residence residence;
    /**
     * The address of the property.
     */
    private Address address;

    /**
     * Constructs a Property object with a given area and distance from the city center.
     *
     * @param area                   The area of the property.
     * @param distanceFromCityCenter The distance from the city center in kilometers.
     * @param photos                 the photos
     * @param address                the address
     */
    public Property(double area, double distanceFromCityCenter, Photos photos, Address address) {
        this.distanceFromCityCenter = distanceFromCityCenter;
        this.area = area;
        this.photos = photos;
        this.address = address;
    }

    /**
     * Instantiates a new Property.
     */
    public Property() {

    }

    /**
     * Instantiates a new Property.
     *
     * @param distanceFromCityCenter the distance from city center
     * @param area                   the area
     * @param address                the address
     */
    public Property(double distanceFromCityCenter, double area, Address address) {
        this.distanceFromCityCenter = distanceFromCityCenter;
        this.area = area;
        this.address = address;
    }

    /**
     * Instantiates a new Property.
     *
     * @param residence the residence
     */
    public Property(Residence residence) {
        this.residence = residence;
    }

    /**
     * Gets number of bedrooms.
     *
     * @return the number of bedrooms
     */
    public double getNumberOfBedrooms() {
        if (residence != null) {
            return residence.getNumberOfBedrooms();
        } else return 0;
    }

    /**
     * Gets residence.
     *
     * @return the residence
     */
    public Residence getResidence() {
        return residence;
    }

    /**
     * Sets residence.
     *
     * @param residence the residence
     */
    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets distance from city center.
     *
     * @return the distance from city center
     */
    public double getDistanceFromCityCenter() {
        return distanceFromCityCenter;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public double getArea() {
        return area;
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public Photos getPhotos() {
        return photos;
    }

    /**
     * Sets photos.
     *
     * @param photos the photos
     */
    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    /**
     * Sets distance from city center.
     *
     * @param distanceFromCityCenter the distance from city center
     */
    public void setDistanceFromCityCenter(int distanceFromCityCenter) {
        this.distanceFromCityCenter = distanceFromCityCenter;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }


    /**
     * Returns a string representation of the Property object.
     *
     * @return A string representation of the Property object, including its area and distance from the city center.
     */
    public String toString() {
        String toString = "";
        if (photos != null) {
           toString = String.format("Area: %s\n" + "Distance From The City Center: %s\n" + "Photos: %s\n" + "Address: %s\n", area, distanceFromCityCenter, photos.getUrl(), address);
        }else {
            toString = String.format("Area: %s\n" + "Distance From The City Center: %s\n" +  "Address: %s\n", area, distanceFromCityCenter, address);
        }
        return toString;
    }

    /**
     * Compares this Property object to another object for equality.
     *
     * @param o The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return distanceFromCityCenter == property.distanceFromCityCenter && area == property.area && photos == property.photos;
    }

    /**
     * Returns a hash code value for the Property object.
     *
     * @return A hash code value for the Property object based on its area and distance from the city center.
     */
    @Override
    public int hashCode() {
        return Objects.hash(distanceFromCityCenter, area, photos, address);
    }

    /**
     * Creates and returns a new Property object that is a copy of this one.
     *
     * @return A new Property object with the same area and distance from the city center as this one.
     */
    public Property clone() {
        return new Property(this.distanceFromCityCenter, this.area, this.photos, this.address);
    }
}