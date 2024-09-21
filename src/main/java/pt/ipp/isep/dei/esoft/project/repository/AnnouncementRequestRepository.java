package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.Serializable;
import java.util.*;

/**
 * The AnnouncementRequestRepository class is responsible for managing the list of AnnouncementRequest objects.
 * <p>
 * It provides methods to add an AnnouncementRequest to the list, retrieve a list of AnnouncementRequest objects, and validate that an AnnouncementRequest is not already in the list.
 */
public class AnnouncementRequestRepository implements Serializable {

    /**
     * The list of AnnouncementRequest objects.
     */
    private List<AnnouncementRequest> announcementRequests = new ArrayList<>();

    /**
     * Adds an AnnouncementRequest to the list if it is valid and not already in the list.
     *
     * @param announcementRequest The AnnouncementRequest to add.
     * @return An Optional containing the added AnnouncementRequest if the operation was successful, otherwise an empty Optional.
     */
    public Optional<AnnouncementRequest> add(AnnouncementRequest announcementRequest) {

        Optional<AnnouncementRequest> newAnnouncementRequest = Optional.empty();
        boolean operationSuccess = false;

        if (!validateAnnouncementRequest(announcementRequest)) {
            newAnnouncementRequest = Optional.of(announcementRequest);
            operationSuccess = announcementRequests.add(newAnnouncementRequest.get());
        }

        if (!operationSuccess) {
            newAnnouncementRequest = Optional.empty();
        }

        return newAnnouncementRequest;
    }


    /**
     * Validates that an AnnouncementRequest is not already in the list.
     *
     * @param announcementRequest The AnnouncementRequest to validate.
     * @return true if the AnnouncementRequest is valid, false otherwise.
     */
    private boolean validateAnnouncementRequest(AnnouncementRequest announcementRequest) {
        boolean isValid = announcementRequests.contains(announcementRequest);
        return isValid;
    }

    /**
     * Creates an AnnouncementRequest object and adds it to the list if it is valid and not already in the list.
     *
     * @param date               The date of the AnnouncementRequest.
     * @param typeOfBusiness     The type of business for the AnnouncementRequest.
     * @param property           The property for the AnnouncementRequest.
     * @param propertyType       The property type for the AnnouncementRequest.
     * @param business           The business for the AnnouncementRequest.
     * @param durationOfContract The duration of the contract for the AnnouncementRequest.
     * @param agent              the agent
     * @param client             the client
     * @return An Optional containing the added AnnouncementRequest if the operation was successful, otherwise an empty Optional.
     */
    public Optional<AnnouncementRequest> announcementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract, Employee agent, Client client) {

        Optional<AnnouncementRequest> optionalValue = Optional.empty();

        AnnouncementRequest announcementRequest;

        if (propertyType.toString().equals("Rent")) {
            announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, property, propertyType, business, durationOfContract, agent, client);
        } else {
            announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, property, propertyType, business, agent, client);
        }

        if (addAnnouncementRequest(announcementRequest)) {
            optionalValue = Optional.of(announcementRequest);

        }
        return optionalValue;
    }

    /**
     * Adds an AnnouncementRequest to the list if it is valid and not already in the list.
     *
     * @param announcementRequest The AnnouncementRequest to add.
     * @return true if the AnnouncementRequest is valid and was added to the list, false otherwise.
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
    /**
     * Validates an announcement request by checking if the tasks do not contain the request.
     *
     * @param announcementRequest The announcement request to be validated.
     * @return True if the tasks do not contain the request, false otherwise.
     */
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

    /**
     * Get announcement requests by most recent list.
     *
     * @param agent the agent
     * @return the list
     */
    public List<AnnouncementRequest> getAnnouncementRequestsByMostRecent(Employee agent) {
        List<AnnouncementRequest> newAnnouncementRequest = new ArrayList<>();

        int aux = 0;
        for (AnnouncementRequest announcementRequest : announcementRequests) {
            if (announcementRequest.getAgent().equals(agent)) {
                if (announcementRequest.getStatus().equals("")) {
                    newAnnouncementRequest.add(announcementRequest);
                }
            }

        }


        /**
         * Sorts the list of offers by the date of the associated published announcements in ascending order,
         * and then reverses the list to obtain a descending order by date.
         *
         * @param resultList The list of offers to be sorted.
         * @return The sorted list of offers in descending order by date.
         */
        newAnnouncementRequest.sort(new Comparator<AnnouncementRequest>() {
            @Override
            public int compare(AnnouncementRequest a1, AnnouncementRequest a2) {
                Date date1 = a1.getDate();
                Date date2 = a2.getDate();

                return date1.compareTo(date2);

            }
        });

        Collections.reverse(newAnnouncementRequest);



        return newAnnouncementRequest;
    }

    /**
     * Gets announcement request by description.
     *
     * @param annnouncementRequestDescription the annnouncement request description
     * @return the announcement request by description
     */
    public AnnouncementRequest getAnnouncementRequestByDescription(int annnouncementRequestDescription) {
        AnnouncementRequest newAnnouncementRequest = announcementRequests.get(annnouncementRequestDescription);
        AnnouncementRequest announcementRequest = null;
        if (announcementRequests.contains(newAnnouncementRequest)) {
            announcementRequest = announcementRequests.get(announcementRequests.indexOf(newAnnouncementRequest));
        }
        if (announcementRequest == null) {
            throw new IllegalArgumentException(
                    "Announcement Request requested for [" + newAnnouncementRequest.toString() + "] does not exist.");
        }
        return announcementRequest;
    }

    /**
     * Reject announcement request.
     *
     * @param announcementRequestDto the announcement request dto
     */
    public void rejectAnnouncementRequest(AnnouncementRequestDto announcementRequestDto){

        int i =0;

        AnnouncementRequest announcementRequest = new AnnouncementRequest(announcementRequestDto);

        for (AnnouncementRequest announcementRequest1: announcementRequests) {

            if (announcementRequest1.equals(announcementRequest)){
               announcementRequests.get(i).setStatus("false");
            }

            i++;
        }


    }


}
