package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AnnouncementRequestRepository {
    private List<AnnouncementRequest> announcementRequests = new ArrayList<>();

    public Optional<AnnouncementRequest> add(AnnouncementRequest announcementRequest) {

        Optional<AnnouncementRequest> newAnnouncementRequest = Optional.empty();
        boolean operationSuccess = false;

        if (validateTaskCategory(announcementRequest)) {
            newAnnouncementRequest = Optional.of(announcementRequest);
            operationSuccess = announcementRequests.add(newAnnouncementRequest.get());
        }

        if (!operationSuccess) {
            newAnnouncementRequest = Optional.empty();
        }

        return newAnnouncementRequest;
    }

    private boolean validateTaskCategory(AnnouncementRequest announcementRequest) {
        boolean isValid = !announcementRequests.contains(announcementRequest);
        return isValid;
    }

    public Optional<AnnouncementRequest> announcementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract) {


        Optional<AnnouncementRequest> optionalValue = Optional.empty();

        AnnouncementRequest announcementRequest;


        if (property.toString().equals("Rent")) {
            announcementRequest = new AnnouncementRequest(date, typeOfBusiness, property, propertyType, business, durationOfContract);
        } else {
            announcementRequest = new AnnouncementRequest(date, typeOfBusiness, property, propertyType, business);
        }


        if (addAnnouncementRequest(announcementRequest)) {
            optionalValue = Optional.of(announcementRequest);

        }
        return optionalValue;
    }

    private boolean addAnnouncementRequest(AnnouncementRequest announcementRequest) {
        boolean success = false;
        AnnouncementRequestController controller = new AnnouncementRequestController();
        List<AnnouncementRequest> announcementRequests = controller.getAnnouncementRequest();
        if (validate(announcementRequest)) {
            // A clone of the task is added to the list of tasks, to avoid side effects and outside manipulation.
            success = announcementRequests.add(announcementRequest);
        }
        return success;

    }

    private boolean validate(AnnouncementRequest announcementRequest) {
        return tasksDoNotContain(announcementRequest);
    }


    private boolean tasksDoNotContain(AnnouncementRequest announcementRequest) {
        AnnouncementRequestController controller = new AnnouncementRequestController();
        return !controller.getAnnouncementRequest().contains(announcementRequest);
    }




    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<AnnouncementRequest> getAnnouncementsRequest() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return announcementRequests;
    }
}
