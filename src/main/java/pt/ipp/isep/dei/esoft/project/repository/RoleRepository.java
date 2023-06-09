package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The RoleRepository class provides functionality to manage roles, including creating, retrieving and validating roles.
 */
public class RoleRepository {

    /**
     * Represents a list of roles.
     */
    private final List<Role> roles = new ArrayList<>();

    /**
     * Returns an existing role by its description.
     *
     * @param taskCategoryDescription The description of the role to retrieve.
     * @return The Role object.
     * @throws IllegalArgumentException If the role does not exist.
     */
    public Role getRoleByDescription(String taskCategoryDescription) {
        Role newRole = new Role(taskCategoryDescription);
        Role role = null;
        if (roles.contains(newRole)) {
            role = roles.get(roles.indexOf(newRole));
        }
        if (role == null) {
            throw new IllegalArgumentException(
                    "Role requested for [" + taskCategoryDescription + "] does not exist.");
        }
        return role;
    }

    /**
     * Returns a list of existing roles by their descriptions.
     *
     * @param roleDescription The descriptions of the roles to retrieve.
     * @return The list of Role objects.
     * @throws IllegalArgumentException If any of the roles do not exist.
     */
    public List<Role> getRolesByDescription(List<String> roleDescription) {
        List<Role> newRoles = new ArrayList<>();

        for (String st : roleDescription) {
            newRoles.add(new Role(st));
        }

        List<Role> role = new ArrayList<>();
        for (Role r : newRoles) {
            if (roles.contains(r)) {
                role.add(roles.get(roles.indexOf(r)));
            }
        }

        if (role.isEmpty()) {
            throw new IllegalArgumentException(
                    "Roles requested do not exist.");
        }
        return role;
    }

    /**
     * Adds a new role to the repository.
     *
     * @param role The role to add.
     * @return An Optional object containing the new Role object if the operation was successful, or an empty Optional otherwise.
     */
    public Optional<Role> add(Role role) {

        Optional<Role> newRole = Optional.empty();
        boolean operationSuccess = false;

        if (validateRole(role)) {
            newRole = Optional.of(role.clone());
            operationSuccess = roles.add(newRole.get());
        }

        if (!operationSuccess) {
            newRole = Optional.empty();
        }

        return newRole;
    }

    /**

     Validates that a role is not already present in the repository.
     @param role The role to validate.
     @return true if the role is valid, false otherwise.
     */
    private boolean validateRole(Role role) {
        boolean isValid = !roles.contains(role);
        return isValid;
    }

    /**
     * Returns a defensive copy of the list of roles in the repository.
     *
     * @return The list of Role objects.
     */
    public List<Role> getRoles() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(roles);
    }
}
