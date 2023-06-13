package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResidenceTest {
    Photos photos = new Photos("urlll");
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
    Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9);
    Photos photos1 = new Photos("ulll");
    Address address1 = new Address("12 St", 13426, new District("District"), new City("City"), new State("State"));
    Photos photos2 = new Photos("ulvrtbll");
    Address address2 = new Address("12 ihiuech St", 13416, new District("District d"), new City("City fd"), new State("State d"));

    List<Role> roles = new ArrayList<>();
    Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
    Client client = new Client("client@this.app",123456789,123456789,"client",address,1234567890L);


    @Test
    void testToString() {
        int area = 100;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence r = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment,photos, address);

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

        Residence r1 = new Residence(area1, distanceFromCityCenter1, numberOfBedrooms1, numberOfBathrooms1, parkingSpaces1, availableEquipment1,photos,address);
        Residence r2 = new Residence(area1, distanceFromCityCenter1, numberOfBedrooms1, numberOfBathrooms1, parkingSpaces1, availableEquipment1,photos,address);

        int area2 = 100;
        int distanceFromCityCenter2 = 10;
        int numberOfBedrooms2 = 2;
        int numberOfBathrooms2 = 1;
        int parkingSpaces2 = 1;
        AvailableEquipment availableEquipment2 = new AvailableEquipment("air conditioning");

        Residence r3 = new Residence(area2, distanceFromCityCenter2, numberOfBedrooms2, numberOfBathrooms2, parkingSpaces2, availableEquipment2,photos,address);

        //for the same object
        assertEquals(r1, r1);
        //for equal objects
        assertEquals(r1, r2);
        //for different objects
        assertEquals(r1, r3);
    }

    @Test
    void testHashCode() {
        int area = 100;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence r1 = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment,photos,address);
        Residence r2 = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment,photos,address);

        assertEquals(r1.hashCode(), r2.hashCode());
    }
}