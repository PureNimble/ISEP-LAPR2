package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Comission;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The ComissionRepository class manages a collection of Comission objects.
 * It provides methods to add new Comission objects to the collection,
 * retrieve a specific Comission object by description, and get an immutable copy of the entire collection.
 */
public class ComissionRepository {

    private final List<Comission> comissions = new ArrayList<>();

    /**
     * This method returns an existing Comission object by its description.
     *
     * @param comissionDescription The description of the Comission object to be retrieved.
     * @return The Comission object.
     * @throws IllegalArgumentException if the Comission object does not exist.
     */
    public Comission getComissionByDescription(Double comissionDescription) {
        Comission newComission = new Comission(comissionDescription);
        Comission comission = null;
        if (comissions.contains(newComission)) {
            comission = comissions.get(comissions.indexOf(newComission));
        }
        if (comission == null) {
            throw new IllegalArgumentException(
                    "Comission requested for [" + comissionDescription + "] does not exist.");
        }
        return comission;
    }

    /**
     * This method adds a new Comission object to the collection.
     *
     * @param comission The Comission object to be added.
     * @return An Optional object containing the newly added Comission object, or an empty Optional object if the operation fails.
     */
    public Optional<Comission> add(Comission comission) {

        Optional<Comission> newComission = Optional.empty();
        boolean operationSuccess = false;

        if (validateComission(comission)) {
            newComission = Optional.of(comission);
            operationSuccess = comissions.add(newComission.get());
        }

        if (!operationSuccess) {
            newComission = Optional.empty();
        }

        return newComission;
    }

    /**

     This method checks if a Comission object is valid to be added to the collection.
     @param comission The Comission object to be validated.
     @return true if the Comission object is valid, false otherwise.
     */
    private boolean validateComission(Comission comission) {
        boolean isValid = !comissions.contains(comission);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of Comission objects.
     *
     * @return The list of Comission objects.
     */
    public List<Comission> getComission() {
//This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(comissions);
    }
}
