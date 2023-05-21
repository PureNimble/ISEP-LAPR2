package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

/**
 * A repository for storing and managing PublishedAnnouncement objects.
 */
public class PublishedAnnouncementRepository {
    /**
     * A list of published announcements.
     */
    private List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

    /**
     * Adds a new published announcement to the repository.
     *
     * @param publishedAnnouncement the published announcement to add.
     * @return an Optional containing the newly added published announcement if the operation was successful,         otherwise an empty Optional.
     */
    public Optional<PublishedAnnouncement> add(PublishedAnnouncement publishedAnnouncement) {

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();
        boolean operationSuccess = false;

        if (validate(publishedAnnouncement)) {
            newPublishedAnnouncement = Optional.of(publishedAnnouncement);
            operationSuccess = publishedAnnouncements.add(newPublishedAnnouncement.get());
        }

        if (!operationSuccess) {
            newPublishedAnnouncement = Optional.empty();
        }

        return newPublishedAnnouncement;
    }

    private boolean validate(PublishedAnnouncement publishedAnnouncement) {
        boolean isValid = !publishedAnnouncements.contains(publishedAnnouncement);
        return isValid;
    }

    /**
     * Checks if a published announcement is valid and adds it to the repository if it is.
     *
     * @param date               the date of the published announcement.
     * @param typeOfBusiness     the type of business of the published announcement.
     * @param property           the property of the published announcement.
     * @param propertyType       the property type of the published announcement.
     * @param comission          the commission of the published announcement.
     * @param business           the business of the published announcement.
     * @param durationOfContract the duration of the contract of the published announcement.
     * @return an Optional containing the newly added published announcement if the operation was successful,         otherwise an empty Optional.
     */
    public Optional<PublishedAnnouncement> publishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract) {


        Optional<PublishedAnnouncement> optionalValue = Optional.empty();

        PublishedAnnouncement publishedAnnouncement;

        if (property.toString().equals("Rent")) {
            publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract);
        } else {
            publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business);
        }

        if (addPublishedAnnouncement(publishedAnnouncement)) {
            optionalValue = Optional.of(publishedAnnouncement);

        }
        return optionalValue;
    }

    /**
     * Published announcement request optional.
     *
     * @param comission           the comission
     * @param announcementRequest the announcement request
     * @return the optional
     */
    public Optional<PublishedAnnouncement> publishedAnnouncementRequest( Comission comission, AnnouncementRequest announcementRequest) {


        Optional<PublishedAnnouncement> optionalValue = Optional.empty();

        PublishedAnnouncement publishedAnnouncement;


        publishedAnnouncement = new PublishedAnnouncement(comission,announcementRequest);


        if (addPublishedAnnouncement(publishedAnnouncement)) {
            optionalValue = Optional.of(publishedAnnouncement);

        }
        return optionalValue;
    }

    /**
     * Adds a new published announcement to the repository.
     *
     * @param publishedAnnouncement the published announcement to add.
     * @return true if the published announcement was added to the repository, false otherwise.
     */
    private boolean addPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        boolean success = false;
        PublishAnnouncementController controller = new PublishAnnouncementController();
        List<PublishedAnnouncement> publishedAnnouncements = controller.getPublishedAnnoucement();
        if (validatePublishedAnnouncement(publishedAnnouncement)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = publishedAnnouncements.add(publishedAnnouncement);
        }
        return success;

    }
    /**

     This method validates a published announcement, checking if it is not already present in the repository.
     @param publishedAnnouncement the published announcement to be validated
     @return true if the published announcement is valid, false otherwise
     */
    private boolean validatePublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        return tasksDoNotContainAnnouncement(publishedAnnouncement);
    }

    /**

     This method checks if a PublishedAnnouncement object is present in the list of published announcements
     @param publishAnnouncement the PublishedAnnouncement object to check for in the list
     @return true if the object is not present in the list, false otherwise
     */
    private boolean tasksDoNotContainAnnouncement(PublishedAnnouncement publishAnnouncement) {
        PublishAnnouncementController controller = new PublishAnnouncementController();
        return !controller.getPublishedAnnoucement().contains(publishAnnouncement);
    }


    /**
     * This method returns a defensive (immutable) copy of the list of published announcements.
     *
     * @return The list of published announcements.
     */
    public List<PublishedAnnouncement> getPublishedAnnouncements() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return publishedAnnouncements;
    }

    /**
     * Gets published announcements desc.
     *
     * @return the published announcements desc
     */
    public List<PublishedAnnouncement> getPublishedAnnouncementsDesc() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        Collections.sort(publishedAnnouncements, Comparator.comparing(PublishedAnnouncement::getDate).reversed());
        return publishedAnnouncements;
    }

    /**
     * A comparator that compares PublishedAnnouncement objects in ascending order based on city name.
     */
    Comparator<PublishedAnnouncement> compareToAscendingCityName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };
    /**
     * A comparator that compares PublishedAnnouncement objects in descending order based on city name.
     */
    Comparator<PublishedAnnouncement> compareToDescendingCityName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };
    /**
     * A comparator that compares PublishedAnnouncement objects in ascending order based on price.
     */
    Comparator<PublishedAnnouncement> compareToAscendingPrice = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };
    /**
     * A comparator that compares PublishedAnnouncement objects in descending order based on price.
     */
    Comparator<PublishedAnnouncement> compareToDescendingPrice = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };
    /**
     * A comparator that compares PublishedAnnouncement objects in ascending order based on state name.
     */
    Comparator<PublishedAnnouncement> compareToAscendingStateName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };
    /**
     * A comparator that compares PublishedAnnouncement objects in descending order based on state name.
     */
    Comparator<PublishedAnnouncement> compareToDescendingStateName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };


    /**
     * The Compare to descending date.
     */
    Comparator<PublishedAnnouncement> compareToDescendingDate = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return announcement2.getDate().compareTo(announcement1.getDate());
        }
    };

}
