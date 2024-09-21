package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvailableEquipmentTest {

    @Test
    void getAvailableEquipment() {
        AvailableEquipment equipment = new AvailableEquipment("air conditioning");
        assertEquals("air conditioning", equipment.getAvailableEquipment());
    }

    @Test
    void testToString() {
        AvailableEquipment equipment = new AvailableEquipment("air conditioning");
        assertEquals("air conditioning", equipment.toString());
    }

    //for the same object
    @Test
    void testEquals() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");

        assertEquals(equipment1, equipment1);
    }

    //for equal objects
    @Test
    void testEquals2() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("air conditioning");
        assertEquals(equipment1, equipment2);
    }

    //for diffrent objects
    @Test
    void testEquals3() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("central heating");

        assertNotEquals(equipment1, equipment2);
    }

    @Test
    void testHashCode() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("air conditioning");

        assertEquals(equipment1.hashCode(), equipment2.hashCode());
    }
}