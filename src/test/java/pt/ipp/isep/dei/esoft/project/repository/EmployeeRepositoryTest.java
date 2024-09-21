package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        employeeRepository = new EmployeeRepository();
    }

    @Test
    void add() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",0,1);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Optional<Employee> result = employeeRepository.add(employee1);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(employee1, result.get());
        Assertions.assertEquals(List.of(employee1), employeeRepository.getEmployees());

    }

    @Test
    void getEmployees() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com",0,1);
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

    @Test
    void getEmployeeByEmail() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com", 0,1);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee1@example.com", 123456789, 987654321, "Name Employee 1", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee2 = new Employee("employee2@example.com", 987654321, 123456789, "Name Employee 2", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        employeeRepository.add(employee1);
        employeeRepository.add(employee2);
        Employee result = employeeRepository.getEmployeeByEmail("employee1@example.com");
        assertEquals(employee1, result);
    }

    @Test
    void getEmployeeByString() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com", 0,1);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee1@example.com", 123456789, 987654321, "Name Employee 1", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee2 = new Employee("employee2@example.com", 987654321, 123456789, "Name Employee 2", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        employeeRepository.add(employee1);
        employeeRepository.add(employee2);
        Employee result = employeeRepository.getEmployeeByString(employee1.toString());
        assertEquals(employee1, result);
    }

    @Test
    void getEmployeesAlphabeticallySorted() {
        Address address = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store A", 1, address, 5551234, "storea@example.com", 0,1);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        roles.add(new Role("Manager"));
        Employee employee1 = new Employee("employee1@example.com", 123456789, 987654321, "John Doe", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        Employee employee2 = new Employee("employee2@example.com", 987654321, 123456789, "Adam Smith", 5551234, store, roles, new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State")));
        employeeRepository.add(employee1);
        employeeRepository.add(employee2);
        List<Employee> result = employeeRepository.getEmployeesAllphabeticallySorted();
        assertEquals(List.of(employee2, employee1), result);
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
}