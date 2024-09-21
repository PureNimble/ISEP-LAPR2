package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Announcement request controller.
 */
public class AnnouncementRequestController {

    private UserRepository userRepository = null;
    /**
     * The Property type repository.
     */
    PropertyTypeRepository propertyTypeRepository = null;
    /**
     * The Announcement request repository.
     */
    AnnouncementRequestRepository announcementRequestRepository = null;
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = null;
    /**
     * The Employee repository.
     */
    EmployeeRepository employeeRepository = null;
    /**
     * The Type of business repository.
     */
    TypeOfBusinessRepository typeOfBusinessRepository = null;
    /**
     * The Available equipment repository.
     */
    AvailableEquipmentRepository availableEquipmentRepository = null;
    private StateRepository stateRepository = null;

    /**
     * Instantiates a new Announcement request controller.
     */
    public AnnouncementRequestController() {
        getUserRepository();
        getPropertyTypeRepository();
        getAuthenticationRepository();
        getAnnouncementRequestRepository();
        getStateRepository();
    }
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }

    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private StateRepository getStateRepository() {
        if (stateRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the AuthenticationRepository
            stateRepository = repositories.getStateRepository();
        }
        return stateRepository;
    }

    /**
     * Gets property type by description.
     *
     * @param propertyTypeDescription the property type description
     * @return the property type by description
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        PropertyType propertyTypeByDescription = getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);
        return propertyTypeByDescription;
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }
    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }

    /**
     * Gets type of business by description.
     *
     * @param typeOfBusinessDescription the type of business description
     * @return the type of business by description
     */
    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription) {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        TypeOfBusiness typeOfBusinessByDescription = typeOfBusinessRepository.getTypeOfBusinessByDescription(typeOfBusinessDescription);
        return typeOfBusinessByDescription;
    }

    /**
     * Gets type of business.
     *
     * @return the type of business
     */
    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
    }
    private AnnouncementRequestRepository getAnnouncementRequestRepository() {
        if (announcementRequestRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            announcementRequestRepository = repositories.getAnnouncementRequestRepository();
        }
        return announcementRequestRepository;
    }
    private EmployeeRepository getEmployeeRepository() {
        if (employeeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            employeeRepository = repositories.getEmployeeRepository();
        }
        return employeeRepository;
    }

    /**
     * Gets list agents.
     *
     * @return the list agents
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
     * Gets current session email.
     *
     * @return the current session email
     */
    public String getCurrentSessionEmail() {
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * Gets client email.
     *
     * @return the client email
     */
    public Client getClientEmail() {
        String email = getCurrentSessionEmail();
        return getUserRepository().getClientEmail(email);
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
     * Gets announcement request.
     *
     * @return the announcement request
     */
    public List<AnnouncementRequest> getAnnouncementRequest() {
        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();
        return announcementRequestRepository.getAnnouncementsRequest();
    }

    /**
     * Get business by description business.
     *
     * @param priceDescription the price description
     * @return the business
     */
    public Business getBusinessByDescription(double priceDescription){

        Business priceByDescription = new Business(priceDescription);

        return priceByDescription;

    }
    private AvailableEquipmentRepository getAvailableEquipmentRepository() {
        if (availableEquipmentRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            availableEquipmentRepository = repositories.getAvailableEquipmentRepository();
        }
        return availableEquipmentRepository;
    }

    /**
     * Gets available equipment by description.
     *
     * @param availableEquipmentDescription the available equipment description
     * @return the available equipment by description
     */
    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();

        //Get the TaskCategory by its description
        AvailableEquipment availableEquipmentByDescription =
                availableEquipmentRepository.getAvailableEquipmentByDescription(availableEquipmentDescription);

        return availableEquipmentByDescription;

    }

    /**
     * Gets available equipment.
     *
     * @return the available equipment
     */
    public List<AvailableEquipment> getAvailableEquipment() {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();
        return availableEquipmentRepository.getAvailableEquipments();
    }

    /**
     * Create announcement request optional.
     *
     * @param date               the date
     * @param typeOfBusiness     the type of business
     * @param property           the property
     * @param propertyType       the property type
     * @param business           the business
     * @param durationOfContract the duration of contract
     * @param agent              the agent
     * @param client             the client
     * @return the optional
     */
    public Optional<AnnouncementRequest> createAnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract,Employee agent, Client client) {

        Optional<AnnouncementRequest> newAnnoucementRequest = Optional.empty();

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, property, propertyType, business, durationOfContract,agent, client);

        if (!getAnnouncementRequestRepository().getAnnouncementsRequest().contains(announcementRequest)) {
            newAnnoucementRequest = getAnnouncementRequestRepository().announcementRequest(date, typeOfBusiness, property, propertyType, business, durationOfContract,agent, client);
        }
        return newAnnoucementRequest;
    }


    /**
     * Get agent by description employee.
     *
     * @param agentDescription the agent description
     * @return the employee
     */
    public Employee getAgentByDescription(String agentDescription){
        EmployeeRepository employeeRepository = getEmployeeRepository();

        return employeeRepository.getEmployeeByString(agentDescription);
    }


}
