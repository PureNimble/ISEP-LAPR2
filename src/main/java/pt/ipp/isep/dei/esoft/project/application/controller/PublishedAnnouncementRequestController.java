package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.AbstractCollection;
import java.util.List;
import java.util.Optional;

public class PublishedAnnouncementRequestController {

    /**
     * The Published announcement repository.
     */
    PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    /**
     * The Announcement request repository.
     */
    AnnouncementRequestRepository announcementRequestRepository = null;

    /**
     * The Comission repository.
     */
    ComissionRepository comissionRepository = null;

    /**
     * The Employee repository.
     */
    EmployeeRepository employeeRepository = null;

    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = null;



    /**
     * Instantiates a new Published announcement request controller.
     */
    public PublishedAnnouncementRequestController() {
       getAnnouncementRequestRepository();
       getComissionRepository();
       getEmployeeRepository();
       getPublishedAnnouncementRepository();
       getAuthenticationRepository();
    }


    /**

     Returns an instance of the AuthenticationRepository.

     If the instance does not exist, it creates one using the Repositories class.

     @return The AuthenticationRepository instance
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }


    /**

     Returns the AnnouncementRequestRepository instance, creating it if necessary.

     @return The AnnouncementRequestRepository instance.
     */
    private AnnouncementRequestRepository getAnnouncementRequestRepository() {
        if (announcementRequestRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            announcementRequestRepository = repositories.getAnnouncementRequestRepository();
        }
        return announcementRequestRepository;
    }

    /**

     Returns the EmployeeRepository instance, creating it if necessary.

     @return The EmployeeRepository instance.
     */

    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     Retrieves the ComissionRepository instance and initializes it if it is null.
     @return ComissionRepository instance.
     */
    private ComissionRepository getComissionRepository() {
        if (comissionRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            comissionRepository = repositories.getComissionRepository();
        }
        return comissionRepository;
    }


    /**
     Retrieves the PublishedAnnoucementRepository instance and initializes it if it is null.
     @return PublishedAnnoucementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    private AnnouncementRequestMapper getAnnouncementRequestMapper(){

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();
        return announcementRequestMapper;
    }


    /**

     Returns a list of all Published Announcements.
     @return A List of Published Announcements objects.
     */
    public List<PublishedAnnouncement> getPublishedAnnouncements() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncements();
    }


    /**

     Returns a list of all Announcement Requests.
     @return A List of AnnouncementRequest objects.
     */
    public List<AnnouncementRequest> getAnnouncementRequestByMostRecent() {

        Employee agent = getEmployeeByEmail();

        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();

        return announcementRequestRepository.getAnnouncementRequestsByMostRecent(agent);
    }

    public List<AnnouncementRequestDto> toDto(){

        List<AnnouncementRequest> announcementRequests = getAnnouncementRequestByMostRecent();

        AnnouncementRequestMapper announcementRequestMapper = getAnnouncementRequestMapper();

        return  announcementRequestMapper.toDto(announcementRequests);
    }


    /**

     Returns a list of all available Comission objects.
     @return a list of Comission objects
     */

    public List<Comission> getComission() {
        ComissionRepository comissionRepository = getComissionRepository();
        return comissionRepository.getComission();
    }

    /**
     * Get current session email string.
     *
     * @return the string
     */
    public String getCurrentSessionEmail(){
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }


    /**
     * Get employee by email employee.
     *

     * @return the employee
     */
    public Employee getEmployeeByEmail(){

        String email = getCurrentSessionEmail();

        return getEmployeeRepository().getEmployeeByEmail(email);
    }

    /**
     * Get agent by description employee.
     *
     * @param agentDescription the agent description
     * @return the employee
     */

    /**

     Returns the Comission object with the specified description.
     @param comissionDescription the description of the Comission object to be retrieved
     @return the Comission object with the specified description
     */
    public Comission getComissionByDescription(Double comissionDescription) {
       ComissionRepository comissionRepository = getComissionRepository();

        //Get the TaskCategory by its description
        Comission comissionByDescription =
               comissionRepository.getComissionByDescription(comissionDescription);

        return comissionByDescription;

    }

    /**
     * Gets announcement request by description.
     *
     * @param announcementRequestDescription the announcement request description
     * @return the announcement request by description
     */
    public AnnouncementRequest getAnnouncementRequestByDescription(int announcementRequestDescription) {
        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();

        //Get the TaskCategory by its description
        AnnouncementRequest announcementRequestByDescription =
                announcementRequestRepository.getAnnouncementRequestByDescription(announcementRequestDescription);

        return announcementRequestByDescription;

    }

    public Optional<PublishedAnnouncement> createPublishAnnouncementRequest(double comissionDescription, int announcementRequestDtoDescription){

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();

        Comission comission = getComissionByDescription(comissionDescription);

        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();

        List<AnnouncementRequestDto> announcementRequestDtos = toDto();

        AnnouncementRequestMapper announcementRequestMapper = new AnnouncementRequestMapper();

        AnnouncementRequestDto announcementRequestDto =  announcementRequestMapper.getAnnouncementRequestDtoByDescription(announcementRequestDtos,announcementRequestDtoDescription);

        newPublishedAnnouncement = getPublishedAnnouncementRepository().publishedAnnouncementRequest(announcementRequestRepository.getAnnouncementsRequest(),announcementRequestDto,comission);

        return newPublishedAnnouncement;
    }

    public void rejectPublishAnnouncementRequest(int announcementRequestDtoDescription){

        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();

        List<AnnouncementRequestDto> announcementRequestDtos = toDto();

        AnnouncementRequestMapper announcementRequestMapper = getAnnouncementRequestMapper();

        AnnouncementRequestDto announcementRequestDto =  announcementRequestMapper.getAnnouncementRequestDtoByDescription(announcementRequestDtos,announcementRequestDtoDescription);

        announcementRequestRepository.rejectAnnouncementRequest(announcementRequestDto);


    }


}
