package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Optional;

public class PublishedAnnouncement {

    private String contractyType;

    private Property property;

    private PropertyType propertyType;

    public PublishedAnnouncement(String contractyType, Property property, PropertyType propertyType) {
        this.contractyType = contractyType;
        this.property = property;
        this.propertyType = propertyType;
    }

    public String toString() {
        return String.format("Contract Type:%s\n" +
                        "Property Type:%s\n" +
                        "%s",
                contractyType, propertyType,property.toString());
    }

    public Optional<PublishedAnnouncement> publishedAnnouncement(Property property) {


        Optional<PublishedAnnouncement> optionalValue = Optional.empty();

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(contractyType, property, propertyType);

        if (addLand(publishedAnnouncement)) {
            optionalValue = Optional.of(publishedAnnouncement);

        }
        return optionalValue;
    }

    private boolean addLand(PublishedAnnouncement publishedAnnouncement) {
        boolean success = false;
        PublishAnnouncementController controller = new PublishAnnouncementController();
        List<PublishedAnnouncement> publishedAnnouncements = controller.getPublishedAnnoucement();
        if (validateLand(publishedAnnouncement)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = publishedAnnouncements.add(publishedAnnouncement);
        }
        return success;

    }

    private boolean validateLand(PublishedAnnouncement publishedAnnouncement) {
        return tasksDoNotContainLand(publishedAnnouncement);
    }


    private boolean tasksDoNotContainLand(PublishedAnnouncement publishAnnouncement) {
        PublishAnnouncementController controller = new PublishAnnouncementController();
        return !controller.getPublishedAnnoucement().contains(publishAnnouncement);
    }
}
