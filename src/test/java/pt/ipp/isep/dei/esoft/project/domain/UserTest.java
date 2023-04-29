package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    // with return 0
    @Test
    void compare() {
        String email = "johndoe@example.com";
        int passportNumber = 123456;
        int taxNumber = 789012;
        String name = "John Doe";
        int phoneNumber = 5551234;
        User user = new User(email, passportNumber, taxNumber, name, phoneNumber);
        assertEquals(0, user.compare(email));
    }

    //with return != 0
    @Test
    public void testCompare() {
        String email = "johndoe@example.com";
        int passportNumber = 123456;
        int taxNumber = 789012;
        String name = "John Doe";
        int phoneNumber = 5551234;
        User user = new User(email, passportNumber, taxNumber, name, phoneNumber);
        assertNotEquals(0, user.compare("janedoe@example.com"));
    }
}