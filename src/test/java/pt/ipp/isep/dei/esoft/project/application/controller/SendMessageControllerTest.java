package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageControllerTest {

    String name = "John Doe";
    long phoneNumber = 1234567890;
    String description = "Test message";
    Date date = new Date();
    int initialTime = 10;
    int endTime = 11;
    Comission com = new Comission(25.00);
    Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    Store store = new Store("Holloway",10234,address2,1234567890,"holloway@gmail.com", 0);

    Role role = new Role("Agent");

    List<Role> roles = new ArrayList<>();


    Employee employee =  new Employee("age@this.app", 123446789, 987658321, "Miguelito", 1234587890L, store, roles , address2);

    Address address = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));

    Property property = new Property(274,55,address);
    PropertyType propertyType = new PropertyType("House");
    TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
    Business business = new Business(200);
    PublishedAnnouncement p1 = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, com, business,employee,new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L),55,AnnouncementState.available,store);
    Message message = new Message(name, phoneNumber, description, date, initialTime, endTime, p1,MessageState.UNANSWERED,false);


    @Test
    void getMessageFromRepository() {
        SendMessageController controller = new SendMessageController();
        List<Message> expectedMessages = createSampleMessages();
        MessageRepository repository = new MessageRepository();
        repository.add(expectedMessages.get(0));
        repository.add(expectedMessages.get(1));
        Repositories.getInstance().setMessageRepository(repository);
        controller.messageRepository = repository;

        List<Message> messages = controller.getMessage();

        assertEquals(expectedMessages, messages);
    }

    @Test
    void createNewMessageToAgentValid() {
        SendMessageController controller = new SendMessageController();

        MessageRepository repository = new MessageRepository();
        Repositories.getInstance().setMessageRepository(repository);
        controller.messageRepository = repository;

        Optional<Message> result = controller.createNewMessageToAgent(name, description, phoneNumber, date, initialTime, endTime, p1,MessageState.UNANSWERED);

        assertTrue(result.isPresent());
        Message message = result.get();
        assertEquals(name, message.getName());
        assertEquals(description, message.getDescription());
        assertEquals(phoneNumber, message.getPhoneNumber());
        assertEquals(date, message.getInitialDate());
        assertEquals(initialTime, message.getInitialTime());
        assertEquals(endTime, message.getEndTime());
        assertEquals(p1, message.getPublishedAnnouncement());
    }

    @Test
    void createNewMessageToAgentInvalid() {
        SendMessageController controller = new SendMessageController();

        Message existingMessage = message;
        MessageRepository repository = new MessageRepository();
        repository.add(existingMessage);
        Repositories.getInstance().setMessageRepository(repository);
        controller.messageRepository = repository;

        Optional<Message> result = controller.createNewMessageToAgent(name, description, phoneNumber, date, initialTime, endTime, p1,MessageState.UNANSWERED);

        assertFalse(result.isPresent());
    }

    private List<Message> createSampleMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(createSampleMessage("John Doe", 123456789, "Message 1", new Date(), 10, 11, null ));
        messages.add(createSampleMessage("Jane Smith", 987654321, "Message 2", new Date(), 13, 14, null));
        return messages;
    }

    private Message createSampleMessage(String name, int phoneNumber, String description, Date date, int initialTime, int endTime,PublishedAnnouncement announcement) {
        Message message = new Message();
        message.setName(name);
        message.setPhoneNumber(phoneNumber);
        message.setDescription(description);
        message.setInitialDate(date);
        message.setInitialTime(initialTime);
        message.setEndTime(endTime);
        message.setPublishedAnnouncement(announcement);
        return message;
    }

}