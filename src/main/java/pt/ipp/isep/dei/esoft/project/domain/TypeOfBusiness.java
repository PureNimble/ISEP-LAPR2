package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class TypeOfBusiness {
    private String typeOfBusiness;

    public TypeOfBusiness(String typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    public String getTypeOfBusiness() {
        return typeOfBusiness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfBusiness that = (TypeOfBusiness) o;
        return typeOfBusiness.equals(that.typeOfBusiness);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfBusiness);
    }

    public TypeOfBusiness clone() {
        return new TypeOfBusiness(this.typeOfBusiness);
    }
}
