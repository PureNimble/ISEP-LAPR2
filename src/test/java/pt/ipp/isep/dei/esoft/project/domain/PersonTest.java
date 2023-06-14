package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Address address1 = new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
    Address address2 = new Address("456 Other St", 67890, new District("Other District"), new City("Other City"), new State("Other State"));


    @Test
    void testToString() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals("Test Person resident in 123 Main St, Test City, Test District, Test State, 12345 that possess as identifying documents the passport, whose number is: 123456789, the tax number, which is: 987654321, the email: test@example.com and the phone number: 1234567890 , ", person.toString());
    }

    @Test
    void testEquals() {
        Person person1 = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        Person person2 = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        Person person3 = new Person("other@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        //for the same object
        assertEquals(person1, person1);
        //for equal objects
        assertEquals(person1, person2);
        //for different objects
        assertNotEquals(person1, person3);
    }

    @Test
    void testHashCode() {
        Person person1 = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        Person person2 = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    void compare() {
        Person person1 = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals(0, person1.compare("test@example.com"));
        assertEquals(1, person1.compare("other@example.com"));
    }

    @Test
    void getEmail() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals("test@example.com", person.getEmail());
    }

    @Test
    void getName() {
        Person person = new Person("test@example.com", 13456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals("Test Person", person.getName());
    }

    @Test
    void setEmail() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        person.setEmail("new@example.com");
        assertEquals("new@example.com", person.getEmail());
    }

    @Test
    void getPassportNumber() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals(123456789, person.getPassportNumber());
    }

    @Test
    void setPassportNumber() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        person.setPassportNumber(123456789);
        assertEquals(123456789, person.getPassportNumber());
    }

    @Test
    void getTaxNumber() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals(987654321, person.getTaxNumber());
    }

    @Test
    void setTaxNumber() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        person.setTaxNumber(987654321);
        assertEquals(987654321, person.getTaxNumber());
    }

    @Test
    void setName() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        person.setName("New Person");
        assertEquals("New Person", person.getName());
    }

    @Test
    void getAddress() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals(address1, person.getAddress());
    }

    @Test
    void setAddress() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        person.setAddress(address2);
        assertEquals(address2, person.getAddress());
    }

    @Test
    void getPhoneNumber() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        assertEquals(1234567890, person.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        Person person = new Person("test@example.com", 123456789, 987654321, "Test Person", address1, 1234567890);
        person.setPhoneNumber(1234567890);
        assertEquals(1234567890, person.getPhoneNumber());
    }
}