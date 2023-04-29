package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**

 The UserRepository class represents a repository for User objects.

 It provides methods to add a user to the repository and retrieve a defensive copy of the list of users.
 */
public class UserRepository {

    private List<User> users = new ArrayList<>();

    /**

     Adds a user to the repository if it is valid.

     @param user The user object to add to the repository.

     @return An Optional object containing the added user if the operation is successful, empty Optional otherwise.
     */
    public Optional<User> add(User user) {

        Optional<User> newUser = Optional.empty();
        boolean operationSuccess = false;

        if (validateUser(user)) {
            newUser = Optional.of(user);
            operationSuccess = users.add(newUser.get());
        }

        if (!operationSuccess) {
            newUser = Optional.empty();
        }

        return newUser;
    }

    /**

     Validates if a user already exists in the repository.
     @param user The user object to validate.
     @return True if the user does not exist in the repository, false otherwise.
     */
    private boolean validateUser(User user) {
        boolean isValid = !users.contains(user);
        return isValid;
    }
    /**

     Returns a defensive (immutable) copy of the list of users in the repository.
     @return The list of users.
     */
    public List<User> getUsers() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return new ArrayList<>(users);
    }
}
