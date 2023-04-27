package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

public class State {

    private String state;
    private List<District> districts;

    public State(String state,List<District> districts) {
        this.state = state;
        this.districts = districts;
    }

    public State(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public String toString(){
        return String.format(state);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return state.equals(state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    public State clone(){
        return new State(this.state,this.districts);
    }
}
