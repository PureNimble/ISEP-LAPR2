package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**

 The AnnouncementRequestRepository class is responsible for managing the list of AnnouncementRequest objects.

 It provides methods to add an AnnouncementRequest to the list, retrieve a list of AnnouncementRequest objects, and validate that an AnnouncementRequest is not already in the list.
 */
public class AnnouncementRequestRepository {

    /**

     The list of AnnouncementRequest objects.
     */
    private List<AnnouncementRequest> announcementRequests = new ArrayList<>();
    /**

     Adds an AnnouncementRequest to the list if it is valid and not already in the list.

     @param announcementRequest The AnnouncementRequest to add.

     @return An Optional containing the added AnnouncementRequest if the operation was successful, otherwise an empty Optional.
     */
    public Optional<AnnouncementRequest> add(AnnouncementRequest announcementRequest) {

        Optional<AnnouncementRequest> newAnnouncementRequest = Optional.empty();
        boolean operationSuccess = false;

        if (validateAnnouncementRequest(announcementRequest)) {
            newAnnouncementRequest = Optional.of(announcementRequest);
            operationSuccess = announcementRequests.add(newAnnouncementRequest.get());
        }

        if (!operationSuccess) {
            newAnnouncementRequest = Optional.empty();
        }

        return newAnnouncementRequest;
    }

    /**

     Validates that an AnnouncementRequest is not already in the list.
     @param announcementRequest The AnnouncementRequest to validate.
     @return true if the AnnouncementRequest is valid, false otherwise.
     */
    private boolean validateAnnouncementRequest(AnnouncementRequest announcementRequest) {
        boolean isValid = !announcementRequests.contains(announcementRequest);
        return isValid;
    }
    /**

     Creates an AnnouncementRequest object and adds it to the list if it is valid and not already in the list.

     @param date The date of the AnnouncementRequest.

     @param typeOfBusiness The type of business for the AnnouncementRequest.

     @param property The property for the AnnouncementRequest.

     @param propertyType The property type for the AnnouncementRequest.

     @param business The business for the AnnouncementRequest.

     @param durationOfContract The duration of the contract for the AnnouncementRequest.

     @return An Optional containing the added AnnouncementRequest if the operation was successful, otherwise an empty Optional.
     */
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

    /**

     Adds an AnnouncementRequest to the list if it is valid and not already in the list.
     @param announcementRequest The AnnouncementRequest to add.
     @return true if the AnnouncementRequest is valid and was added to the list, false otherwise.
     */
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

    /**
     * Checks if the list of announcement requests does not contain the specified announcement request.
     *
     * @param announcementRequest The announcement request to check.
     * @return True if the list of announcement requests does not contain the specified announcement request, false otherwise.
     */
    private boolean tasksDoNotContain(AnnouncementRequest announcementRequest) {
        AnnouncementRequestController controller = new AnnouncementRequestController();
        return !controller.getAnnouncementRequest().contains(announcementRequest);
    }

    /**
     * This method returns a defensive (immutable) copy of the list of announcement requests.
     *
     * @return The list of announcement requests.
     */
    public List<AnnouncementRequest> getAnnouncementsRequest() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return announcementRequests;
    }
}
