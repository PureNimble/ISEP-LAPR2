package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {

    @Test
    void getPrice() {
        Business business = new Business(100.0);
        assertEquals(100.0, business.getPrice());
    }

    @Test
    void testToString() {
        Business business = new Business(100.0);
        assertEquals("100.0", business.toString());
    }

    //for the same object
    @Test
    void testEquals() {
        Business business = new Business(100.0);
        assertEquals(business,business);
    }

    //for equal objects
    @Test
    void testEquals2(){
        Business business1 = new Business(100.0);
        Business business2 = new Business(100.0);

        assertEquals(business1, business2);
    }

    //for different objects

    @Test
    void testEquals3(){
        Business business1 = new Business(100.0);
        Business business2 = new Business(200.0);

        assertNotEquals(business1, business2);
    }

    @Test
    void testHashCode() {
        Business business1 = new Business(100.0);
        Business business2 = new Business(100.0);

        assertEquals(business1.hashCode(), business2.hashCode());
    }
}