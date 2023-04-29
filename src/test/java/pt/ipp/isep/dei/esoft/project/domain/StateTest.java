package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    private State state;
    private List<District> districts;

    @BeforeEach
    void setUp() throws Exception {
        districts = new ArrayList<District>();
        districts.add(new District("District A"));
        districts.add(new District("District B"));
        state = new State("State A", districts);
    }

    //Constructor with State and District

    @Test
    void testConstructorWithStateNameAndDistrictList() {
        assertEquals("State A", state.getState());
        assertEquals(districts, state.getDistricts());
    }

    //Constructor with State

    @Test
    void testConstructorWithStateName() {
        State state = new State("State A");
        assertEquals("State A", state.getState());
        assertNull(state.getDistricts());
    }


    @Test
    void getState() {
        assertEquals("State A", state.getState());
    }

    @Test
    void getDistricts() {
        assertEquals(districts, state.getDistricts());
    }

    @Test
    void testToString() {
        assertEquals("State A", state.toString());
    }

    @Test
    void testEquals() {
        State state1 = new State("State A", districts);
        State state2 = new State("State A", districts);
        State state3 = new State("State B", districts);

        //for the same object
        assertEquals(state1, state1);
        //for equal objects
        assertEquals(state1, state2);
        //for different objects
        assertNotEquals(state1, state3);
    }

    @Test
    void testHashCode() {
        State state1 = new State("State A", districts);
        State state2 = new State("State B", districts);
        State state3 = new State("State A", new ArrayList<District>());
        State state4 = new State("State A", districts);
        assertEquals(state.hashCode(), state1.hashCode());
        assertNotEquals(state.hashCode(), state2.hashCode());
        assertEquals(state.hashCode(), state3.hashCode());
        assertEquals(state.hashCode(), state4.hashCode());
    }

    @Test
    void testClone() {
        State clonedState = state.clone();
        assertEquals(state.getState(), clonedState.getState());
        assertEquals(state.getDistricts(), clonedState.getDistricts());
        assertSame(state.getDistricts(), clonedState.getDistricts());
    }
}