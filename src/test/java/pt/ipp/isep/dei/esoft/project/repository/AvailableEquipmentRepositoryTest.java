package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.AvailableEquipment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AvailableEquipmentRepositoryTest {


    private AvailableEquipmentRepository availableEquipmentRepository;
    private AvailableEquipment equipment1;
    private AvailableEquipment equipment2;

    @BeforeEach
    void setUp() {
        availableEquipmentRepository = new AvailableEquipmentRepository();
        equipment1 = new AvailableEquipment("air conditioning");
        equipment2 = new AvailableEquipment("central heating");
        availableEquipmentRepository.add(equipment1);
        availableEquipmentRepository.add(equipment2);
    }


    @Test
    void getAvailableEquipmentByDescription() {
        AvailableEquipment equipment = availableEquipmentRepository.getAvailableEquipmentByDescription("air conditioning");
        assertNotNull(equipment);
        assertEquals(equipment1, equipment);
    }

    @Test
    void add() {
        AvailableEquipment newEquipment = new AvailableEquipment("new equipment");
        Optional<AvailableEquipment> result = availableEquipmentRepository.add(newEquipment);
        assertTrue(result.isPresent());
        assertEquals(newEquipment, result.get());
        assertTrue(availableEquipmentRepository.getAvailableEquipments().contains(newEquipment));

    }

    @Test
    void getAvailableEquipments() {
        List<AvailableEquipment> equipmentList = availableEquipmentRepository.getAvailableEquipments();
        assertEquals(2, equipmentList.size());
        assertEquals(2, availableEquipmentRepository.getAvailableEquipments().size());

    }
}