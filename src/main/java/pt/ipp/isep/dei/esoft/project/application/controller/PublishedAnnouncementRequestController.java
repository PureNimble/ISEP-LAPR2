package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementRequest;
import pt.ipp.isep.dei.esoft.project.domain.Comission;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.AbstractCollection;
import java.util.List;
import java.util.Optional;

public class PublishedAnnouncementRequestController {

    PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    AnnouncementRequestRepository announcementRequestRepository = null;

    ComissionRepository comissionRepository = null;

    EmployeeRepository employeeRepository = null;

    AuthenticationRepository authenticationRepository = null;


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

    /**

     Returns a list of all Announcement Requests.
     @return A List of AnnouncementRequest objects.
     */
    public List<AnnouncementRequest> getAnnouncementRequestByMostRecent(Employee agent) {
        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();
        return announcementRequestRepository.getAnnouncementRequestsByMostRecent(agent);
    }

    /**

     Returns a list of all available Comission objects.
     @return a list of Comission objects
     */

    public List<Comission> getComission() {
        ComissionRepository comissionRepository = getComissionRepository();
        return comissionRepository.getComission();
    }

    public String getCurrentSessionEmail(){
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }


    public Employee getEmployeeByEmail(String email){

        return getEmployeeRepository().getEmployeeByEmail(email);
    }

    public Employee getAgentByDescription(String agentDescription){
        EmployeeRepository employeeRepository = new EmployeeRepository();

        return employeeRepository.getEmployeeByString(agentDescription);
    }


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

    public AnnouncementRequest getAnnouncementRequestByDescription(int announcementRequestDescription) {
        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();

        //Get the TaskCategory by its description
        AnnouncementRequest announcementRequestByDescription =
                announcementRequestRepository.getAnnouncementRequestByDescription(announcementRequestDescription);

        return announcementRequestByDescription;

    }

    public Optional<PublishedAnnouncement> createPublishAnnouncementRequest(Comission comission, AnnouncementRequest announcementRequest){
        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(comission,announcementRequest);

        if (!getPublishedAnnouncementRepository().getPublishedAnnouncements().contains(publishedAnnouncement)){
            newPublishedAnnouncement = getPublishedAnnouncementRepository().publishedAnnouncementRequest(comission,announcementRequest);
            getAnnouncementRequestRepository().remove(announcementRequest);
        }
        return newPublishedAnnouncement;
    }

}
