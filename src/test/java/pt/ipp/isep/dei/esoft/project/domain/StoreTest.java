package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private final Address address = new Address("Test Street", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
    private final Store store = new Store("Test Store", 1, address, 5551234, "test@store.com",0, 1);


    @Test
    void getId() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1 );
        assertEquals(1, store.getId());
    }

    @Test
    void getAddress() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1 );
        assertEquals(address, store.getAddress());
    }

    @Test
    void testToString() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1);
        String expected = "Store: 1 Store1 located at Streett Test, Test City, Test District, Test State, 45672";
        assertEquals(expected, store.toString());
    }

    @Test
    void createEmployee() {
//        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
//        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com");
//        List<Role> roles = new ArrayList<>();
//        roles.add(new Role("Sales"));
//        Optional<Employee> optionalEmployee = store.createEmployee("employee@example.com", "Employee Name", 5554321, roles, store, address, 123456, 789012);
//        Employee employee1 = new Employee("employee@example.com", 123456789,987654321, "Employe Name", 5555678,new Store("Store A", 1, address, 5551234, "storea@example.com"), (List<Role>) new Role("Agent"), new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State")));
//
//        int passportNumber = 123456789;
//        int taxNumber = 987654321;
//        String name = "Employe Name";
//        int phone = 5555678;
//        String email = "employee@example.com";
//        String Role = "Agent";
//
//
//        assertTrue(optionalEmployee.isPresent());
//        Employee employee = optionalEmployee.get();
//        assertEquals(employee, email);
//        assertEquals("Employee Name", name);
//        assertEquals(5554321, phone);
//        assertEquals(store, employee.getStore());
//        assertEquals(address, address);
//        assertEquals(123456, passportNumber);
//        assertEquals(789012, taxNumber);
    }

    @Test
    void testEquals() {

        //for the same object
        assertEquals(store,store);

        //for equal objects
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store2 = new Store("Test Store", 1, address2, 5551234, "test@store.com",0,1);
        assertEquals(store, store2);

        //for different objects
        Store store3 = new Store("Test Store", 2, address, 5551234, "test@store.com",0,1);
        assertNotEquals(store, store3);
    }

    @Test
    void testHashCode() {
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store2 = new Store("Test Store", 1, address2, 5551234, "test@store.com",0,1);
        assertEquals(store.hashCode(), store2.hashCode());

        Store store3 = new Store("Testt Store", 2, address, 5558234, "test@store.com",0,1);
        assertNotEquals(store.hashCode(), store3.hashCode());
    }

    @Test
    void getDesignation() {
        Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com",0,1);
        assertEquals("Store1", store.getDesignation());
    }

    @Test
    void setDesignation() {
        store.setDesignation("New Store");
        assertEquals("New Store", store.getDesignation());
    }

    @Test
    void setId() {
        store.setId(2);
        assertEquals(2, store.getId());
    }

    @Test
    void setAddress() {
        Address newAddress = new Address("New Street", 54321, new District("New District"), new City("New City"), new State("New State"));
        store.setAddress(newAddress);
        assertEquals(newAddress, store.getAddress());
    }

    @Test
    void getEmail() {
        assertEquals("test@store.com", store.getEmail());
    }

    @Test
    void setEmail() {
        store.setEmail("new@store.com");
        assertEquals("new@store.com", store.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals(5551234, store.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        store.setPhoneNumber(5554321);
        assertEquals(5554321, store.getPhoneNumber());
    }

    @Test
    void getListing() {
        assertEquals(0, store.getListing());
    }

    @Test
    void setListing() {
        store.setListing(1);
        assertEquals(1, store.getListing());
    }
}