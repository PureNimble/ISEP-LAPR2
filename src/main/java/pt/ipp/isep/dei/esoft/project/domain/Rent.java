package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Rent {
    private int durationContract;

    public Rent(int durationContract) {
        this.durationContract = durationContract;
    }

    public int getDurationContract() {
        return durationContract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return durationContract == rent.durationContract;
    }

    @Override
    public int hashCode() {
        return Objects.hash(durationContract);
    }
}
