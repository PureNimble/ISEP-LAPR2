package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Residence extends Property{
    private int numberOfBedrooms;

    private int numberOfBathrooms;

    private int parkingSpaces;

    private String availableEquipment;

    public Residence(int area, int distanceFromCityCenter, int numberOfBedrooms, int numberOfBathrooms, int parkingSpaces, String availableEquipment) {

        super(area, distanceFromCityCenter);
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.parkingSpaces = parkingSpaces;
        this.availableEquipment = availableEquipment;
    }

    public String toString(){
        return String.format(super.toString()+
                "Number Bedrooms: %s\n" +
                "Number Bathrooms: %s\n" +
                "ParkingSpaces: %s\n" +
                "AvailableEquipment: %s\n",numberOfBedrooms,numberOfBathrooms,parkingSpaces,availableEquipment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Residence residence = (Residence) o;
        return numberOfBedrooms == residence.numberOfBedrooms && numberOfBathrooms == residence.numberOfBathrooms && parkingSpaces == residence.parkingSpaces && availableEquipment.equals(residence.availableEquipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);
    }

}
