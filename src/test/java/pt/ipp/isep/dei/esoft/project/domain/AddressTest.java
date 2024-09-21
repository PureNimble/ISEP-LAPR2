package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testToString() {
        Address address = new Address("123 Test St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals("123 Test St, Test City, Test District, Test State, 12345", address.toString());
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
    @Test
    void getStreet() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals("123 Main St", address.getStreet());
    }

    @Test
    void setStreet() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        address.setStreet("456 New St");
        assertEquals("456 New St", address.getStreet());
    }

    @Test
    void getZipcode() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(13456, address.getZipcode());
    }

    @Test
    void setZipcode() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        address.setZipcode(54321);
        assertEquals(54321, address.getZipcode());
    }

    @Test
    void getDistrict() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(new District("Test District"), address.getDistrict());
    }

    @Test
    void setDistrict() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        District newDistrict = new District("New District");
        address.setDistrict(newDistrict);
        assertEquals(newDistrict, address.getDistrict());
    }

    @Test
    void getCity() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(new City("Test City"), address.getCity());
    }

    @Test
    void setCity() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        City newCity = new City("New City");
        address.setCity(newCity);
        assertEquals(newCity, address.getCity());
    }

    @Test
    void getState() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        assertEquals(new State("Test State"), address.getState());
    }

    @Test
    void setState() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        State newState = new State("New State");
        address.setState(newState);
        assertEquals(newState, address.getState());
    }
}
