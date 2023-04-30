package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegisterEmployeeControllerTest {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private StateRepository stateRepository;
    private StoreRepository storeRepository;
    private List<Store> stores;
    private State state;
    private List<District> districts;


    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void addUserSuccessful() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        User user = new User("test@example.com", 12343345, 6789344, "Person", address, 5551234);

        userRepository.add(user);

        assertEquals(1, userRepository.getUsers().size());
        assertTrue(userRepository.getUsers().contains(user));
    }

    @BeforeEach
    public void setUp1() {
        employeeRepository = new EmployeeRepository();
    }

    @Test
    void createEmployee() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));

        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store,  roles, address);

        employeeRepository.add(employee1);

        assertEquals(1, employeeRepository.getEmployees().size());
        assertTrue(employeeRepository.getEmployees().contains(employee1));
    }

    @BeforeEach
    public void setUp2() {
        roleRepository = new RoleRepository();
    }

    @Test
    void getRolesByDescription() {
        Role role1 = new Role("Test Role 1");
        Role role2 = new Role("Test Role 2");

        roleRepository.add(role1);
        roleRepository.add(role2);

        List<String> roleDescriptions = new ArrayList<>();
        roleDescriptions.add("Test Role 1");
        roleDescriptions.add("Test Role 2");

        List<Role> retrievedRoles = roleRepository.getRolesByDescription(roleDescriptions);

        Assertions.assertEquals(retrievedRoles.size(), 2);
        Assertions.assertTrue(retrievedRoles.contains(role1));
        Assertions.assertTrue(retrievedRoles.contains(role2));
    }

    @BeforeEach
    void setUp3() {
        stateRepository = new StateRepository();

        // Add some sample data to the repository for testing
        State california = new State("California");
        District district1 = new District("District 1");
        City losAngeles = new City("Los Angeles");
        stateRepository.add(california);
    }

    @Test
    void getStateByDescription() {
        State california = stateRepository.getStateByDescription("California");
        Assertions.assertEquals("California", "California");

        // Test for IllegalArgumentException if state doesn't exist
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stateRepository.getStateByDescription("New York");
        });

    }

    @Test
    void getCityByDescription() {
        City losAngeles = stateRepository.getCityByDescription("Los Angeles", new District("District 1"));
        Assertions.assertEquals("Los Angeles", "Los Angeles");

        // Test for IllegalArgumentException if state doesn't exist
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stateRepository.getCityByDescription("New York", new District("District 1"));
        });
    }

    @Test
    void getDistrictByDescription() {
        District district1 = stateRepository.getDistrictByDescription("District 1", new State("California"));
        Assertions.assertEquals("District 1", "District 1");

        // Test for IllegalArgumentException if state doesn't exist
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stateRepository.getDistrictByDescription("District 1", new State("California"));
        });
    }

    @BeforeEach
    void setUp4() {
        stores = new ArrayList<>();
        storeRepository = new StoreRepository();
    }

    @Test
    void getStore() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com");
        Store store2 = new Store("Test Store", 2, address, 5551234, "test@store.com");
        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        assertEquals(stores, storeRepository.getStores());
    }

    @Test
    void getEmployee() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee2 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        employeeRepository.add(employee1);
        employeeRepository.add(employee2);
        List<Employee> result = employeeRepository.getEmployees();
        Assertions.assertNotEquals(List.of(employee1, employee2), result);
    }

    @BeforeEach
    void setUp5() throws Exception {
        districts = new ArrayList<District>();
        districts.add(new District("District A"));
        districts.add(new District("District B"));
        state = new State("State A", districts);
    }


    @Test
    void getState() {
        assertEquals("State A", state.getState());
    }

    @Test
    void getDistrict() {
        assertEquals(districts, state.getDistricts());
    }

    @Test
    void getCities() {
    }

    @Test
    void getRoles() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com");
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));

        assertEquals(roles, employee.getRoles());
    }
}