package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTypeTest {

    @Test
    void getDesignation() {
        PropertyType type = new PropertyType("Apartment");
        assertEquals("Apartment", type.getDesignation());
    }

    @Test
    void testToString() {
        PropertyType type = new PropertyType("House");
        assertEquals("House", type.toString());
    }

    @Test
    void testEquals() {
        PropertyType type1 = new PropertyType("Apartment");
        PropertyType type2 = new PropertyType("Apartment");
        PropertyType type3 = new PropertyType("House");

        //for the same object
        assertEquals(type1,type1);
        //for equal objects
        assertEquals(type1, type2);
        //for different objects
        assertNotEquals(type1, type3);
    }

    @Test
    void testHashCode() {
        PropertyType type1 = new PropertyType("Apartment");
        PropertyType type2 = new PropertyType("Apartment");
        PropertyType type3 = new PropertyType("House");
        assertTrue(type1.hashCode() == type2.hashCode());
        assertFalse(type1.hashCode() == type3.hashCode());
    }

    @Test
    void testClone() {
        PropertyType type1 = new PropertyType("Apartment");
        PropertyType type2 = type1.clone();
        assertEquals(type1.getDesignation(), type2.getDesignation());
        assertNotSame(type1, type2);
    }
}