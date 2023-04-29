package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentTest {

    @Test
    void getDurationContract() {
        Rent rent = new Rent(12);
        assertEquals(12, rent.getDurationContract());
    }

    @Test
    void testEquals() {
        Rent rent1 = new Rent(36);
        Rent rent2 = new Rent(36);
        Rent rent3 = new Rent(124);

        assertTrue(rent1.equals(rent2));
        assertTrue(rent2.equals(rent1));
        assertFalse(rent1.equals(rent3));
        assertFalse(rent3.equals(rent1));
    }

    @Test
    void testHashCode() {
        Rent rent1 = new Rent(64);
        Rent rent2 = new Rent(64);
        Rent rent3 = new Rent(78);

        assertEquals(rent1.hashCode(), rent2.hashCode());
        assertNotEquals(rent1.hashCode(), rent3.hashCode());
    }
}