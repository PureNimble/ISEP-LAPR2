package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testToString() {
        Role role = new Role("Agent");
        assertEquals("Agent", role.toString());
    }

    @Test
    void testEquals() {
        Role role1 = new Role("Agent");
        Role role2 = new Role("Agent");
        Role role3 = new Role("Employee");

        assertTrue(role1.equals(role2));
        assertFalse(role1.equals(role3));
    }

    @Test
    void testHashCode() {
        Role role1 = new Role("Agent");
        Role role2 = new Role("Agent");
        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    void getDescription() {
        Role role = new Role("Agent");
        assertEquals("Agent", role.getDescription());
    }

    @Test
    void testClone() {
        Role role1 = new Role("Agent");
        Role role2 = role1.clone();

        assertEquals(role1, role2);
        assertNotSame(role1, role2);
    }
}