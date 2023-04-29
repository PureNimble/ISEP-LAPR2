package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistrictTest {

    private City city1 = new City("City 1");
    private City city2 = new City("City 2");
    private City city3 = new City("City 3");

    private List<City> cities = new ArrayList<>(Arrays.asList(city1, city2, city3));

    private District district1 = new District("District 1", cities);
    private District district2 = new District("District 2");
    private District district3 = new District("District 1", cities);

    @Test
    void getDistrict() {
        assertEquals("District 1", district1.getDistrict());
        assertEquals("District 2", district2.getDistrict());
    }

    @Test
    void getCities() {
        assertEquals(cities, district1.getCities());
        assertNull(district2.getCities());
    }

    @Test
    void testToString() {
        assertEquals("District 1", district1.toString());
        assertEquals("District 2", district2.toString());
    }

    @Test
    void testEquals() {
        assertEquals(district1, district3);
        assertNotEquals(district1, district2);
        assertNotEquals(null, district2);
        assertNotEquals(district2, new Object());
    }

    @Test
    void testHashCode() {
        assertEquals(district1.hashCode(), district3.hashCode());
        assertNotEquals(district1.hashCode(), district2.hashCode());
    }

    @Test
    void testClone() {
        District cloneDistrict = district1.clone();
        assertEquals(district1.getDistrict(), cloneDistrict.getDistrict());
        assertNotSame(district1, cloneDistrict);
        assertNotSame(district1.getCities(), cloneDistrict.getCities());
    }
}