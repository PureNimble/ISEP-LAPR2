package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRepositoryTest {

    @Test
    void doLogin() {
        AuthenticationRepository repository = new AuthenticationRepository();
        assertFalse(repository.doLogin("persona@example.com", "password123"));

    }

    @Test
    void doLogout() {
        AuthenticationRepository repository = new AuthenticationRepository();
        repository.doLogout();
        assertNotNull(repository.getCurrentUserSession());
    }

    @Test
    void getCurrentUserSession() {
        AuthenticationRepository repository = new AuthenticationRepository();
        assertNotNull(repository.getCurrentUserSession());
    }

    @Test
    void addUserRole() {
        AuthenticationRepository repository = new AuthenticationRepository();
        assertTrue(repository.addUserRole("admin", "Administrator"));
    }

    @Test
    void addUserWithRole() {
        AuthenticationRepository repository = new AuthenticationRepository();
        assertFalse(repository.addUserWithRole("Person Name", "persona@example.com", "password123", "user"));
    }

    @Test
    void passwordGenerator() {
        AuthenticationRepository repository = new AuthenticationRepository();
        String password = repository.passwordGenerator();
        assertNotNull(password);
        assertEquals(7, password.length());
    }
}