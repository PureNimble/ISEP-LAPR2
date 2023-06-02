package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The State class represents a state in a country with its name and a list of its districts.
 */
public class State {

    /** The name of the state. */
    private String state;

    /** The list of districts in the state. */
    private  List<District> districts = new ArrayList<>();

    /**
     * Constructs a new State object with the specified state name and district list.
     *
     * @param state     the name of the state
     * @param districts the list of districts in the state
     */
    public State(String state,List<District> districts) {
        this.state = state;
        this.districts = districts;
    }


    /**
     * Constructs a new State object with the specified state name.
     *
     * @param state the name of the state
     */
    public State(String state) {
        this.state = state;
    }

    /**
     * Returns the name of the state.
     *
     * @return the name of the state
     */
    public String getState() {
        return state;
    }

    /**
     * Returns the list of districts in the state.
     *
     * @return the list of districts in the state
     */
    public List<District> getDistricts() {
        return districts;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets districts.
     *
     * @param districts the districts
     */
    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    /**

     Returns a string representation of the state.
     @return a string representation of the state
     */
    public String toString(){
        return String.format(state);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o the reference object with which to compare
     @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return state.equals(state1.state);
    }
    /**

     Returns a hash code value for the state.
     @return a hash code value for the state
     */
    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
    /**

     Returns a cloned State object with the same state name and district list.
     @return a cloned State object with the same state name and district list
     */
    public State clone(){
        return new State(this.state,this.districts);
    }
}
