package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MessageRepositoryTest {

    private MessageRepository repository;


    String name = "John Doe";
    String name1 = "Mary Jane";
    long phoneNumber = 1234567890;
    long phoneNumber1 = 1122334455;
    String description = "Test message";
    String description1 = "Description Of Jane";
    Date date = new Date();
    int initialTime = 10;
    int initialTime1 = 7;

    int endTime = 11;
    int endTime1 = 9;
    Comission com = new Comission(25.00);

    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);
    List<Role> roles = new ArrayList<>();

    Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    Store store = new Store("Holloway",10234,address2,1234567890,"holloway@gmail.com", 0);

    Employee employee =  new Employee("age@this.app", 123446789, 987658321, "Miguelito", 1234587890L, store, roles , address2);

    Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    Property property = new Property(2, 2,address);

    PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L),55,AnnouncementState.available,store);
    Message message = new Message(name, phoneNumber, description, date, initialTime, endTime, p1,MessageState.UNANSWERED,false);
    Message message1 = new Message(name1, phoneNumber1, description1, date, initialTime1, endTime1, p1,MessageState.UNANSWERED,false);

    Message message2 = new Message(name, phoneNumber1, description, date, initialTime1, endTime, p1,MessageState.UNANSWERED,false);


    @BeforeEach
    void setUp() {
        repository = new MessageRepository();

    }

    @BeforeEach
    void setUpRoles(){
        roles.add(new Role("Agent"));
    }


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
    @Test
    void getMessagesByAscendingDate() {
        // Arrange
        Message message1 = new Message();
        message1.setInitialDate(new Date(System.currentTimeMillis()));
        message1.setMessageState(MessageState.UNANSWERED); // Set a default message state
        repository.add(message1);

        Message message2 = new Message();
        message2.setInitialDate(new Date(System.currentTimeMillis() - 86400000)); // Set message2 to an earlier date
        message2.setMessageState(MessageState.UNANSWERED); // Set a default message state
        repository.add(message2);

        // Act
        List<Message> sortedMessages = repository.getMessagesByAscendingDate();

        // Assert
        assertEquals(2, sortedMessages.size());
        assertEquals(message2, sortedMessages.get(0));
        assertEquals(message1, sortedMessages.get(1));
    }

//    @Test
//    public void getMessageRequests() {
//
//    }

}