package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The Property class represents a real estate property with an area and a distance from the city center.
 */
public class Property {
    private int distanceFromCityCenter;
    private int area;
    private Photos photos;
    private Residence residence;
    private Address address;

    /**
     * Constructs a Property object with a given area and distance from the city center.
     *
     * @param area                   The area of the property.
     * @param distanceFromCityCenter The distance from the city center in kilometers.
     * @param photos                 the photos
     */
    public Property(int area, int distanceFromCityCenter, Photos photos, Address address) {
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

    public Property(Residence residence) {
        this.residence = residence;
    }

    public int getNumberOfBedrooms() {
        if (residence != null) {
            return residence.getNumberOfBedrooms();
        } else return 0;
    }

    /**
     * Gets distance from city center.
     *
     * @return the distance from city center
     */
    public int getDistanceFromCityCenter() {
        return distanceFromCityCenter;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    public int getArea() {
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

    public Address getAddress() {
        return address;
    }


    /**
     * Returns a string representation of the Property object.
     *
     * @return A string representation of the Property object, including its area and distance from the city center.
     */
    public String toString() {
        return String.format("Area: %s\n" + "Distance From The City Center: %s\n" + "Photos: %s\n" + "Address: %s\n", area, distanceFromCityCenter, photos.getUrl(), address);
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
