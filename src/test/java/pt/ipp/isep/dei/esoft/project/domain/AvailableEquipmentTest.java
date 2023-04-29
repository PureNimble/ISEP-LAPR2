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

    @Test
    void testEquals() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment3 = new AvailableEquipment("central heating");

        assertTrue(equipment1.equals(equipment2));
        assertFalse(equipment1.equals(equipment3));
    }

    @Test
    void testHashCode() {
        AvailableEquipment equipment1 = new AvailableEquipment("air conditioning");
        AvailableEquipment equipment2 = new AvailableEquipment("air conditioning");

        assertEquals(equipment1.hashCode(), equipment2.hashCode());
    }
}