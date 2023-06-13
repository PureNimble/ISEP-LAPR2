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
        String expected = "Area: 100\n" + "DistanceFromCityCenter:5\n";
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
}