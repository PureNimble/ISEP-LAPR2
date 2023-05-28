package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishAnnouncementRequestUI;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementRequestControllerTest {


    private PublishedAnnouncement publishedAnnouncement, publishedAnnouncement1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType;

    private PropertyType propertyType2;

    private Employee employee, employee1;

    private AnnouncementRequest announcementRequest, announcementRequest1, announcementRequest2;

    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Comission comission;

    private Business business, business1;

    private Role role;

    private Address address2;

    private Store store;

    private  AnnouncementRequestDto announcementRequestDto;

    @BeforeEach
    void setUpPropertys() {
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South");
        land = new Property(5, 1000);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"));
        setUpPropertyTypes();
        setUpBusiness();
        setUpAddress();
        setUpStore();
        setEmployees();
        setUpTypeOfBusiness();
        setUpRoles();
        setUpAnnouncementRequest();
        setUpPublishedAnnouncements();
        setUpComission();
        setUpAnnouncementRequestDto();
    }

    @BeforeEach
    void setUpAnnouncementRequestDto() {
        announcementRequestDto = new AnnouncementRequestDto("",date, typeOfBusiness, house, propertyType, business, employee);
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
        store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
    }


    @BeforeEach
    void setUpPublishedAnnouncements() {
        publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, house, propertyType, comission, business);
        publishedAnnouncement1 = new PublishedAnnouncement(date, typeOfBusiness, land, propertyType, comission, business);

    }

    @BeforeEach
    void setEmployees() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee = new Employee("emailExample@this.app", 12, 12, "nome", 1234567, store, roles, address2);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);

    }


    @BeforeEach
    void setUpAnnouncementRequest() {
        announcementRequest = new AnnouncementRequest("", date, typeOfBusiness, house, propertyType, business, employee);
        announcementRequest1 = new AnnouncementRequest("", date, typeOfBusiness, land, propertyType, business, employee);
        announcementRequest2 = new AnnouncementRequest("", date, typeOfBusiness, appartment, propertyType2, business1, employee1);
    }

    @Test
    void getPublishedAnnouncementsTest() {

        Repositories repositories = Repositories.getInstance();

        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        publishedAnnouncementRepository.add(publishedAnnouncement);
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        publishedAnnouncements.add(publishedAnnouncement);

        assertEquals(publishedAnnouncements, controller.getPublishedAnnouncements());
    }

    @Test
    void getAnnouncementRequestByMostRecentTest() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        List<AnnouncementRequest> announcementRequests = new ArrayList<>();

        announcementRequests.add(announcementRequest);

        controller.announcementRequestRepository.add(announcementRequest);

        assertEquals(announcementRequests, controller.getAnnouncementRequestByMostRecent());
    }

    @Test
    void toDto() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        announcementRequestRepository.add(announcementRequest);
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        List<AnnouncementRequestDto> announcementRequestDtos = new ArrayList<>();
        announcementRequestDtos.add(announcementRequestDto);

        assertEquals(announcementRequestDtos,controller.toDto());
    }

    @Test
    void getComissionTest() {
        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();


        List<Comission> comissions = new ArrayList<>();

        comissions.add(new Comission(50));
        comissions.add(new Comission(20));
        comissions.add(new Comission(10.5));
        comissions.add(new Comission(23.7));

        controller.comissionRepository.add(new Comission(50));
        controller.comissionRepository.add(new Comission(20));
        controller.comissionRepository.add(new Comission(10.5));
        controller.comissionRepository.add(new Comission(23.7));

        assertEquals(comissions, controller.getComission());
    }

    @Test
    void getEmployeeByEmailTest() {

        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        ComissionRepository comissionRepository = new ComissionRepository();
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);


        assertEquals(employee, controller.getEmployeeByEmail());

    }

    @Test
    void getComissionByDescriptionTest() {

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

        double comissionValue = 50;
        controller.comissionRepository.add(comission);

        assertEquals(comission, controller.getComissionByDescription(comissionValue));

    }

    @Test
    void getAnnouncementRequestByDescriptionTest() {

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();
        int index = 0;

        controller.announcementRequestRepository.add(announcementRequest);
        controller.announcementRequestRepository.add(announcementRequest1);
        controller.announcementRequestRepository.add(announcementRequest2);

        assertEquals(announcementRequest, controller.getAnnouncementRequestByDescription(index));

    }

    @Test
    void createPublishAnnouncementRequestTest() {

        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        publishedAnnouncementRepository.add(publishedAnnouncement1);
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        announcementRequestRepository.add(announcementRequest1);
        ComissionRepository comissionRepository = new ComissionRepository();
        comissionRepository.add(comission);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");


        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.of(publishedAnnouncement1);

        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        publishedAnnouncements.add(publishedAnnouncement1);


        assertEquals(newPublishedAnnouncement, controller.createPublishAnnouncementRequest(comission.getComission(), 0));
        assertEquals(publishedAnnouncements, controller.getPublishedAnnouncements());
        assertEquals(1, controller.getPublishedAnnouncements().size());

        PublishedAnnouncement publishedAnnouncement = newPublishedAnnouncement.get();

        assertEquals(publishedAnnouncement1.getComission(), publishedAnnouncement.getComission());
        assertEquals(publishedAnnouncement1.getBusiness(), publishedAnnouncement.getBusiness());
        assertEquals(publishedAnnouncement1.getDate(), publishedAnnouncement.getDate());
        assertEquals(publishedAnnouncement1.getProperty(), publishedAnnouncement.getProperty());
        assertEquals(publishedAnnouncement1.getPropertyType(), publishedAnnouncement.getPropertyType());
        assertEquals(publishedAnnouncement1.getTypeOfBusiness(), publishedAnnouncement.getTypeOfBusiness());

    }

    @Test
    void rejectPublishAnnouncementRequest() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
        publishedAnnouncementRepository.add(publishedAnnouncement1);
        AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
        announcementRequestRepository.add(announcementRequest1);
        ComissionRepository comissionRepository = new ComissionRepository();
        comissionRepository.add(comission);
        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeRepository.add(employee);
        AuthenticationRepository authenticationRepository = new AuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_AGENT, AuthenticationController.ROLE_AGENT);
        authenticationRepository.addUserWithRole("Diogo", "emailExample@this.app", "1231dwadwd", AuthenticationController.ROLE_AGENT);
        authenticationRepository.doLogin("emailExample@this.app", "1231dwadwd");


        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController(publishedAnnouncementRepository, announcementRequestRepository, comissionRepository, employeeRepository, authenticationRepository);


        controller.rejectPublishAnnouncementRequest(0);

        assertEquals("false",announcementRequest1.getStatus());



    }
}