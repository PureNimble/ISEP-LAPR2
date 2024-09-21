package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Comission;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ComissionRepositoryTest {

    private ComissionRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new ComissionRepository();
    }

    @Test
    void getComissionByDescription() {
        Comission comission1 = new Comission(0.05);
        Comission comission2 = new Comission(0.1);
        repository.add(comission1);
        repository.add(comission2);
        assertEquals(comission1, repository.getComissionByDescription(0.05));
        assertEquals(comission2, repository.getComissionByDescription(0.1));
    }

    @Test
    void add() {
        Comission comission = new Comission(0.05);
        Optional<Comission> result = repository.add(comission);
        assertTrue(result.isPresent());
        assertEquals(comission, result.get());
        assertEquals(List.of(comission), repository.getComission());
    }

    @Test
    void getComission() {
        Comission comission = new Comission(0.05);
        repository.add(comission);
        assertThrows(IllegalArgumentException.class, () -> repository.getComissionByDescription(0.1));
    }
}