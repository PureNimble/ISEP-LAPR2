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
        Rent rent1 = new Rent(12);
        Rent rent2 = new Rent(12);
        Rent rent3 = new Rent(24);

        //for the same object
        assertEquals(rent1,rent1);
        //for equal objects
        assertEquals(rent1, rent2);
        //for different objects
        assertNotEquals(rent1, rent3);
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