package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AvailableEquipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The AvailableEquipmentRepository class represents a repository of available equipment.
 */
public class AvailableEquipmentRepository {

    /**

     The list of available equipment objects.
     */
    private List<AvailableEquipment> availableEquipments = new ArrayList<>();

    /**
     * Returns an AvailableEquipment object that matches the specified description.
     *
     * @param availableEquipmentDescription The description of the available equipment to search for.
     * @return The AvailableEquipment object that matches the description.
     * @throws IllegalArgumentException If the type of business requested for the given description does not exist.
     */
    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) throws IllegalArgumentException {

        AvailableEquipment newAvailableEquipment = new AvailableEquipment(availableEquipmentDescription);
        AvailableEquipment availableEquipment = null;

        if (availableEquipments.contains(newAvailableEquipment)) {
            availableEquipment = availableEquipments.get(availableEquipments.indexOf(newAvailableEquipment));
        }
        if (availableEquipment == null) {
            throw new IllegalArgumentException(
                    "Available Equipment requested for [" + availableEquipmentDescription + "] does not exist.");
        }
        return availableEquipment;
    }

    /**
     * Adds an AvailableEquipment object to the repository.
     *
     * @param availableEquipment The AvailableEquipment object to add to the repository.
     * @return An optional AvailableEquipment object representing the newly added equipment.
     */
    public Optional<AvailableEquipment> add(AvailableEquipment availableEquipment) {

        Optional<AvailableEquipment> newAvailableEquipment = Optional.empty();
        boolean operationSuccess = false;

        if (validateAvailableEquipment(availableEquipment)) {
            newAvailableEquipment = Optional.of(availableEquipment);
            operationSuccess = availableEquipments.add(newAvailableEquipment.get());
        }

        if (!operationSuccess) {
            newAvailableEquipment = Optional.empty();
        }

        return newAvailableEquipment;
    }

    /**

     Validates an AvailableEquipment object to ensure it is not already present in the repository.
     @param availableEquipment The AvailableEquipment object to validate.
     @return true if the AvailableEquipment object is not already present in the repository, false otherwise.
     */
    private boolean validateAvailableEquipment(AvailableEquipment availableEquipment) {
        boolean isValid = !availableEquipments.contains(availableEquipment);
        return isValid;
    }

    /**
     * Returns an immutable copy of the list of available equipment objects.
     *
     * @return An immutable copy of the list of available equipment objects.
     */
    public List<AvailableEquipment> getAvailableEquipments() {
//This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(availableEquipments);
    }
}
