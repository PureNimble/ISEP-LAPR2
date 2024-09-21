package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.City;
import pt.ipp.isep.dei.esoft.project.domain.District;
import pt.ipp.isep.dei.esoft.project.domain.State;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StateRepositoryTest {

    private StateRepository stateRepository;

    @BeforeEach
    void setUp() {
        stateRepository = new StateRepository();

        State california = new State("California");
        District district1 = new District("District 1");
        City losAngeles = new City("Los Angeles");
        stateRepository.add(california);

        State florida = new State("Florida");
        District district2 = new District("District 2");
        City miami = new City("Miami");
        stateRepository.add(florida);
    }

    @Test
    void getStateByDescription() {
        State california = stateRepository.getStateByDescription("California");
        Assertions.assertEquals("California", "California");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stateRepository.getStateByDescription("New York");
        });
    }

    @Test
    void getCityByDescription() {

    }

    @Test
    void add() {
        State newYork = new State("New York");
        District district3 = new District("District 3");
        City newYorkCity = new City("New York City");


        Optional<State> addedState = stateRepository.add(newYork);
        Assertions.assertTrue(addedState.isPresent());
        Assertions.assertEquals("New York", "New York");

        List<State> states = stateRepository.getStates();
        Assertions.assertEquals(3, states.size());
        Assertions.assertTrue(states.contains(newYork));
    }

    @Test
    void getStates() {
        List<State> states = stateRepository.getStates();
        Assertions.assertEquals(2, states.size());

        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            states.add(new State("New State"));
        });
    }
}