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
        AvailableEquipment equipment2 = new AvailableEquipment("centra heating");

        house1 = new House(120, 10, 3, 2, 2, equipment1, "No", "Yes", "south",photos);
        house2 = new House(120, 10, 3, 2, 2, equipment1, "No", "Yes", "south",photos);
        house3 = new House(100, 5, 2, 1, 1, equipment2, "Yes", "No", "west");
    }

    @Test
    void testToString() {
        String expected = "Area: 120\n" +
                "DistanceFromCityCenter:10\n" +
                "Number Bedrooms: 3\n" +
                "Number Bathrooms: 2\n" +
                "ParkingSpaces: 2\n" +
                "AvailableEquipment: air conditioning\n" +
                "Basement: No\n" +
                "Inhabitable Loft: Yes\n" +
                "Sun Exposure: south\n";
        String actual = house1.toString();
        assertEquals(expected, actual);
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
}