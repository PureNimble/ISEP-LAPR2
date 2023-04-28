package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Comission;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComissionRepository {

    private final List<Comission> comissions = new ArrayList<>();

    public Comission getComissionByDescription(Double comissionDescription) {
        Comission newComission = new Comission(comissionDescription);
        Comission comission = null;
        if (comissions.contains(newComission)) {
            comission = comissions.get(comissions.indexOf(newComission));
        }
        if (comission == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + comissionDescription + "] does not exist.");
        }
        return comission;
    }



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

    private boolean validateComission(Comission comission) {
        boolean isValid = !comissions.contains(comission);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Comission> getComission() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(comissions);
    }

}
