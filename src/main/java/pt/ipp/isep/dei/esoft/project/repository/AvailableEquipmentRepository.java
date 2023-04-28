package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AvailableEquipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AvailableEquipmentRepository {
    private List<AvailableEquipment> availableEquipments = new ArrayList<>();

    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription){

        AvailableEquipment newAvailableEquipment = new AvailableEquipment(availableEquipmentDescription);
        AvailableEquipment availableEquipment = null;

        if (availableEquipments.contains(newAvailableEquipment)) {
            availableEquipment = availableEquipments.get(availableEquipments.indexOf(newAvailableEquipment));
        }
        if (availableEquipment == null) {
            throw new IllegalArgumentException(
                    "Type of Business requested for [" + availableEquipmentDescription + "] does not exist.");
        }
        return availableEquipment;
    }

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
    private boolean validateAvailableEquipment(AvailableEquipment availableEquipment) {
        boolean isValid = !availableEquipments.contains(availableEquipment);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<AvailableEquipment> getAvailableEquipments() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(availableEquipments);
    }
}
