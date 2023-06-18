package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageMapperTest {



    private MessageDto messageDTO, message1DTO, message2DTO;

    private PublishedAnnouncement publishedAnnouncement, publishedAnnouncement1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType;

    private PropertyType propertyType2;

    private Employee employee, employee1;

    private Message message, message1, message2;

    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Comission comission;

    private Business business, business1;

    private Role role;

    private Address address2;

    private Store store;



    @BeforeEach
    void setUpPropertys() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South",address);
        land = new Property(5, 1000,new Photos("url"), address2);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"), new Photos("urllll"), address);
        setUpPropertyTypes();
        setUpBusiness();
        setUpAddress();
        setUpStore();
        setEmployees();
        setUpTypeOfBusiness();
        setUpRoles();
        setUpMessageRequest();
        setUpPublishedAnnouncements();
        setUpComission();
        setUpMessageRequestDTO();
    }

    @BeforeEach
    void setUpPropertyTypes() {
        propertyType = new PropertyType("House");
        propertyType2 = new PropertyType("Land");
    }

    @BeforeEach
    void setUpTypeOfBusiness() {
        typeOfBusiness = new TypeOfBusiness("Sale");
        typeOfBusiness1 = new TypeOfBusiness("Rent");
    }

    @BeforeEach
    void setUpComission() {
        comission = new Comission(25);

    }

    @BeforeEach
    void setUpBusiness() {
        Double price = 1000.32;
        business = new Business(price);
        Double price1 = 102.213;
        business1 = new Business(price1);
    }

    @BeforeEach
    void setUpRoles() {
        role = new Role("Agent");
    }

    @BeforeEach
    void setUpAddress() {
        address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
    }

    @BeforeEach
    void setUpStore() {
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com",9,1);
    }


    @BeforeEach
    void setUpPublishedAnnouncements() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Elvis",224,address,1274567809,"elvis@gmail.com", 0,1 );
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("Agent"));
        Employee employee1 = new Employee("employee@example.com", 123456789, 987654321, "Name Employee", 5551234, store,  roles, address);
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com",9,1 );

        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, 77, employee1, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L), AnnouncementState.available, store1);
        publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, land, propertyType, comission, business, 88, employee, new Client("client@this.app", 123456789,1234567890,"client",address2,1234567890L), AnnouncementState.available, store);

    }

    @BeforeEach
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("emailExample@this.app", 12, 12, "nome", 1234567, store, roles, address2);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);

    }
    @BeforeEach
    void setUpMessageRequest() {
        message = new Message("Pedro",1234567890,"Olá",new Date(2023, Calendar.JANUARY,1), 11,12, publishedAnnouncement,MessageState.UNANSWERED,false);
        message1 = new Message("Luna",1234567890,"Olá!",new Date(2023, Calendar.JANUARY,2), 12,13, publishedAnnouncement1,MessageState.UNANSWERED,false);
        message2 = new Message("LUNAPEDRO",1234567890,"Olá!!",new Date(2023, Calendar.JANUARY,3), 13,14, publishedAnnouncement,MessageState.UNANSWERED,false);

    }

    @BeforeEach
    void setUpMessageRequestDTO() {
        messageDTO = new MessageDto("Pedro",1234567890,"Olá",new Date(2023, Calendar.JANUARY,1), 11,12, publishedAnnouncement,MessageState.UNANSWERED,false);
        message1DTO = new MessageDto("Luna",1234567890,"Olá!",new Date(2023, Calendar.JANUARY,2), 12,13, publishedAnnouncement1,MessageState.UNANSWERED,false);
        message2DTO = new MessageDto("LUNAPEDRO",1234567890,"Olá!!",new Date(2023, Calendar.JANUARY,3), 13,14, publishedAnnouncement,MessageState.UNANSWERED,false);

    }

//    @Test
//    void toDto() {
//        MessageMapper messageMapper = new MessageMapper();
//
//        List<Message> messageList = new ArrayList<>();
//        messageList.add(message);
//        messageList.add(message1);
//        messageList.add(message2);
//
//        List<MessageDto> messageDto = new ArrayList<>();
//        messageDto.add(messageDTO);
//        messageDto.add(message1DTO);
//        messageDto.add(message2DTO);
//
//        assertEquals(messageDto, messageMapper.toDto(messageList));
//
//    }
//
//    @Test
//    void toDtoObject() {
//
//        MessageMapper messageMapper = new MessageMapper();
//        assertEquals(messageDTO,messageMapper.toDtoObject("Pedro",1234567890,"Olá",new Date(2023, Calendar.JANUARY,1), 11,12, publishedAnnouncement,MessageState.UNANSWERED,false));
//    }
}