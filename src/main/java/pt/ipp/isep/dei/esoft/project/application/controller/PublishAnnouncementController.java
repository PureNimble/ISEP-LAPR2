package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The PublishAnnouncementController class is responsible for handling the logic related to publishing announcements,
 * <p>
 * such as retrieving user information, property types, available equipment, commissions, types of business, and published
 * <p>
 * announcements. It also provides methods for creating new published announcements and retrieving them from the
 * <p>
 * repository.
 */
public class PublishAnnouncementController {
    /**
     * The UserRepository instance.
     */
    private UserRepository userRepository = null;

    /**
     * The Property type repository.
     */
    PropertyTypeRepository propertyTypeRepository = null;

    /**
     * The Published announcement repository.
     */
    PublishedAnnouncementRepository publishedAnnouncementRepository = null;

    /**
     * The Comission repository.
     */
    ComissionRepository comissionRepository = null;

    /**
     * The Type of business repository.
     */
    TypeOfBusinessRepository typeOfBusinessRepository = null;

    /**
     * The Available equipment repository.
     */
    AvailableEquipmentRepository availableEquipmentRepository = null;

    /**
     * The Employee repository.
     */
    EmployeeRepository employeeRepository = null;
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = null;
    /**
     * The StateRepository instance.
     */
    private StateRepository stateRepository = null;


    /**
     * Constructor that initializes the repository variables.
     */
    public PublishAnnouncementController() {
        getUserRepository();
        getPropertyTypeRepository();
        getPublishedAnnouncementRepository();
        getStateRepository();
    }

