package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.ui.console.PublishAnnouncementRequestUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementRequestControllerTest {


    private PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

    private PublishedAnnouncement publishedAnnouncement, publishedAnnouncement1;

    private House house;

    private Property land;

    private Residence appartment;

    private PropertyType propertyType, propertyType1, propertyType2;

    private Employee employee, employee1, employee2;

    private AnnouncementRequest announcementRequest, announcementRequest1, announcementRequest2;

    private Date date = new Date();

    private TypeOfBusiness typeOfBusiness, typeOfBusiness1;

    private Comission comission, comission1, comission2;

    private Business business, business1, business2;

    private Role role, role1;

    private Address address, address1, address2;

    private Store store, store1, store2;


    @BeforeEach
    void setUpPropertys() {
        house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South");
        land = new Property(5, 1000);
        appartment = new Residence(20, 150, 3, 2, 1, new AvailableEquipment("air conditioning"));
    }

    @BeforeEach
    void setUpPropertyTypes() {
        propertyType = new PropertyType("House");
        propertyType1 = new PropertyType("Appartment");
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
        double comissionValue1 = 10.5;
        comission1 = new Comission(comissionValue1);
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
        employee = new Employee("employee@this.app", 12, 12, "nome", 1234567, store, roles, address);
        employee1 = new Employee("employee1@this.app", 12, 12, "nome", 19191919, store, roles, address2);
        employee2 = new Employee("employee2@this.app", 12, 12, "nome", 19191919, store, roles, address2);
    }

    @BeforeEach
    void setUpAnnouncementRequest() {
        announcementRequest = new AnnouncementRequest(date, typeOfBusiness, house, propertyType, business, employee);
        announcementRequest1 = new AnnouncementRequest(date, typeOfBusiness1, land, propertyType, business, employee);
        announcementRequest2 = new AnnouncementRequest(date, typeOfBusiness, appartment, propertyType2, business1, employee1);
    }


    @Test
    void getPublishedAnnouncements() {



        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        publishedAnnouncements.add(publishedAnnouncement);

        controller.publishedAnnouncementRepository.add(publishedAnnouncement);

        assertEquals(publishedAnnouncements, controller.getPublishedAnnouncements());
    }

    @Test
    void getAnnouncementRequestByMostRecent() {


        List<AnnouncementRequest> announcementRequests = new ArrayList<>();

        announcementRequests.add(announcementRequest);

        controller.announcementRequestRepository.add(announcementRequest);


        assertEquals(announcementRequests, controller.getAnnouncementRequestByMostRecent());
    }

    @Test
    void toDto() {
    }

    @Test
    void getComission() {



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
    void getEmployeeByEmail() {

        assertEquals(employee,controller.getEmployeeByEmail());

    }

    @Test
    void getComissionByDescription() {

        double comissionValue = 50;
        controller.comissionRepository.add(comission);

        assertEquals(comission,controller.getComissionByDescription(comissionValue));

    }

    @Test
    void getAnnouncementRequestByDescription() {

        int index = 0;

        controller.announcementRequestRepository.add(announcementRequest);
        controller.announcementRequestRepository.add(announcementRequest1);
        controller.announcementRequestRepository.add(announcementRequest2);

        assertEquals(announcementRequest,controller.getAnnouncementRequestByDescription(index));

    }

    @Test
    void createPublishAnnouncementRequest() {
    }
}