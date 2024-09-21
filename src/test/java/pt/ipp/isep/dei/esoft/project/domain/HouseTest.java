package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    private House house1;
    private House house2;
    private House house3;

    Photos photos = new Photos("url");
    Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));


    @BeforeEach
    void setUp() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("central heating");

        house1 = new House(120, 10, 3, 2, 2, equipment1, "No", "Yes", "south",photos, address);
        house2 = new House(120, 10, 3, 2, 2, equipment1, "No", "Yes", "south",photos, address);
        house3 = new House(100, 5, 2, 1, 1, equipment2, "Yes", "No", "west",photos, address);
    }

    @Test
    void testToString() {
        String basement = "No";
        String inhabitableLoft = "No";
        String sunExposure = "East";
        String expected = String.format(super.toString() +
                "Basement: %s\n" +
                "Inhabitable Loft: %s\n" +
                "Sun Exposure: %s\n", basement, inhabitableLoft, sunExposure);
    }

    @Test
    void testEquals() {
        //for the same object
        assertEquals(house1,house1);
        //for equal objects
        assertEquals(house1, house2);
        //for different objects
        assertNotEquals(house1, house3);
    }

    @Test
    void testHashCode() {
        assertEquals(house1.hashCode(), house2.hashCode());
        assertNotEquals(house1.hashCode(), house3.hashCode());
    }

    @Test
    void getBasement() {
        String basement = house1.getBasement();
        assertEquals("No", basement);
        // Add more assertions as needed
    }

    @Test
    void getInhabitableLoft() {
        String inhabitableLoft = house1.getInhabitableLoft();
        assertEquals("Yes", inhabitableLoft);
        // Add more assertions as needed
    }

    @Test
    void getSunExposure() {
        String sunExposure = house1.getSunExposure();
        assertEquals("south", sunExposure);
        // Add more assertions as needed
    }

    @Test
    void setBasement() {
        house1.setBasement("Yes");
        assertEquals("Yes", house1.getBasement());
    }

    @Test
    void setInhabitableLoft() {
        house1.setInhabitableLoft("No");
        assertEquals("No", house1.getInhabitableLoft());
    }

    @Test
    void setSunExposure() {
        house1.setSunExposure("east");
        assertEquals("east", house1.getSunExposure());
    }

}