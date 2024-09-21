package pt.ipp.isep.dei.esoft.project.application.controller;

import java.util.List;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.domain.StoreEmployeeDTO;
import pt.ipp.isep.dei.esoft.project.domain.StoreEmployeeMapper;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

/**
 * The type Display employee controller.
 */
public class DisplayEmployeeController {
    /**
     * Retrieves the StoreRepository instance.
     *
     * @return The StoreRepository instance.
     */
    private StoreRepository storeRepository = null;
    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return The AuthenticationRepository instance.
     */
    private AuthenticationRepository authenticationRepository = null;
    /**
     * Retrieves the EmployeeRepository instance.
     *
     * @return The EmployeeRepository instance.
     */
    private EmployeeRepository employeeRepository = null;
    /**
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    /**
     * Constructs a new instance of PlaceOfferController and initializes the repositories.
     */
    public DisplayEmployeeController() {
        getStoreRepository();
        getAuthenticationRepository();
        getEmployeeRepository();
        getPublishedAnnouncementRepository();
    }
    /**
     * Retrieves the StoreRepository instance.
     *
     * @return The StoreRepository instance.
     */
    private StoreRepository getStoreRepository() {
        if (storeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the StoreRepository
            storeRepository = repositories.getStoreRepository();
        }
        return storeRepository;
    }
    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return The AuthenticationRepository instance.
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
     * Retrieves the PublishedAnnouncementRepository instance.
     *
     * @return The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        if (publishedAnnouncementRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            publishedAnnouncementRepository = repositories.getPublishedAnnouncementRepository();
        }
        return publishedAnnouncementRepository;
    }
    /**
     * Retrieves the EmployeeRepository instance.
     *
     * @return The EmployeeRepository instance.
     */
    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the EmployeeRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     * Gets stores property.
     */
    public void getStoresProperty() {
        StoreRepository storeRepository = getStoreRepository();
        //EmployeeRepository employeeRepository = getEmployeeRepository();
        PublishedAnnouncementRepository announcementRepository = getPublishedAnnouncementRepository();
        List<PublishedAnnouncement> announcementList = announcementRepository.getPublishedAnnouncements();
        //List<Employee> employeeList = employeeRepository.getEmployees();
        storeRepository.getStoresProperty(announcementList);
    }

    /**
     * Gets employees allphabetically sorted.
     *
     * @return the employees allphabetically sorted
     */
    public List <StoreEmployeeDTO> toDTO() {
        //getStoresProperty();
        StoreEmployeeMapper storeEmployeeMapper = new StoreEmployeeMapper();
        List<Store> listStore = storeRepository.getStoresByMostAvailableListings();
        List<Employee> listEmployee = employeeRepository.getEmployeesAllphabeticallySorted();
        return storeEmployeeMapper.toDTO(listStore, listEmployee);
    }
}
