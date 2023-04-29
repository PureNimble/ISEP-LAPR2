package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResidenceTest {

    @Test
    void testToString() {
        int area = 100;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence r = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);

        String expected = "Area: 100\n" +
                "DistanceFromCityCenter:10\n" +
                "Number Bedrooms: 2\n" +
                "Number Bathrooms: 1\n" +
                "ParkingSpaces: 1\n" +
                "AvailableEquipment: air conditioning\n";

        assertEquals(expected, r.toString());
    }

    @Test
    void testEquals() {
        int area1 = 100;
        int distanceFromCityCenter1 = 10;
        int numberOfBedrooms1 = 2;
        int numberOfBathrooms1 = 1;
        int parkingSpaces1 = 1;
        AvailableEquipment availableEquipment1 = new AvailableEquipment("air conditioning");

        Residence r1 = new Residence(area1, distanceFromCityCenter1, numberOfBedrooms1, numberOfBathrooms1, parkingSpaces1, availableEquipment1);

        int area2 = 100;
        int distanceFromCityCenter2 = 10;
        int numberOfBedrooms2 = 2;
        int numberOfBathrooms2 = 1;
        int parkingSpaces2 = 1;
        AvailableEquipment availableEquipment2 = new AvailableEquipment("air conditioning");

        Residence r2 = new Residence(area2, distanceFromCityCenter2, numberOfBedrooms2, numberOfBathrooms2, parkingSpaces2, availableEquipment2);

        assertEquals(r1, r2);

    }

    @Test
    void testHashCode() {
        int area = 100;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence r1 = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);
        Residence r2 = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);

        assertEquals(r1.hashCode(), r2.hashCode());
    }
}