package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    void add_ValidUser() {
        // Arrange
        UserRepository repository = new UserRepository();
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

        Client client = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address2, 1234567890);

        // Act
        Optional<Client> addedUser = repository.add(client);

        // Assert
        assertTrue(addedUser.isPresent());
        assertEquals(client, addedUser.get());
        assertEquals(1, repository.getUsers().size());
        assertTrue(repository.getUsers().contains(client));
    }

    @Test
    void getClientEmail_ValidEmail() {
        // Arrange
        UserRepository repository = new UserRepository();
        Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Client client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address, 9876543210L);
        Client client2 = new Client("luna@isep.ipp.pt", 987654321, 123456789, "Luna", address, 1234567890L);
        repository.add(client1);
        repository.add(client2);
        String email = "pedro@isep.ipp.pt";

        // Act
        Client result = repository.getClientEmail(email);

        // Assert
        assertEquals(client1, result);
    }

    @Test
    void getClientEmail_InvalidEmail() {
        // Arrange
        UserRepository repository = new UserRepository();
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

        Client client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address2, 9876543210L);
        Client client2 = new Client("luna@isep.ipp.pt", 987654321, 123456789, "Pedro", address2, 1234567890);
        repository.add(client1);
        repository.add(client2);
        String email = "invalid@example.com";

        // Act
        Client result = repository.getClientEmail(email);

        // Assert
        assertNull(result);
    }

    @Test
    void add_InvalidUser() {
        // Arrange
        UserRepository repository = new UserRepository();
        Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Client existingClient = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address, 9876543210L);
        repository.add(existingClient); // Add an existing client to the repository

        Client invalidClient = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address, 1234567890);

        // Act
        Optional<Client> addedUser = repository.add(invalidClient);

        // Assert
        assertTrue(addedUser.isPresent());
        assertEquals(2, repository.getUsers().size()); // Both clients should be in the repository
        assertTrue(repository.getUsers().contains(existingClient));
        assertTrue(repository.getUsers().contains(invalidClient));
    }

    @Test
    void getUsers() {
        // Arrange
        UserRepository repository = new UserRepository();
        Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Client client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address, 9876543210L);
        Client client2 = new Client("luna@isep.ipp.pt", 987654321, 123456789, "Luna", address, 1234567890L);
        repository.add(client1);
        repository.add(client2);

        // Act
        List<Client> users = repository.getUsers();

        // Assert
        assertNotNull(users);
        assertEquals(2, users.size());
        assertTrue(users.contains(client1));
        assertTrue(users.contains(client2));

        // Modifying the returned list should not affect the repository
        users.remove(client1);
        assertFalse(repository.getUsers().contains(client1));
        assertTrue(repository.getUsers().contains(client2));
    }


//    @Test
//    void createOwnerByFileReading() {
//        // Arrange
//        UserRepository repository = new UserRepository();
//        List<Client> initialUsers = repository.getUsers();
//        assertEquals(0, initialUsers.size()); // Ensure the initial user list is empty
//
//        // Example data for file reading
//        ArrayList<String[]> ownerInformations = new ArrayList<>();
//        ownerInformations.add(new String[]{"John Doe", "123456789", "987654321", "john@example.com", "1234567890"});
//        ownerInformations.add(new String[]{"Jane Smith", "987654321", "123456789", "jane@example.com", "9876543210"});
//
//        // Act
//        repository.createOwnerByFileReading(ownerInformations);
//
//        // Assert
//        List<Client> updatedUsers = repository.getUsers();
//        assertEquals(initialUsers.size() + ownerInformations.size(), updatedUsers.size(), "Unexpected number of users in the repository");
//    }
}
