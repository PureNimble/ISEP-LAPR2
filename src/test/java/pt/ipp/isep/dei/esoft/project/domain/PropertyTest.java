package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void testToString() {
        Property property = new Property(100, 5);
        String expected = "Area: 100\n" + "DistanceFromCityCenter:5\n";
        assertEquals(expected, property.toString());
    }

    @Test
    void testEquals() {
        Property property1 = new Property(100, 5);
        Property property2 = new Property(100, 5);
        Property property3 = new Property(200, 5);
        Property property4 = new Property(100, 10);

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
        Property property1 = new Property(100, 5);
        Property property2 = new Property(100, 5);
        Property property3 = new Property(200, 5);

        assertEquals(property1.hashCode(), property2.hashCode());
        assertNotEquals(property1.hashCode(), property3.hashCode());
    }

    @Test
    void testClone() {
        Property property1 = new Property(100, 5);
        Property property2 = property1.clone();

        assertNotEquals(property2, property1);
        assertNotSame(property1, property2);
    }
}