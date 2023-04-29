package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.TypeOfBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 The TypeOfBusinessRepository class represents a repository for TypeOfBusiness objects.

 It provides methods to get a type of business by its description, add a type of business to the repository and retrieve a defensive copy of the list of types of businesses.
 */
public class TypeOfBusinessRepository {

    private List<TypeOfBusiness> typeOfBusinesses = new ArrayList<>();

    /**

     Gets a TypeOfBusiness object by its description.

     @param typeOfBusinessDescription The description of the type of business to get.

     @return The TypeOfBusiness object with the specified description.

     @throws IllegalArgumentException if the type of business does not exist in the repository.
     */
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

    /**

     Adds a type of business to the repository if it is valid.

     @param typeOfBusiness The TypeOfBusiness object to add to the repository.

     @return An Optional object containing the added type of business if the operation is successful, empty Optional otherwise.
     */
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

    /**

     Validates if a type of business already exists in the repository.
     @param typeOfBusiness The TypeOfBusiness object to validate.
     @return True if the type of business does not exist in the repository, false otherwise.
     */
    private boolean validateTypeOfBusiness(TypeOfBusiness typeOfBusiness) {
        boolean isValid = !typeOfBusinesses.contains(typeOfBusiness);
        return isValid;
    }
    /**

     Returns a defensive (immutable) copy of the list of types of businesses in the repository.
     @return The list of types of businesses.
     */
    public List<TypeOfBusiness> getTypeOfBusinesses() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(typeOfBusinesses);
    }
}
