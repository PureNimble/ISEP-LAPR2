package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

public class PublishedAnnouncementRepository {

    private List<PublishedAnnouncement> publishedAnnouncements = new ArrayList<>();

    public Optional<PublishedAnnouncement> add(PublishedAnnouncement publishedAnnouncement) {

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskCategory(publishedAnnouncement)) {
            newPublishedAnnouncement = Optional.of(publishedAnnouncement);
            operationSuccess = publishedAnnouncements.add(newPublishedAnnouncement.get());
        }

        if (!operationSuccess) {
            newPublishedAnnouncement = Optional.empty();
        }

        return newPublishedAnnouncement;
    }

    private boolean validateTaskCategory(PublishedAnnouncement publishedAnnouncement) {
        boolean isValid = !publishedAnnouncements.contains(publishedAnnouncement);
        return isValid;
    }

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

    private boolean validatePublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        return tasksDoNotContainAnnouncement(publishedAnnouncement);
    }


    private boolean tasksDoNotContainAnnouncement(PublishedAnnouncement publishAnnouncement) {
        PublishAnnouncementController controller = new PublishAnnouncementController();
        return !controller.getPublishedAnnoucement().contains(publishAnnouncement);
    }


    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<PublishedAnnouncement> getPublishedAnnouncements() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return publishedAnnouncements;
    }

    Comparator<PublishedAnnouncement> compareToAscendingCityName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToDescendingCityName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToAscendingPrice = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToDescendingPrice = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToAscendingStateName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };

    Comparator<PublishedAnnouncement> compareToDescendingStateName = new Comparator<PublishedAnnouncement>() {
        @Override
        public int compare(PublishedAnnouncement announcement1, PublishedAnnouncement announcement2) {
            return 0;
        }
    };


}
