package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testToString() {
        Address address = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals("123 Test St,Test City,Test District,Test State,12345", address.toString());
    }

    //for the same object
    @Test
    void testEquals() {
        Address address1 = new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(address1, address1);
    }

    //for equal objects
    @Test
    void testEquals2() {
        Address address1 = new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address2 = new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(address1, address2);
    }

    //for different objscts
    @Test
    void testEquals3() {
        Address address1 = new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address2 = new Address("456 Other St", 67890, new District("Other District"), new City("Other City"), new State("Other State"));
        assertNotEquals(address1, address2);
    }

    @Test
    void testHashCode() {
        Address address1 = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        Address address2 = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(address1.hashCode(), address2.hashCode());
    }
}
