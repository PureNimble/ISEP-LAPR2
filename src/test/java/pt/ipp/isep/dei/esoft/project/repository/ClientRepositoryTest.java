package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClientRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void add() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        UserRepository userRepository = new UserRepository();
        Client client = new Client("person@exampl.com", 123456, 567890, "Person", address, 8937653);

        Optional<Client> addedUser = userRepository.add(client);

        assertTrue(addedUser.isPresent());
        assertEquals(client, addedUser.get());
        assertEquals(List.of(client), userRepository.getUsers());
    }

    @Test
    public void testAddDuplicateUser() {
        UserRepository userRepository = new UserRepository();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));

        Client client1 = new Client("person@exampl.com", 123456, 567890, "Person", address, 8937653);
        Client client2 = new Client("person@exampl.com", 123456, 567890, "Person", address, 8937653);


        Optional<Client> addedUser1 = userRepository.add(client1);
        Optional<Client> addedUser2 = userRepository.add(client2);

        assertTrue(addedUser1.isPresent());
        assertFalse(addedUser2.isPresent());
        assertEquals(List.of(client1), userRepository.getUsers());
    }

    @Test
    void getUsers() {
        UserRepository userRepository = new UserRepository();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));

        Client client1 = new Client("person@exampl.com", 123456, 567890, "Person", address, 8937634);
        Client client2 = new Client("persona@exampl.com", 1234565, 5678909, "Personaa", address, 89376534);

        userRepository.add(client1);
        userRepository.add(client2);

        List<Client> clients = userRepository.getUsers();

        assertEquals(2, clients.size());
        assertTrue(clients.contains(client1));
        assertTrue(clients.contains(client2));
    }
}