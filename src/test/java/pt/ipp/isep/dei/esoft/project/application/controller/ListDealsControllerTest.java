package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListDealsControllerTest {

    private PublishedAnnouncement publishedAnnouncement, publishedAnnouncement1,publishedAnnouncement2,publishedAnnouncement3;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType,propertyType3;

    private PropertyType propertyType2;

    private Employee employee, employee1;


    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Comission comission;

    private Business business, business1;

    private Role role;

    private Offer offer,offer1,offer2,offer3;
    private Address address2;

    private Store store;

    private Client client1,client2;



    @BeforeEach
    void setUpPropertys() {
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South", new Photos("pjh"), address2);
        land = new Property(5, 1000, new Photos("urlll"),address2);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"), new Photos("urllll"), address2);
        setUpPropertyTypes();
        setUpBusiness();
        setUpAddress();
        setUpStore();
        setEmployees();
        setUpTypeOfBusiness();
        setUpRoles();
        setUpPublishedAnnouncements();
        setUpComission();
        setDeals();
    }

    @BeforeEach
    void setUpClient() {
        client1 = new Client("pedro@isep.ipp.pt", 123456789, 987654321, "Pedro", address2, 1234567890);
        client2 = new Client("luna@isep.ipp.pt", 123450789, 987654021, "Luna", address2, 1234567090);
    }


    @BeforeEach
    void setUpPropertyTypes() {
        propertyType = new PropertyType("House");
        propertyType2 = new PropertyType("Land");
        propertyType3 = new PropertyType("Appartment");

    }

    @BeforeEach
    void setUpTypeOfBusiness() {
        typeOfBusiness = new TypeOfBusiness("Sale");
        typeOfBusiness1 = new TypeOfBusiness("Rent");
    }

    @BeforeEach
    void setUpComission() {
        double comissionValue = 50.0;
        comission = new Comission(comissionValue);

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
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com",5);
    }


    @BeforeEach
    void setUpPublishedAnnouncements() {
        Comission comission1 = new Comission(25.00);
        Role role = new Role("Agent");
        AvailableEquipment equipment1 = new AvailableEquipment("Air Conditioning");
        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Property property1 = new Property(274,2576, new Photos("url"),address1);
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0);
        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890L, store1, (List<Role>) role, address1);
        PropertyType propertyType1 = new PropertyType("House");
        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
        Business business1 = new Business(200000);
        Date date1 = new GregorianCalendar(2023, Calendar.JUNE, 20).getTime();
        AnnouncementState state1 = AnnouncementState.available;
        publishedAnnouncement1 = new PublishedAnnouncement(date1, typeOfBusiness1, property1, propertyType1, comission1, business1, agent1, client1, 1, state1, store1);

        Comission comission2 = new Comission(26.00);
        AvailableEquipment equipment2 = new AvailableEquipment("Air Frosting");
        Address address2 = new Address("Las Vegas Blvd", 892100, new District("gambas"), new City("portooo"), new State("Neves"));
        Property property2 = new Property(277,2576, new Photos("url"),address1);
        Employee agent2 = new Employee("age@this.app", 123446789, 987658321, "Miguelito", 1234587890L, store1, (List<Role>) role, address1);
        Business business2 = new Business(2000);
        Date date2 = new GregorianCalendar(2024, Calendar.JUNE, 20).getTime();
        publishedAnnouncement = new PublishedAnnouncement(date2, typeOfBusiness1, property2, propertyType1, comission2, business2, agent2, client2, 1, state1, store1);


        publishedAnnouncement2 = new PublishedAnnouncement(date2,typeOfBusiness1,land,propertyType2,comission1,business1,agent1,client1,6,AnnouncementState.sold,store);
        publishedAnnouncement3 = new PublishedAnnouncement(date1,typeOfBusiness1,appartment,propertyType3,comission2,business1,agent2,client2,5,AnnouncementState.sold,store1);

    }

    @BeforeEach
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("emailExample@this.app", 12, 12, "nome", 1234567, store, roles, address2);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);

    }


    @BeforeEach
    void setDeals() {
        offer = new Offer("Name",2100,publishedAnnouncement,OfferState.accepted,client1);
        offer1 = new Offer("Name",2100,publishedAnnouncement1,OfferState.accepted,client1);
        offer2 = new Offer("Miguel",21000,publishedAnnouncement2,OfferState.accepted,client1);
        offer3 = new Offer("Zé",20500,publishedAnnouncement3,OfferState.accepted,client2);
    }



    @Test
    void getDealsByAscendingAreaBubbleSort() {

        OfferRepository offerRepository = new OfferRepository();

        offerRepository.add(offer);
        offerRepository.add(offer1);
        offerRepository.add(offer2);
        offerRepository.add(offer3);


        List<Offer> offersResult = new ArrayList<>();
        offersResult.add(offer2);
        offersResult.add(offer3);
        offersResult.add(offer);
        offersResult.add(offer1);

        assertEquals(offersResult,offerRepository.getOffersByAreaAscendingUsingBubbleSortAlgorithm());


    }

    @Test
    void getDealsByDescendingAreaBubbleSort() {
    }

    @Test
    void getDealsByAscendingAreaSortSelection() {
    }

    @Test
    void getDealsByDescendingAreaSortSelection() {
    }

    @Test
    void getOfferMostRecent() {
    }
}