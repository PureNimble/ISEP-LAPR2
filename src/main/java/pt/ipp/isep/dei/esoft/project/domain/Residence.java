package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * Represents a residential property.
 */
public class Residence extends Property{

    /** The number of bedrooms in the residence */
    private int numberOfBedrooms;

    /** The number of bathrooms in the residence */
    private int numberOfBathrooms;

    /** The number of parking spaces in the residence */
    private int parkingSpaces;

    /** The available equipment in the residence */
    private AvailableEquipment availableEquipment;

    /**
     * Constructs a new Residence object.
     *
     * @param area                   The area of the residence.
     * @param distanceFromCityCenter The distance of the residence from the city center.
     * @param numberOfBedrooms       The number of bedrooms in the residence.
     * @param numberOfBathrooms      The number of bathrooms in the residence.
     * @param parkingSpaces          The number of parking spaces in the residence.
     * @param availableEquipment     The available equipment in the residence.
     * @param photos                 the photos
     */
    public Residence(int area, int distanceFromCityCenter, int numberOfBedrooms, int numberOfBathrooms, int parkingSpaces, AvailableEquipment availableEquipment, Photos photos, Address address) {
        super(area, distanceFromCityCenter, photos, address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.parkingSpaces = parkingSpaces;
        this.availableEquipment = availableEquipment;
    }
    /**
     * Constructs a new Residence object with the specified parameters.
     *
     * @param area                 The area of the residence.
     * @param distanceFromCityCenter The distance from the city center of the residence.
     * @param numberOfBedrooms     The number of bedrooms in the residence.
     * @param numberOfBathrooms    The number of bathrooms in the residence.
     * @param parkingSpaces        The number of parking spaces available in the residence.
     * @param availableEquipment   The available equipment in the residence.
     * @param address              The address of the residence.
     */
    public Residence(int area, int distanceFromCityCenter, int numberOfBedrooms, int numberOfBathrooms, int parkingSpaces, AvailableEquipment availableEquipment,Address address) {
        super(area, distanceFromCityCenter,address);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.parkingSpaces = parkingSpaces;
        this.availableEquipment = availableEquipment;
    }



    /**
     * Gets number of bedrooms.
     *
     * @return the number of bedrooms
     */
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    /**
     * Gets number of bathrooms.
     *
     * @return the number of bathrooms
     */
    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    /**
     * Gets parking spaces.
     *
     * @return the parking spaces
     */
    public int getParkingSpaces() {
        return parkingSpaces;
    }

    /**
     * Sets number of bedrooms.
     *
     * @param numberOfBedrooms the number of bedrooms
     */
    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    /**
     * Sets number of bathrooms.
     *
     * @param numberOfBathrooms the number of bathrooms
     */
    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    /**
     * Sets parking spaces.
     *
     * @param parkingSpaces the parking spaces
     */
    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    /**
     * Sets available equipment.
     *
     * @param availableEquipment the available equipment
     */
    public void setAvailableEquipment(AvailableEquipment availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    /**
     * Gets available equipment.
     *
     * @return the available equipment
     */
    public AvailableEquipment getAvailableEquipment() {
        return availableEquipment;
    }

    /**

     Returns a string representation of the Residence object.
     @return A string representation of the Residence object.
     */
    public String toString(){
        return String.format(super.toString()+
                "Number Of Bedrooms: %s\n" +
                "Number Of Bathrooms: %s\n" +
                "Parking Spaces: %s\n" +
                "Available Equipment: %s\n",numberOfBedrooms,numberOfBathrooms,parkingSpaces,availableEquipment);
    }
    /**

     Compares the Residence object to another object to determine if they are equal.
     @param o The object to compare to.
     @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Residence residence = (Residence) o;
        return numberOfBedrooms == residence.numberOfBedrooms && numberOfBathrooms == residence.numberOfBathrooms && parkingSpaces == residence.parkingSpaces && availableEquipment.equals(residence.availableEquipment);
    }
    /**

     Returns the hash code value for the Residence object.
     @return The hash code value for the Residence object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);
    }
}
