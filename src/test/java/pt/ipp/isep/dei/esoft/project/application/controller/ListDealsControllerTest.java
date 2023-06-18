package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.OfferRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ListDealsControllerTest {


    private ListDealsController controller;
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

    private int offerID;

    private OfferDto offerDto,offer1Dto,offer2Dto,offer3Dto;



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
        setDealsDTO();



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
    void setUpOfferID() {
        offerID = 1;
    }
    @BeforeEach
    void setUpStore() {
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com",5,1 );
    }


    @BeforeEach
    void setUpPublishedAnnouncements() {
        Comission comission1 = new Comission(25.00);
        Role role = new Role("Agent");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        AvailableEquipment equipment1 = new AvailableEquipment("Air Conditioning");
        Address address1 = new Address("3655 S Las Vegas Blvd", 892109, new District("Paradise"), new City("Las Vegas"), new State("Nevada"));
        Property property1 = new Property(274,2576, new Photos("url"),address1);
        Store store1 = new Store("Holloway",10234,address1,1234567890,"holloway@gmail.com", 0,1);
        Employee agent1 = new Employee("agent@this.app", 123456789, 987654321, "Miguel", 1234567890L, store1, roles, address1);
        PropertyType propertyType1 = new PropertyType("House");
        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Sale");
        Business business1 = new Business(200000);
        Date date1 = new GregorianCalendar(2023, Calendar.JUNE, 20).getTime();
        Date date3 = new GregorianCalendar(2023, Calendar.APRIL, 20).getTime();
        Date date4 = new GregorianCalendar(2023, Calendar.JANUARY, 20).getTime();
        AnnouncementState state1 = AnnouncementState.available;
        publishedAnnouncement1 = new PublishedAnnouncement(date1, typeOfBusiness1, property1, propertyType1, comission1, business1, 1, agent1, client1, state1, store1);

        Comission comission2 = new Comission(26.00);
        AvailableEquipment equipment2 = new AvailableEquipment("Air Frosting");
        Address address2 = new Address("Las Vegas Blvd", 892100, new District("gambas"), new City("portooo"), new State("Neves"));
        Property property2 = new Property(277,2576, new Photos("url"),address1);
        Employee agent2 = new Employee("age@this.app", 123446789, 987658321, "Miguelito", 1234587890L, store1,  roles, address1);
        Business business2 = new Business(2000);
        Date date2 = new GregorianCalendar(2024, Calendar.JUNE, 20).getTime();
        publishedAnnouncement = new PublishedAnnouncement(date2, typeOfBusiness1, property2, propertyType1, comission2, business2, 1, agent2, client2, state1, store1);


        publishedAnnouncement2 = new PublishedAnnouncement(date3,typeOfBusiness1,land,propertyType2,comission1,business1, 6, agent1, client1, AnnouncementState.sold, store1);

        publishedAnnouncement3 = new PublishedAnnouncement(date4,typeOfBusiness1,appartment,propertyType3,comission2,business1, 5, agent2, client2, AnnouncementState.sold, store1);

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
        offer = new Offer("Name",2100,publishedAnnouncement,OfferState.accepted,client1, offerID);
        offer1 = new Offer("Almeida",21000,publishedAnnouncement1,OfferState.accepted,client2,offerID);
        offer2 = new Offer("Miguel",21500,publishedAnnouncement2,OfferState.accepted,client1,offerID);
        offer3 = new Offer("Zé",20500,publishedAnnouncement3,OfferState.accepted,client2,offerID);
    }
    @BeforeEach
    void setDealsDTO() {
        offerDto = new OfferDto("Name",2100,publishedAnnouncement,OfferState.accepted,offerID,client1);
        offer1Dto = new OfferDto("Almeida",21000,publishedAnnouncement1,OfferState.accepted,offerID,client2);
        offer2Dto = new OfferDto("Miguel",21500,publishedAnnouncement2,OfferState.accepted,offerID,client1);
        offer3Dto = new OfferDto("Zé",20500,publishedAnnouncement3,OfferState.accepted,offerID,client2);
    }



    @Test
    void getDealsByAscendingAreaBubbleSort() {

        OfferRepository offerRepository = new OfferRepository();

        offerRepository.add(offer);
        offerRepository.add(offer1);
        offerRepository.add(offer2);
        offerRepository.add(offer3);

        controller = new ListDealsController(offerRepository);


        List<Offer> offersExpected = new ArrayList<>();
        offersExpected.add(offer2);
        offersExpected.add(offer3);
        offersExpected.add(offer1);
        offersExpected.add(offer);

        List<Offer> offersResult = controller.getDealsByAscendingAreaBubbleSort();

        assertEquals(offersExpected,offersResult);


    }

    @Test
    void getDealsByDescendingAreaBubbleSort() {

        OfferRepository offerRepository = new OfferRepository();

        offerRepository.add(offer);
        offerRepository.add(offer1);
        offerRepository.add(offer2);
        offerRepository.add(offer3);

        controller = new ListDealsController(offerRepository);



        List<Offer> offersExpected = new ArrayList<>();
        offersExpected.add(offer);
        offersExpected.add(offer1);
        offersExpected.add(offer3);
        offersExpected.add(offer2);

        List<Offer> offersResult = controller.getDealsByDescendingAreaBubbleSort();

        assertEquals(offersExpected,offersResult);


    }

    @Test
    void getDealsByAscendingAreaSortSelection() {

        OfferRepository offerRepository = new OfferRepository();

        offerRepository.add(offer);
        offerRepository.add(offer1);
        offerRepository.add(offer2);
        offerRepository.add(offer3);

        controller = new ListDealsController(offerRepository);



        List<Offer> offersExpected = new ArrayList<>();
        offersExpected.add(offer2);
        offersExpected.add(offer3);
        offersExpected.add(offer1);
        offersExpected.add(offer);


        List<Offer> offersResult = controller.getDealsByAscendingAreaSortSelection();

        assertEquals(offersExpected,offersResult);


    }

    @Test
    void getDealsByDescendingAreaSortSelection() {



        OfferRepository offerRepository = new OfferRepository();

        offerRepository.add(offer);
        offerRepository.add(offer1);
        offerRepository.add(offer2);
        offerRepository.add(offer3);

        controller = new ListDealsController(offerRepository);



        List<Offer> offersExpected = new ArrayList<>();
        offersExpected.add(offer);
        offersExpected.add(offer1);
        offersExpected.add(offer3);
        offersExpected.add(offer2);

        List<Offer> offersResult = controller.getDealsByDescendingAreaSortSelection();

        assertEquals(offersExpected,offersResult);



    }

    @Test
    void getOfferMostRecent() {

        OfferRepository offerRepository = new OfferRepository();

        offerRepository.add(offer);
        offerRepository.add(offer1);
        offerRepository.add(offer2);
        offerRepository.add(offer3);

        controller = new ListDealsController(offerRepository);



        List<Offer> offersResult = new ArrayList<>();
        offersResult.add(offer);
        offersResult.add(offer1);
        offersResult.add(offer2);
        offersResult.add(offer3);

        assertEquals(offersResult,controller.getOfferMostRecent());




    }


    @Test
    void toDtoDescendingAreaBubbleSort() {

//        OfferRepository offerRepository = new OfferRepository();
//
//        offerRepository.add(offer);
//        offerRepository.add(offer1);
//        offerRepository.add(offer2);
//        offerRepository.add(offer3);
//
//        controller = new ListDealsController(offerRepository);
//
//        List<OfferDto> offersResult = new ArrayList<>();
//        offersResult.add(offerDto);
//        offersResult.add(offer1Dto);
//        offersResult.add(offer3Dto);
//        offersResult.add(offer2Dto);
//
//        assertEquals(offersResult,controller.toDtoDescendingAreaBubbleSort());


    }

    @Test
    void toDtoAscendingAreaBubbleSort() {

//        OfferRepository offerRepository = new OfferRepository();
//
//        offerRepository.add(offer);
//        offerRepository.add(offer1);
//        offerRepository.add(offer2);
//        offerRepository.add(offer3);
//
//        controller = new ListDealsController(offerRepository);
//
//        List<OfferDto> offersExpected = new ArrayList<>();
//        offersExpected.add(offer2Dto);
//        offersExpected.add(offer3Dto);
//        offersExpected.add(offer1Dto);
//        offersExpected.add(offerDto);
//
//        assertEquals(offersExpected,controller.toDtoAscendingAreaBubbleSort());

    }

    @Test
    void toDtoDescendingAreaSortSelection() {

//      OfferRepository offerRepository = new OfferRepository();
//
//       offerRepository.add(offer);
//       offerRepository.add(offer1);
//       offerRepository.add(offer2);
//       offerRepository.add(offer3);
//
//      controller = new ListDealsController(offerRepository);
//
//       List<OfferDto> offersExpected = new ArrayList<>();
//        offersExpected.add(offerDto);
//        offersExpected.add(offer1Dto);
//        offersExpected.add(offer3Dto);
//        offersExpected.add(offer2Dto);
//
//       assertEquals(offersExpected,controller.toDtoDescendingAreaSortSelection());


    }

    @Test
    void toDtoAscendingAreaSortSelection() {

//        OfferRepository offerRepository = new OfferRepository();
//
//      offerRepository.add(offer);
//       offerRepository.add(offer1);
//       offerRepository.add(offer2);
//      offerRepository.add(offer3);
//
//      controller = new ListDealsController(offerRepository);
//
//      List<OfferDto> offersExpected = new ArrayList<>();
//        offersExpected.add(offer2Dto);
//        offersExpected.add(offer3Dto);
//        offersExpected.add(offer1Dto);
//        offersExpected.add(offerDto);
//
//       assertEquals(offersExpected,controller.toDtoAscendingAreaSortSelection());


    }

    @Test
    void toDtoOffersMostRecent() {

//        OfferRepository offerRepository = new OfferRepository();
//
//        offerRepository.add(offer);
//        offerRepository.add(offer1);
//        offerRepository.add(offer2);
//        offerRepository.add(offer3);
//
//        controller = new ListDealsController(offerRepository);
//
//        List<OfferDto> offersExpected = new ArrayList<>();
//        offersExpected.add(offerDto);
//        offersExpected.add(offer1Dto);
//        offersExpected.add(offer2Dto);
//        offersExpected.add(offer3Dto);
//
//        assertEquals(offersExpected,controller.toDtoOffersMostRecent());
    }
}