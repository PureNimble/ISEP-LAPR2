package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private List<User> users = new ArrayList<>();

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

    private boolean validateUser(User user) {
        boolean isValid = !users.contains(user);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<User> getUsers() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return users;
    }
}
