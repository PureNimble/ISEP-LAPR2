package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComissionTest {

    @Test
    void getComission() {
        Comission comission = new Comission(500.0);
        assertEquals(500.0, comission.getComission(), 0.0);
    }

    @Test
    void testToString() {
        Comission comission = new Comission(560.0);
        assertEquals("560.0\n", comission.toString());
    }

    //for the same object
    @Test
    void testEquals() {
        Comission comission1 = new Comission(500.0);

        assertEquals(comission1, comission1);
    }

    //for equal objects
    @Test
    void testEquals2() {
        Comission comission1 = new Comission(500.0);
        Comission comission2 = new Comission(500.0);

        assertEquals(comission1, comission2);
    }

    //for different objects
    @Test
    void testEquals3() {
        Comission comission1 = new Comission(500.0);
        Comission comission2 = new Comission(1009.0);

        assertNotEquals(comission1, comission2);
    }

    @Test
    void testHashCode() {
        Comission comission1 = new Comission(590.0);
        Comission comission2 = new Comission(590.0);
        assertEquals(comission1.hashCode(), comission2.hashCode());
    }
}