package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRequestRepositoryTest {

    private AnnouncementRequestRepository repository;

    @BeforeEach
    void setUp() {
        repository = new AnnouncementRequestRepository();
    }

    @Test
    void add() {
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest(new Date(), new TypeOfBusiness("Sale"), new Property(345,789), new PropertyType("House"), new Business(89999), employee);
        Optional<AnnouncementRequest> addedAnnouncementRequest = repository.add(announcementRequest);
        Assertions.assertTrue(addedAnnouncementRequest.isPresent());
        Assertions.assertEquals(announcementRequest, addedAnnouncementRequest.get());
        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();
        Assertions.assertTrue(announcementRequests.contains(announcementRequest));
    }

    @Test
    void announcementRequest() {
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequest announcementRequest = new AnnouncementRequest(new Date(), new TypeOfBusiness("Sale"), new Property(345,789), new PropertyType("House"), new Business(89999), employee);
        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();
        Assertions.assertFalse(announcementRequests.contains(announcementRequest));
    }

    @Test
    void getAnnouncementsRequest() {
        Role role = new Role("Agent");
        Address address2 = new Address("Main Street", 1234, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Test Store", 1, address2, 5551234, "test@store.com");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        Employee employee = new Employee("12",12,12,"nome", 12, store, roles,address2);

        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        AnnouncementRequest announcementRequest1 = new AnnouncementRequest(
                new Date(),
                new TypeOfBusiness("Rent"),
                new Property(567,89),
                new PropertyType("House"),
                new Business(45666),
                employee
        );

        AnnouncementRequest announcementRequest2 = new AnnouncementRequest(
                new Date(),
                new TypeOfBusiness("Rent"),
                new Property(967,89),
                new PropertyType("Land"),
                new Business(666),
                employee
        );

        repository.add(announcementRequest1);
        repository.add(announcementRequest2);

        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();

        Assertions.assertSame(announcementRequests, repository.getAnnouncementsRequest());
        Assertions.assertEquals(announcementRequests.size(), 2);
        Assertions.assertTrue(announcementRequests.contains(announcementRequest1));
        Assertions.assertTrue(announcementRequests.contains(announcementRequest2));
    }
}