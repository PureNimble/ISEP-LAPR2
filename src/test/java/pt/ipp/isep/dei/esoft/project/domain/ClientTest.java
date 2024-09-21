package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    // with return 0
    @Test
    void compare() {
        String email = "johndoe@example.com";
        int passportNumber = 123456;
        int taxNumber = 789012;
        String name = "John Doe";
        int phoneNumber = 5551234;
        Client client = new Client(email, passportNumber, taxNumber, name, phoneNumber);
        assertEquals(0, client.compare(email));
    }

    //with return != 0
    @Test
    public void testCompare() {
        String email = "johndoe@example.com";
        int passportNumber = 123456;
        int taxNumber = 789012;
        String name = "John Doe";
        int phoneNumber = 5551234;
        Client client = new Client(email, passportNumber, taxNumber, name, phoneNumber);
        assertNotEquals(0, client.compare("janedoe@example.com"));
    }

    @Test
    void getClientEmail() {
        String expectedEmail = "john.doe@example.com";
        Client client = new Client("client@this.app",123456789,123456789,"client",1234567890L);
        client.setEmail(expectedEmail);

        // Act
        String actualEmail = client.getClientEmail();

        // Assert
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testClientConstructorWithParameters() {
        // Arrange
        String expectedEmail = "john.doe@example.com";
        int expectedPassportNumber = 123456789;
        int expectedTaxNumber = 987654321;
        String expectedName = "John Doe";
        Address expectedAddress = new Address("123 Main St", 13456, new District("Test District"), new City("Test City"), new State("Test State"));
        long expectedPhoneNumber = 1234567890;

        // Act
        Client client = new Client(expectedEmail, expectedPassportNumber, expectedTaxNumber, expectedName, expectedAddress, expectedPhoneNumber);

        // Assert
        assertEquals(expectedEmail, client.getEmail());
        assertEquals(expectedPassportNumber, client.getPassportNumber());
        assertEquals(expectedTaxNumber, client.getTaxNumber());
        assertEquals(expectedName, client.getName());
        assertEquals(expectedAddress, client.getAddress());
        assertEquals(expectedPhoneNumber, client.getPhoneNumber());
    }
}