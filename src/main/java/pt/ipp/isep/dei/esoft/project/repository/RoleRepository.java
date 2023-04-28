package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private final List<Role> roles = new ArrayList<>();

    /**
     * This method returns an exsiting Task Category by its description.
     *
     * @param taskCategoryDescription The description of the task category to be created.
     * @return The task category.
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public Role getTaskCategoryByDescription(String taskCategoryDescription) {
        Role newRole = new Role(taskCategoryDescription);
        Role role = null;
        if (roles.contains(newRole)) {
            role = roles.get(roles.indexOf(newRole));
        }
        if (role == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + taskCategoryDescription + "] does not exist.");
        }
        return role;
    }

    public List<Role> getRolesByDescription(List<String> roleDescription) {
        List<Role> newRoles = new ArrayList<>();

        for (String st: roleDescription) {
            newRoles.add(new Role(st));
        }

        List<Role> role = null;
        for (Role r: newRoles) {
            if (roles.contains(r)) {
                role.add(roles.get(roles.indexOf(r)));
            }
        }

        if (role == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" +  "] does not exist.");
        }
        return role;
    }






    public Optional<Role> add(Role role) {

        Optional<Role> newTaskCategory = Optional.empty();
        boolean operationSuccess = false;

        if (validateRole(role)) {
            newTaskCategory = Optional.of(role.clone());
            operationSuccess = roles.add(newTaskCategory.get());
        }

        if (!operationSuccess) {
            newTaskCategory = Optional.empty();
        }

        return newTaskCategory;
    }

    private boolean validateRole(Role role) {
        boolean isValid = !roles.contains(role);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<Role> getRoles() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(roles);
    }
}
