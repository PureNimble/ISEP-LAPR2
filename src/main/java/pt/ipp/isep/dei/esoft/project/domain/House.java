package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * The House class represents a type of Residence object that includes additional properties such as a basement,
 * an inhabitable loft, and sun exposure.
 * It extends the Residence class and inherits its properties (area, distanceFromCityCenter, numberOfBedrooms,
 * numberOfBathrooms, parkingSpaces, availableEquipment).
 */
public class House extends Residence {

    /**

     The basement of the house.
     */
    private String basement;
    /**

     The inhabitable loft of the house.
     */
    private String inhabitableLoft;
    /**

     The sun exposure of the house.
     */
    private String sunExposure;

    /**
     * Creates a new House object with the given parameters.
     *
     * @param area                   the area of the house
     * @param distanceFromCityCenter the distance from the city center
     * @param numberOfBedrooms       the number of bedrooms in the house
     * @param numberOfBathrooms      the number of bathrooms in the house
     * @param parkingSpaces          the number of parking spaces available
     * @param availableEquipment     the available equipment in the house
     * @param basement               the basement of the house
     * @param inhabitableLoft        the inhabitable loft of the house
     * @param sunExposure            the sun exposure of the house
     * @param photos                 the photos
     */
    public House(int area, int distanceFromCityCenter, int numberOfBedrooms, int numberOfBathrooms, int parkingSpaces, AvailableEquipment availableEquipment, String basement, String inhabitableLoft, String sunExposure, Photos photos, String ID) {
        super(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, ID);
        this.basement = basement;
        this.inhabitableLoft = inhabitableLoft;
        this.sunExposure = sunExposure;
    }

    /**
     * Gets basement.
     *
     * @return the basement
     */
    public String getBasement() {
        return basement;
    }

    /**
     * Gets inhabitable loft.
     *
     * @return the inhabitable loft
     */
    public String getInhabitableLoft() {
        return inhabitableLoft;
    }

    /**
     * Gets sun exposure.
     *
     * @return the sun exposure
     */
    public String getSunExposure() {
        return sunExposure;
    }

    /**
     * Sets basement.
     *
     * @param basement the basement
     */
    public void setBasement(String basement) {
        this.basement = basement;
    }

    /**
     * Sets inhabitable loft.
     *
     * @param inhabitableLoft the inhabitable loft
     */
    public void setInhabitableLoft(String inhabitableLoft) {
        this.inhabitableLoft = inhabitableLoft;
    }

    /**
     * Sets sun exposure.
     *
     * @param sunExposure the sun exposure
     */
    public void setSunExposure(String sunExposure) {
        this.sunExposure = sunExposure;
    }

    /**


     Returns a string representation of the House object.
     @return a string representation of the House object
     */
    @Override
    public String toString() {
        return String.format(super.toString() +
                "Basement: %s\n" +
                "Inhabitable Loft: %s\n" +
                "Sun Exposure: %s\n", basement, inhabitableLoft, sunExposure);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the reference object with which to compare
     @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return basement.equals(house.basement) && inhabitableLoft.equals(house.inhabitableLoft) && sunExposure.equals(house.sunExposure);
    }
    /**

     Returns a hash code value for the object.
     @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(basement, inhabitableLoft, sunExposure);
    }
}