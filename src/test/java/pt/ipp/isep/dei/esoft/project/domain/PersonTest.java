package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Address address1 = new Address("123 Main St", 12345, new District("Test District"), new City("Test City"), new State("Test State"));
    Address address2 = new Address("456 Other St", 67890, new District("Other District"), new City("Other City"), new State("Other State"));


    @Test
    void testToString() {
        Person person = new Person("test@example.com", 123456, 789012, "Test Person", address1, 836727813);
        assertEquals("Test Person residente em 123 Main St,Test City,Test District,Test State,12345 que possui como documentos de identificação 123456 789012 de email:test@example.com e de numero de telefone 836727813 , ", person.toString());
    }

    @Test
    void testEquals() {
        Person person1 = new Person("test@example.com", 123456, 789012, "Test Person", address1, 123456789);
        Person person2 = new Person("test@example.com", 123456, 789012, "Test Person", address1, 123456789);
        Person person3 = new Person("other@example.com", 123456, 789012, "Test Person", address1, 123456789);
        assertNotEquals(null, person1);
        assertEquals(person1, person2);
        assertNotEquals(person1, person3);
    }

    @Test
    void testHashCode() {
        Person person1 = new Person("test@example.com", 123456, 789012, "Test Person", address1, 123456789);
        Person person2 = new Person("test@example.com", 123456, 789012, "Test Person", address1, 123456789);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    void compare() {
        Person person1 = new Person("test@example.com", 123456, 789012, "Test Person", address1, 123456789);
        assertEquals(0, person1.compare("test@example.com"));
        assertEquals(1, person1.compare("other@example.com"));
    }
}