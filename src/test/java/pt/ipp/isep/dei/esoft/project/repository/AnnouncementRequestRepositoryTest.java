package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

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
        AnnouncementRequest announcementRequest = new AnnouncementRequest(new Date(), new TypeOfBusiness("Sale"), new Property(345,789), new PropertyType("House"), new Business(89999));
        Optional<AnnouncementRequest> addedAnnouncementRequest = repository.add(announcementRequest);
        Assertions.assertTrue(addedAnnouncementRequest.isPresent());
        Assertions.assertEquals(announcementRequest, addedAnnouncementRequest.get());
        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();
        Assertions.assertTrue(announcementRequests.contains(announcementRequest));
    }

    @Test
    void announcementRequest() {
        AnnouncementRequest announcementRequest = new AnnouncementRequest(new Date(), new TypeOfBusiness("Sale"), new Property(345,789), new PropertyType("House"), new Business(89999));
        List<AnnouncementRequest> announcementRequests = repository.getAnnouncementsRequest();
        Assertions.assertFalse(announcementRequests.contains(announcementRequest));
    }

    @Test
    void getAnnouncementsRequest() {
        AnnouncementRequestRepository repository = new AnnouncementRequestRepository();

        AnnouncementRequest announcementRequest1 = new AnnouncementRequest(
                new Date(),
                new TypeOfBusiness("Rent"),
                new Property(567,89),
                new PropertyType("House"),
                new Business(45666),
                12
        );

        AnnouncementRequest announcementRequest2 = new AnnouncementRequest(
                new Date(),
                new TypeOfBusiness("Rent"),
                new Property(967,89),
                new PropertyType("Land"),
                new Business(666),
                6
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