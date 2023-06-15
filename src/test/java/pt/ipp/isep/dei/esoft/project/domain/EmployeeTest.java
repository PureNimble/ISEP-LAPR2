package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void getStore() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9,1 );

        List<Role> roles = new ArrayList<>();
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));

        assertEquals(store, employee.getStore());
    }

    @Test
    void getRoles() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9,1);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));

        assertEquals(roles, employee.getRoles());
    }

    @Test
    void testToString() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9,1 );
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));

        String expected = "Name Employee resident in 123 Main St, Test City, Test District, Test State, 12345 that possess as identifying documents the passport, whose number is: 123456789, the tax number, which is: 987654321, the email: employee@example.com and the phone number: 5551234 , " +
                "\nEmployee of Store: 1 Store A located at 123 Main St, Test City, Test District, Test State, 13456, who performs the role of [Agent, Manager]";

        assertEquals(expected, employee.toString());
    }

    @Test
    void testEquals() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9,1 );
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee2 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee3 = new Employee("OtherEmployee@example.com", 123454789, 987454321, "Other Employee", 5531234, store, roles, new Address("123 Test St", 12365, new District("Other District"), new City("Other City"), new State("Other State")));

        //for the same object
        assertEquals(employee1, employee1);
        //for equal objects
        assertEquals(employee1, employee2);
        //for different objects
        assertNotEquals(employee1, employee3);
    }

    @Test
    void testHashCode() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",9,1 );
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee2 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee3 = new Employee("OtherEmployee@example.com", 123454789, 987454321, "Other Employee", 5531234, store, roles, new Address("123 Test St", 12365, new District("Other District"), new City("Other City"), new State("Other State")));

        assertEquals(employee1.hashCode(), employee2.hashCode());
        assertNotEquals(employee1.hashCode(), employee3.hashCode());
    }

    @Test
    void setStore() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store A", 1, address, 5551234, "storea@example.com", 9,1);
        Store store2 = new Store("Store B", 2, address, 5554321, "storeb@example.com", 5,1);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store1, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));

        // Before setting the new store
        assertEquals(store1, employee.getStore());

        // Set the new store
        employee.setStore(store2);

        // After setting the new store
        assertEquals(store2, employee.getStore());
    }

    @Test
    void setRoles() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com", 9,1 );
        List<Role> roles1 = new ArrayList<>();
        roles1.add(new Role("Agent"));
        roles1.add(new Role("Manager"));
        List<Role> roles2 = new ArrayList<>();
        roles2.add(new Role("Agent"));
        roles2.add(new Role("Supervisor"));
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles1, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));

        // Before setting the new roles
        assertEquals(roles1, employee.getRoles());

        // Set the new roles
        employee.setRoles(roles2);

        // After setting the new roles
        assertEquals(roles2, employee.getRoles());
    }
}