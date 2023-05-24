package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementRequestRepository;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.ComissionRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementRequestControllerTest {



    @BeforeEach
    void setUp(){

    }


    @Test
    void getPublishedAnnouncements() {

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

        List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

        Date date = new Date();

        House house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South");
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Double comissionValue = 5.0;
        Comission comission = new Comission(comissionValue);
        Double price = 1000.32;
        Business business = new Business(price);

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(date,typeOfBusiness,house,propertyType,comission,business);
        publishedAnnouncements.add(publishedAnnouncement);

        controller.publishedAnnouncementRepository.add(publishedAnnouncement);

        assertEquals(publishedAnnouncements,controller.getPublishedAnnouncements());
    }

    @Test
    void getAnnouncementRequestByMostRecent() {

        PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

        List<AnnouncementRequest> announcementRequests = new ArrayList<>();

        Date date = new Date();

        House house = new House(100, 2, 2, 1, 1, new AvailableEquipment("air conditioning"), "Yes", "No", "South");
        PropertyType propertyType = new PropertyType("House");
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Sale");
        Double price = 1000.32;
        Business business = new Business(price);
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest(date, typeOfBusiness, house, propertyType, business, employee);
        announcementRequests.add(announcementRequest);

       controller.announcementRequestRepository.add(announcementRequest);


        assertEquals(announcementRequests, controller.getAnnouncementRequestByMostRecent(employee));
    }

    @Test
    void toDto() {
    }

    @Test
    void getComission() {

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

        assertEquals(comissions,controller.getComission());
    }

    @Test
    void getCurrentSessionEmail() {
        AuthenticationRepository repository = new AuthenticationRepository();
        assertNotNull(repository.getCurrentUserSession().getUserId().getEmail());
    }

    @Test
    void getEmployeeByEmail() {
    }

    @Test
    void getComissionByDescription() {
    }

    @Test
    void getAnnouncementRequestByDescription() {
    }

    @Test
    void createPublishAnnouncementRequest() {
    }
}