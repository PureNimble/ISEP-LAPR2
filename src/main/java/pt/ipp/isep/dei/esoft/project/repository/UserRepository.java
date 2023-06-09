package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The UserRepository class represents a repository for User objects.
 * It provides methods to add a user to the repository and retrieve a defensive copy of the list of users.
 */
public class UserRepository {
    /**
     * Represents a list of clients.
     */
    private List<Client> clients = new ArrayList<>();

    /**
     * Adds a user to the repository if it is valid.
     *
     * @param client The user object to add to the repository.
     * @return An Optional object containing the added user if the operation is successful, empty Optional otherwise.
     */
    public Optional<Client> add(Client client) {

        Optional<Client> newUser = Optional.empty();
        boolean operationSuccess = false;

        if (validateUser(client)) {
            newUser = Optional.of(client);
            operationSuccess = clients.add(newUser.get());
        }

        if (!operationSuccess) {
            newUser = Optional.empty();
        }

        return newUser;
    }

    /**

     Validates if a user already exists in the repository.
     @param client The user object to validate.
     @return True if the user does not exist in the repository, false otherwise.
     */
    private boolean validateUser(Client client) {
        boolean isValid = !clients.contains(client);
        return isValid;
    }

    /**
     * Returns a defensive (immutable) copy of the list of users in the repository.
     *
     * @return The list of users.
     */
    public List<Client> getUsers() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return clients;
    }

    /**
     * Get client email client.
     *
     * @param email the email
     * @return the client
     */
    public Client getClientEmail(String email){
        for (Client client: clients) {
            if (client.getClientEmail().equals(email)){
                return client;
            }
        }
        return null;
    }

    /**
     * Create owner by file reading.
     *
     * @param arrayListOwnerInformations the array list owner informations
     */
    public void createOwnerByFileReading(ArrayList<String[]> arrayListOwnerInformations) {

        int aux = 0;
        String name;
        int passportNumber;
        long phoneNumber;
        int taxNumber;
        String email;

        for (String[] ownerInformations : arrayListOwnerInformations) {
            if (aux > 0){
                name = ownerInformations[1];
                passportNumber = Integer.parseInt(ownerInformations[2]);
                taxNumber = Integer.parseInt(ownerInformations[3].replaceAll("-",""));
                email = ownerInformations[4];
                phoneNumber = Long.parseLong(ownerInformations[5].replaceAll("-",""));


                Client client = new Client(email,passportNumber,taxNumber,name,phoneNumber);

                if (!clients.contains(client))
                    clients.add(client);
            }else {
                aux = 1;
            }
        }
    }
}
