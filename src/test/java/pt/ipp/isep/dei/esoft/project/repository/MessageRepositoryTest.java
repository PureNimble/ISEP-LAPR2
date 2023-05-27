package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {

    String name = "John Doe";
    String name1 = "Mary Jane";
    int phoneNumber = 1234567890;
    int phoneNumber1 = 1122334455;
    String description = "Test message";
    String description1 = "Description Of Jane";
    Date date = new Date();
    int initialTime = 10;
    int initialTime1 = 7;

    int endTime = 11;
    int endTime1 = 9;
    Comission com = new Comission(25.00);
    Property property = new Property(2, 2);
    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);
    PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business);
    Message message = new Message(name, phoneNumber, description, date, initialTime, endTime, p1);
    Message message1 = new Message(name1, phoneNumber1, description1, date, initialTime1, endTime1, p1);

    @Test
    void addValidMessage() {
        MessageRepository repository = new MessageRepository();

        Optional<Message> result = repository.add(message);

        assertTrue(result.isPresent());
        assertEquals(message, result.get());
        List<Message> messages = repository.getMessages();
        assertTrue(messages.contains(message));
    }

    @Test
    void addInvalidMessage() {
        MessageRepository repository = new MessageRepository();
        Message message1 = message;
        Message message2 = message;
        repository.add(message1);

        Optional<Message> result = repository.add(message2);

        assertFalse(result.isPresent());
        List<Message> messages = repository.getMessages();
        assertTrue(messages.contains(message2));
    }

    @Test

    public void addOverlapingTime() {
        MessageRepository repository = new MessageRepository();
        Message message1 = message;
        Message message2 = message;
        message2.setInitialTime(9);
        repository.add(message1);

        Optional<Message> result = repository.add(message2);

        assertFalse(result.isPresent());
        List<Message> messages = repository.getMessages();
        assertTrue(messages.contains(message2));
    }


    @Test
    void getMessagesEmptyList() {
        MessageRepository repository = new MessageRepository();

        List<Message> messages = repository.getMessages();

        assertTrue(messages.isEmpty());
    }

    @Test
    void getMessagesNonEmptyList() {
        MessageRepository repository = new MessageRepository();
        repository.add(message1);
        repository.add(message);

        List<Message> messages = repository.getMessages();

        assertEquals(2, messages.size());
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message));
    }
}