package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class AvailableEquipment {
    private String availableEquipment;

    public AvailableEquipment(String availableEquipment) {
        this.availableEquipment = availableEquipment;
    }

    public String getAvailableEquipment() {
        return availableEquipment;
    }

    public String toString(){
        return String.format(availableEquipment);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableEquipment that = (AvailableEquipment) o;
        return availableEquipment.equals(that.availableEquipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(availableEquipment);
    }

}
