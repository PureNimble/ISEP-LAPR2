package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Optional;

/**

 The PublishedAnnouncement class represents a published announcement of a property's availability.
 */
public class PublishedAnnouncement {

    /**

     The type of contract.
     */
    private String contractyType;
    /**

     The property associated with the announcement.
     */
    private Property property;
    /**

     The type of property.
     */
    private PropertyType propertyType;
    /**

     Constructs a new PublishedAnnouncement with the specified contract type, property, and property type.
     @param contractyType the type of contract
     @param property the property associated with the announcement
     @param propertyType the type of property
     */
    public PublishedAnnouncement(String contractyType, Property property, PropertyType propertyType) {
        this.contractyType = contractyType;
        this.property = property;
        this.propertyType = propertyType;
    }
    /**

     Returns a string representation of the PublishedAnnouncement.
     @return a string representation of the PublishedAnnouncement
     */
    public String toString() {
        return String.format("Contract Type:%s\n" +
                        "Property Type:%s\n" +
                        "%s",
                contractyType, propertyType,property.toString());
    }
    /**

     Publishes an announcement for the specified property.

     @param property the property to publish the announcement for

     @return an optional containing the published announcement if successful, otherwise an empty optional
     */
    public Optional<PublishedAnnouncement> publishedAnnouncement(Property property) {

        Optional<PublishedAnnouncement> optionalValue = Optional.empty();

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(contractyType, property, propertyType);

        if (addLand(publishedAnnouncement)) {
            optionalValue = Optional.of(publishedAnnouncement);
        }
        return optionalValue;
    }

    /**

     Adds the specified published announcement to the list of published announcements if it is valid.
     @param publishedAnnouncement the published announcement to add
     @return true if the published announcement is added successfully, otherwise false
     */
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

    /**

     Validates the specified published announcement to ensure it does not conflict with existing published announcements.
     @param publishedAnnouncement the published announcement to validate
     @return true if the published announcement is valid, otherwise false
     */
    private boolean validateLand(PublishedAnnouncement publishedAnnouncement) {
        return tasksDoNotContainLand(publishedAnnouncement);
    }
    /**

     Returns true if the specified published announcement does not conflict with existing published announcements, otherwise false.
     @param publishAnnouncement the published announcement to check for conflicts
     @return true if the specified published announcement does not conflict with existing published announcements, otherwise false
     */
    private boolean tasksDoNotContainLand(PublishedAnnouncement publishAnnouncement) {
        PublishAnnouncementController controller = new PublishAnnouncementController();
        return !controller.getPublishedAnnoucement().contains(publishAnnouncement);
    }
}
