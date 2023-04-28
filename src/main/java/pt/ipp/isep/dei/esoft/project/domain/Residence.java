package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 Represents a residential property.
 */
public class Residence extends Property{

    /** The number of bedrooms in the residence */
    private int numberOfBedrooms;

    /** The number of bathrooms in the residence */
    private int numberOfBathrooms;

    /** The number of parking spaces in the residence */
    private int parkingSpaces;

    /** The available equipment in the residence */
    private String availableEquipment;

    /**

     Constructs a new Residence object.
     @param area The area of the residence.
     @param distanceFromCityCenter The distance of the residence from the city center.
     @param numberOfBedrooms The number of bedrooms in the residence.
     @param numberOfBathrooms The number of bathrooms in the residence.
     @param parkingSpaces The number of parking spaces in the residence.
     @param availableEquipment The available equipment in the residence.
     */
    public Residence(int area, int distanceFromCityCenter, int numberOfBedrooms, int numberOfBathrooms, int parkingSpaces, String availableEquipment) {
        super(area, distanceFromCityCenter);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.parkingSpaces = parkingSpaces;
        this.availableEquipment = availableEquipment;
    }
    /**

     Returns a string representation of the Residence object.
     @return A string representation of the Residence object.
     */
    public String toString(){
        return String.format(super.toString()+
                "Number Bedrooms: %s\n" +
                "Number Bathrooms: %s\n" +
                "ParkingSpaces: %s\n" +
                "AvailableEquipment: %s\n",numberOfBedrooms,numberOfBathrooms,parkingSpaces,availableEquipment);
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
