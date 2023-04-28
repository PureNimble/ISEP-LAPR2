package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Comission {
    private  double comission;

    public Comission(double comission) {
        this.comission = comission;
    }

    public String toString(){
        return String.format("%s\n",comission);
    }

    public double getComission() {
        return comission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comission comission1 = (Comission) o;
        return Double.compare(comission1.comission, comission) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comission);
    }
}
