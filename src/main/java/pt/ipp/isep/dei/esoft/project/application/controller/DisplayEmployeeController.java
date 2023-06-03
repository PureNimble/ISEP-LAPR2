package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.List;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

public class DisplayEmployeeController {

    private StoreRepository storeRepository = null;

    private AuthenticationRepository authenticationRepository = null;

    private EmployeeRepository employeeRepository = null;

    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    /**

     Constructs a new instance of PlaceOfferController and initializes the repositories.
     */
    public DisplayEmployeeController() {
        getStoreRepository();
        getAuthenticationRepository();
        getEmployeeRepository();
        getPublishedAnnouncementRepository();
    }

    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }

    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the EmployeeRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    public void getStoresProperty() {
        StoreRepository storeRepository = getStoreRepository();
        EmployeeRepository employeeRepository = getEmployeeRepository();
        PublishedAnnouncementRepository announcementRepository = getPublishedAnnouncementRepository();
        List<PublishedAnnouncement> announcementList = announcementRepository.getPublishedAnnouncements();
        List<Employee> employeeList = employeeRepository.getEmployees();
        storeRepository.getStoresProperty(announcementList, employeeList);
    }

    public List <Employee> getEmployeesAllphabeticallySorted() {
        getStoresProperty();
        StoreRepository storeRepository = getStoreRepository();
        EmployeeRepository employeeRepository = getEmployeeRepository();
        List<Store> listStore = storeRepository.getStoresByMostListings();
        return employeeRepository.getEmployeesAllphabeticallySorted(listStore);
    }
}
