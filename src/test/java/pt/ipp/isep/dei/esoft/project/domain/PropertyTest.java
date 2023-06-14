package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
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
        Property property = new Property(100, 5,photos,address);
        String expected = "Area: 100\n" +
        "Distance From The City Center: 5\n" +
        "Photos: [urlll]\n" +
        "Address: 123 Main St, Test City, Test District, Test State, 13456\n";

        assertEquals(expected, property.toString());
    }

    @Test
    void testEquals() {
        Property property1 = new Property(100, 5, photos, address);
        Property property2 = new Property(100, 5,photos,address);
        Property property3 = new Property(200, 5, photos1, address1);
        Property property4 = new Property(100, 10,photos2,address2);

        //for the same object
        assertEquals(property1,property1);
        //for equal objects
        assertEquals(property1, property2);
        //for different objects
        assertNotEquals(property1, property3);
        assertNotEquals(property1, property4);
    }

    @Test
    void testHashCode() {
        Property property1 = new Property(100, 5,photos,address);
        Property property2 = new Property(100, 5,photos,address);
        Property property3 = new Property(200, 5,photos2,address2);

        assertEquals(property1.hashCode(), property2.hashCode());
        assertNotEquals(property1.hashCode(), property3.hashCode());
    }

    @Test
    void testClone() {
        Property property1 = new Property(100, 5,photos,address2);
        Property property2 = property1.clone();

        assertNotEquals(property2, property1);
        assertNotSame(property1, property2);
    }

    @Test
    void getNumberOfBedrooms() {
        int area = 100;
        int area2 = 101;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence residence = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, address);

        residence.setNumberOfBedrooms(3);
        Property property = new Property(residence);

        int expected = 3;
        assertEquals(expected, property.getNumberOfBedrooms());
    }

    @Test
    void getResidence() {
        int area = 100;
        int area2 = 101;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence residence1 = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, address);
        Property property = new Property(residence1);

        assertEquals(residence1, property.getResidence());
    }

    @Test
    void setResidence() {
        int area = 100;
        int area2 = 101;
        int distanceFromCityCenter = 10;
        int numberOfBedrooms = 2;
        int numberOfBathrooms = 1;
        int parkingSpaces = 1;
        AvailableEquipment availableEquipment = new AvailableEquipment("air conditioning");

        Residence residence1 = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, address);
        Residence residence2 = new Residence(area2, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, address);
        Property property = new Property(residence1);

        property.setResidence(residence2);

        assertEquals(residence2, property.getResidence());
    }

    @Test
    void setAddress() {
        Property property = new Property();
        Address newAddress = new Address("456 Oak St", 13426, new District("Test District"), new City("Test City"), new State("Test State"));

        property.setAddress(newAddress);

        assertEquals(newAddress, property.getAddress());
    }

    @Test
    void getDistanceFromCityCenter() {
        Property property = new Property(100, 5, photos, address);

        int expected = 5;
        assertEquals(expected, property.getDistanceFromCityCenter());
    }

    @Test
    void getArea() {
        Property property = new Property(100, 5, photos, address);

        int expected = 100;
        assertEquals(expected, property.getArea());
    }

    @Test
    void getPhotos() {
        Property property = new Property(100, 5, photos, address);

        assertEquals(photos, property.getPhotos());
    }

    @Test
    void setPhotos() {
        Property property = new Property(100, 5, photos, address);
        Photos newPhotos = new Photos("newurl");

        property.setPhotos(newPhotos);

        assertEquals(newPhotos, property.getPhotos());
    }

    @Test
    void setDistanceFromCityCenter() {
        Property property = new Property(100, 5, photos, address);
        int newDistance = 10;

        property.setDistanceFromCityCenter(newDistance);

        assertEquals(newDistance, property.getDistanceFromCityCenter());
    }

    @Test
    void setArea() {
        Property property = new Property(100, 5, photos, address);
        int newArea = 200;

        property.setArea(newArea);

        assertEquals(newArea, property.getArea());
    }

    @Test
    void getAddress() {
        Property property = new Property(100, 5, photos, address);

        assertEquals(address, property.getAddress());
    }
}
