package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementRepositoryTest {

    private PublishedAnnouncementRepository repository;
    private PublishedAnnouncement announcement1;
    private PublishedAnnouncement announcement2;

    @BeforeEach
    void setUp() {
        repository = new PublishedAnnouncementRepository();
        announcement1 = new PublishedAnnouncement(
                new Date(), new TypeOfBusiness("sale"), new Property(235, 67), new PropertyType("house"),
                new Comission(78), new Business(456788));
        announcement2 = new PublishedAnnouncement(
                new Date(), new TypeOfBusiness("sale"), new Property(285, 57), new PropertyType("Land"),
                new Comission(78), new Business(456788));
    }

    @Test
    void add() {
        Optional<PublishedAnnouncement> addedAnnouncement = repository.add(announcement1);
        assertTrue(addedAnnouncement.isPresent());
        assertEquals(announcement1, addedAnnouncement.get());

        List<PublishedAnnouncement> announcements = repository.getPublishedAnnouncements();
        assertTrue(announcements.contains(announcement1));

    }

    @Test
    void publishedAnnouncement() {
        repository.add(announcement1);
        repository.add(announcement2);

        List<PublishedAnnouncement> announcements = repository.getPublishedAnnouncements();
        assertTrue(announcements.contains(announcement1));
        assertTrue(announcements.contains(announcement2));
    }


}