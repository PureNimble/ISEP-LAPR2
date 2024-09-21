package pt.ipp.isep.dei.esoft.project.application.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListMessageControllerTest {
    private ListMessageController listMessageController;
    private MessageRepository messageRepository;
    private AuthenticationRepository authenticationRepository;
    private UserRepository userRepository;

    Date date1 = new GregorianCalendar(2023, Calendar.JUNE, 20).getTime();
    TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
    Property property1;
    PropertyType propertyType1 = new PropertyType("House");
    Comission comission1 = new Comission(25.00);
    Business business1 = new Business(200000);
    List<Role> roles = new ArrayList<>();
    Role role = new Role("Agent");


    @BeforeEach
    void setUp() {

        roles.add(role);

    }
    Employee agent1;
    Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
    Client client1 = new Client("pedro@gmail.com", 123456789, 987654321, "Pedro", address1, 1234567890);
    AnnouncementState state1 = AnnouncementState.available;
    Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0,1);


    Message message1 = new Message("Pedro",1234567890,"Olá",new Date(2023,Calendar.DECEMBER,1),11,12,new PublishedAnnouncement(date1, typeOfBusiness1, property1, propertyType1, comission1, business1, 1, agent1, client1, state1, store1), MessageState.UNANSWERED, false);
    Message message2 = new Message("Luna",2345678901L,"Olá",new Date(2023,Calendar.DECEMBER,2),11,12,new PublishedAnnouncement(date1, typeOfBusiness1, property1, propertyType1, comission1, business1, 1, agent1, client1, state1, store1), MessageState.UNANSWERED, false);

    public ListMessageControllerTest() {
        agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890L, store1, roles, address1);
        property1 = new Property(274,2576, new Photos("url"),address1);
    }

    @BeforeEach
    public void setup() {
        messageRepository = new MessageRepository();
        authenticationRepository = new AuthenticationRepository();
        userRepository = new UserRepository();
        listMessageController = new ListMessageController(messageRepository, authenticationRepository, userRepository);
    }

    @Test
    public void testSortMessageRequests() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(message1);
        messageList.add(message2);

        listMessageController.sortMessageRequests(messageList);

        assertEquals(message1, messageList.get(0));
        assertEquals(message2, messageList.get(1));
    }

    @Test
    public void testGetMessageRequestsForPeriod() {
        MessageRepository messageRepository = new MessageRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        UserRepository userRepository = new UserRepository();
        ListMessageController listMessageController = new ListMessageController(messageRepository, authenticationRepository, userRepository);
        Date beginDate = new Date(1, Calendar.JANUARY, 30);
        Date endDate = new Date(3923, Calendar.DECEMBER, 31);

        List<Message> messageList = new ArrayList<>();
        messageList.add(message1);
        messageList.add(message2);
        messageRepository.add(message1);
        messageRepository.add(message2);

        List<Message> result = listMessageController.getMessageRequestsForPeriod(beginDate, endDate);

        assertEquals(messageList, result);
    }

    @Test
    public void testGetMessagesByAscendingDate() {
        MessageRepository messageRepository = new MessageRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        UserRepository userRepository = new UserRepository();
        ListMessageController listMessageController = new ListMessageController(messageRepository, authenticationRepository, userRepository);
        List<Message> messageList = new ArrayList<>();
        messageList.add(message1);
        messageList.add(message2);
        messageRepository.add(message1);
        messageRepository.add(message2);

        List<Message> result = listMessageController.getMessagesByAscendingDate();

        assertEquals(message1, result.get(0));
        assertEquals(message2, result.get(1));
    }
}
