package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TypeOfBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeOfBusinessRepository {
    private List<TypeOfBusiness> typeOfBusinesses = new ArrayList<>();

    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription){

        TypeOfBusiness newTypeOfBusiness = new TypeOfBusiness(typeOfBusinessDescription);
        TypeOfBusiness typeOfBusiness = null;
        if (typeOfBusinesses.contains(newTypeOfBusiness)) {
            typeOfBusiness = typeOfBusinesses.get(typeOfBusinesses.indexOf(newTypeOfBusiness));
        }
        if (typeOfBusiness == null) {
            throw new IllegalArgumentException(
                    "Type of Business requested for [" + typeOfBusinessDescription + "] does not exist.");
        }
        return typeOfBusiness;
    }

    public Optional<TypeOfBusiness> add(TypeOfBusiness typeOfBusiness) {

        Optional<TypeOfBusiness> newTypeOfBusiness = Optional.empty();
        boolean operationSuccess = false;

        if (validateTypeOfBusiness(typeOfBusiness)) {
            newTypeOfBusiness = Optional.of(typeOfBusiness.clone());
            operationSuccess = typeOfBusinesses.add(newTypeOfBusiness.get());
        }

        if (!operationSuccess) {
            newTypeOfBusiness = Optional.empty();
        }

        return newTypeOfBusiness;
    }
    private boolean validateTypeOfBusiness(TypeOfBusiness typeOfBusiness) {
        boolean isValid = !typeOfBusinesses.contains(typeOfBusiness);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<TypeOfBusiness> getTypeOfBusinesses() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(typeOfBusinesses);
    }
}