    /**
     * Retrieves the UserRepository instance and initializes it if it is null.
     *
     * @return UserRepository instance.
     */
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }

    /**

     Initializes the StateRepository instance variable.
     @return The StateRepository object associated with this controller.
     */
    private StateRepository getStateRepository() {
        if (stateRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            stateRepository = repositories.getStateRepository();
        }
        return stateRepository;
    }

    /**
     * Retrieves the AvailableEquipmentRepository instance and initializes it if it is null.
     *
     * @return AvailableEquipmentRepository instance.
     */
    public AvailableEquipmentRepository getAvailableEquipmentRepository() {
        if (availableEquipmentRepository == null) {
            Repositories repositories = Repositories.getInstance();
            availableEquipmentRepository = repositories.getAvailableEquipmentRepository();
        }
        return availableEquipmentRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance and initializes it if it is null.
     *
     * @return AuthenticationRepository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
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
     * Retrieves an AvailableEquipment object by its description.
     *
     * @param availableEquipmentDescription Description of the AvailableEquipment object.
     * @return AvailableEquipment object.
     */
    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();

        //Get the TaskCategory by its description
        AvailableEquipment availableEquipmentByDescription =
                availableEquipmentRepository.getAvailableEquipmentByDescription(availableEquipmentDescription);

        return availableEquipmentByDescription;

    }

    /**
     * Retrieves a list of all AvailableEquipment objects.
     *
     * @return List of AvailableEquipment objects.
     */
    public List<AvailableEquipment> getAvailableEquipment() {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();
        return availableEquipmentRepository.getAvailableEquipments();
    }


    /**
     * Retrieves a User object by its email.
     *
     * @param email Email of the User object.
     * @return User object.
     */
    public Client getUserByEmail(String email) {
        UserRepository userRepository = getUserRepository();

        for (Client client : userRepository.getUsers()) {
            if (client.compare(email) == 0) {
                return client;
            }
        }
        return null;
    }

    /**
     * Retrieves the PropertyTypeRepository instance and initializes it if it is null.
     *
     * @return PropertyTypeRepository instance.
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    /**
     * Retrieves the TypeOfBusinessRepository instance and initializes it if it is null.
     *
     * @return TypeOfBusinessRepository instance.
     */
    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }

    /**
     * Retrieves a TypeOfBusiness object by its description.
     *
     * @param typeOfBusinessDescription Description of the TypeOfBusiness object.
     * @return TypeOfBusiness object.
     */
    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription) {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        TypeOfBusiness typeOfBusinessByDescription =
                typeOfBusinessRepository.getTypeOfBusinessByDescription(typeOfBusinessDescription);

        return typeOfBusinessByDescription;

    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public List<State> getState() {
        StateRepository stateRepository = getStateRepository();
        return stateRepository.getStates();
    }

    /**
     * Gets state by description.
     *
     * @param stateDescription the state description
     * @return the state by description
     */
    public State getStateByDescription(String stateDescription) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        State stateByDescription =
                stateRepository.getStateByDescription(stateDescription);
        return stateByDescription;

    }

    /**
     * Gets city by description.
     *
     * @param cityDescription the city description
     * @param district        the district
     * @return the city by description
     */
    public City getCityByDescription(String cityDescription, District district) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        City cityByDescription =
                stateRepository.getCityByDescription(cityDescription,district);
        return cityByDescription;

    }

    /**
     * Gets district by description.
     *
     * @param districtDescription the district description
     * @param state               the state
     * @return the district by description
     */
    public District getDistrictByDescription(String districtDescription,State state) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        District districtByDescription =
                stateRepository.getDistrictByDescription(districtDescription,state);
        return districtByDescription;
    }

    /**
     * Get district list.
     *
     * @param state the state
     * @return the list
     */
    public List<District> getDistrict(State state){
        return state.getDistricts();
    }

    /**
     * Get cities list.
     *
     * @param district the district
     * @return the list
     */
    public List<City> getCities(District district){
        return district.getCities();
    }

    /**
     * Returns a list of all available type of businesses.
     *
     * @return a list of TypeOfBusiness objects
     */
    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }


    /**
     * Retrieves the ComissionRepository instance and initializes it if it is null.
     *
     * @return ComissionRepository instance.
     */
    private ComissionRepository getComissionRepository() {
        if (comissionRepository == null) {
            Repositories repositories = Repositories.getInstance();
            comissionRepository = repositories.getComissionRepository();
        }
        return comissionRepository;
    }

    /**
     * Returns the Comission object with the specified description.
     *
     * @param comissionDescription the description of the Comission object to be retrieved
     * @return the Comission object with the specified description
     */
    public Comission getComissionByDescription(Double comissionDescription) {
        Comission comissionByDescription =
                getComissionRepository().getComissionByDescription(comissionDescription);

        return comissionByDescription;

    }

    /**
     * Returns a list of all available Comission objects.
     *
     * @return a list of Comission objects
     */
    public List<Comission> getComission() {
        ComissionRepository comissionRepository = getComissionRepository();
        return comissionRepository.getComission();
    }


    /**
     * Returns the PropertyType object with the specified description.
     *
     * @param propertyTypeDescription the description of the PropertyType object to be retrieved
     * @return the PropertyType object with the specified description
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();

        //Get the TaskCategory by its description
        PropertyType propertyTypeByDescription =
                propertyTypeRepository.getPropertyTypeByDescription(propertyTypeDescription);

        return propertyTypeByDescription;

    }

    /**
     * Returns a list of all available PropertyType objects.
     *
     * @return a list of PropertyType objects
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    /**
     * Retrieves the PublishedAnnoucementRepository instance and initializes it if it is null.
     *
     * @return PublishedAnnoucementRepository instance.
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
     * Returns a list of all available PublishedAnnouncement objects.
     *
     * @return a list of PublishedAnnouncement objects
     */
    public List<PublishedAnnouncement> getPublishedAnnoucement() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getPublishedAnnouncements();
    }

    /**
     * Gets published announcements desc.
     *
     * @return the published announcements desc
     */
    public List<PublishedAnnouncement> getAvailablePublishedAnnouncementsDesc() {
        PublishedAnnouncementRepository publishedAnnouncementRepository = getPublishedAnnouncementRepository();
        return publishedAnnouncementRepository.getAvailablePublishedAnnouncementsDesc();
    }

    /**
     * Returns a new Business object with the specified price description.
     *
     * @param priceDescription the description of the price for the new Business
     * @return a new Business object with the specified price description
     */
    public Business getBusinessByDescription(double priceDescription) {

        Business priceByDescription = new Business(priceDescription);

        return priceByDescription;

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
     * Returns a list of all agents.
     *
     * @return A List of Employee objects representing agents.
     */
    public List<Employee> getListAgents() {

        List<Employee> listAgents = new ArrayList<>();

        for (Employee employee: getEmployeeRepository().getEmployees()) {

            for (Role roles: employee.getRoles()) {
                if (roles.equals(new Role("Agent"))){
                    listAgents.add(employee);
                }
            }
        }
        return listAgents;
    }

    /**
     * Get agent by description employee.
     *
     * @param agentDescription the agent description
     * @return the employee
     */
    public Employee getAgentByDescription(String agentDescription){
        EmployeeRepository employeeRepository = getEmployeeRepository();

        return employeeRepository.getEmployeeByEmail(agentDescription);
    }


    /**
     * Creates a new published announcement for a house with the specified parameters,
     * and returns an Optional containing the new published announcement.
     * If a published announcement with the same parameters already exists,
     * an empty Optional is returned instead.
     *
     * @param date               the date of the new published announcement
     * @param typeOfBusiness     the type of business for the new published announcement
     * @param property           the property for the new published announcement
     * @param propertyType       the property type for the new published announcement
     * @param comission          the commission for the new published announcement
     * @param business           the business for the new published announcement
     * @param durationOfContract the duration of the contract for the new published announcement
     * @param agent              the agent
     * @param client             the client
     * @param propertyID         the property id
     * @param state              the state
     * @param store              the store
     * @return an Optional containing the new published announcement if it was created successfully, or an empty Optional if a published announcement with the same parameters already exists
     */
    public Optional<PublishedAnnouncement> createPublishmentAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract, Employee agent, Client client, int propertyID, AnnouncementState state, Store store) {

        Optional<PublishedAnnouncement> newPublishedAnnouncement = Optional.empty();

        PublishedAnnouncement publishedAnnouncement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, agent, client,propertyID, state, store);

        if (!getPublishedAnnouncementRepository().getPublishedAnnouncements().contains(publishedAnnouncement)) {
            newPublishedAnnouncement = getPublishedAnnouncementRepository().publishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract, agent, client, propertyID, state, store);
        }
        return newPublishedAnnouncement;
    }


}
