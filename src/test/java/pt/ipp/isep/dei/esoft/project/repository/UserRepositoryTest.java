package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void add() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        UserRepository userRepository = new UserRepository();
        User user = new User("person@exampl.com", 123456, 567890, "Person", address, 8937653);

        Optional<User> addedUser = userRepository.add(user);

        assertTrue(addedUser.isPresent());
        assertEquals(user, addedUser.get());
        assertEquals(List.of(user), userRepository.getUsers());
    }

    @Test
    public void testAddDuplicateUser() {
        UserRepository userRepository = new UserRepository();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));

        User user1 = new User("person@exampl.com", 123456, 567890, "Person", address, 8937653);
        User user2 = new User("person@exampl.com", 123456, 567890, "Person", address, 8937653);


        Optional<User> addedUser1 = userRepository.add(user1);
        Optional<User> addedUser2 = userRepository.add(user2);

        assertTrue(addedUser1.isPresent());
        assertFalse(addedUser2.isPresent());
        assertEquals(List.of(user1), userRepository.getUsers());
    }

    @Test
    void getUsers() {
        UserRepository userRepository = new UserRepository();
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));

        User user1 = new User("person@exampl.com", 123456, 567890, "Person", address, 8937634);
        User user2 = new User("persona@exampl.com", 1234565, 5678909, "Personaa", address, 89376534);

        userRepository.add(user1);
        userRepository.add(user2);

        List<User> users = userRepository.getUsers();

        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }
}