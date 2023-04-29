package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testToString() {
        Address address = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals("123 Test St,Test City,Test District,Test State,12345", address.toString());
    }

    @Test
    void testEquals() {
        Address address1 = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address2 = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address3 = new Address("456 Other Test St", 67890, new District("Other Test District"), new City("Other Test City"), new State("Other Test State"));
        assertTrue(address1.equals(address2));
        assertFalse(address1.equals(address3));
    }

    @Test
    void testHashCode() {
        Address address1 = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address2 = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(address1.hashCode(), address2.hashCode());
    }
}
