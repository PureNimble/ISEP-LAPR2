package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {

    @Test
    void getCity() {
        City city = new City("Los Angeles");
        assertEquals("Los Angeles", city.getCity());
    }

    @Test
    void testToString() {
        City city = new City("Los Angeles");
        assertEquals("Los Angeles", city.toString());
    }

    //for the same object
    @Test
    void testEquals() {
        City city1 = new City("Los Angeles");

        assertEquals(city1, city1);
    }

    //for equal objects
    @Test
    void testEquals2() {
        City city1 = new City("Los Angeles");
        City city2 = new City("Los Angeles");


        assertEquals(city1, city2);
    }

    //for different objects
    @Test
    void testEquals3() {
        City city1 = new City("Los Angeles");
        City city2 = new City("Other City");

        assertNotEquals(city1, city2);
    }

    @Test
    void testHashCode() {
        City city = new City("Los Angeles");
        City city1 = new City("Los Angeles");
        City city2 = new City("Other City");

        assertEquals(city.hashCode(), city1.hashCode());
        assertNotEquals(city.hashCode(), city2.hashCode());
    }

    @Test
    void testClone() {
        City city = new City("Los Angeles");
        City clone = city.clone();
        assertNotSame(city, clone);
        assertEquals(city, clone);
    }
}