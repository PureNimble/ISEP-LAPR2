package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**

 The AnnouncementRequestController class is responsible for managing announcement requests.

 It interacts with the UserRepository, PropertyTypeRepository, AnnouncementRequestRepository,

 EmployeeRepository, TypeOfBusinessRepository, and AvailableEquipmentRepository to perform its operations.
 */
public class AnnouncementRequestController {

    private UserRepository userRepository = null;
    PropertyTypeRepository propertyTypeRepository = null;
    AnnouncementRequestRepository announcementRequestRepository = null;
    EmployeeRepository employeeRepository = null;
    TypeOfBusinessRepository typeOfBusinessRepository = null;
    AvailableEquipmentRepository availableEquipmentRepository = null;
    private StateRepository stateRepository = null;

    /**

     Constructor for AnnouncementRequestController that initializes the required repositories.
     */
    public AnnouncementRequestController() {
        getUserRepository();
        getPropertyTypeRepository();
        getAnnouncementRequestRepository();
        getStateRepository();
    }
    /**

     Method to get the UserRepository instance.
     @return UserRepository instance
     */
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }
    /**

     Method to get the PropertyTypeRepository instance.
     @return PropertyTypeRepository instance
     */
    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
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

     Method to get a PropertyType instance by its description.
     @param propertyTypeDescription The description of the property type.
     @return PropertyType instance
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        PropertyType propertyTypeByDescription = getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);
        return propertyTypeByDescription;
    }
    /**

     Method to get a list of all PropertyType instances.
     @return List of PropertyType instances
     */
    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }
    /**

     Method to get the TypeOfBusinessRepository instance.
     @return TypeOfBusinessRepository instance
     */
    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }
    /**

     Method to get a TypeOfBusiness instance by its description.
     @param typeOfBusinessDescription The description of the type of business.
     @return TypeOfBusiness instance
     */
    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription) {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        TypeOfBusiness typeOfBusinessByDescription = typeOfBusinessRepository.getTypeOfBusinessByDescription(typeOfBusinessDescription);
        return typeOfBusinessByDescription;
    }
    /**

     Method to get a list of all TypeOfBusiness instances.
     @return List of TypeOfBusiness instances
     */
    public List<TypeOfBusiness> getTypeOfBusiness() {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();
        return typeOfBusinessRepository.getTypeOfBusinesses();
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

     Returns a list of all agents.

     @return A List of Employee objects representing agents.
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

    public List<State> getState() {
        StateRepository stateRepository = getStateRepository();
        return stateRepository.getStates();
    }

    public State getStateByDescription(String stateDescription) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        State stateByDescription =
                stateRepository.getStateByDescription(stateDescription);
        return stateByDescription;

    }

    public City getCityByDescription(String cityDescription, District district) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        City cityByDescription =
                stateRepository.getCityByDescription(cityDescription,district);
        return cityByDescription;

    }

    public District getDistrictByDescription(String districtDescription,State state) {

        StateRepository stateRepository = getStateRepository();

        //Get the TaskCategory by its description
        District districtByDescription =
                stateRepository.getDistrictByDescription(districtDescription,state);
        return districtByDescription;
    }

    public List<District> getDistrict(State state){
        return state.getDistricts();
    }

    public List<City> getCities(District district){
        return district.getCities();
    }

    /**

     Returns a list of all Announcement Requests.
     @return A List of AnnouncementRequest objects.
     */
    public List<AnnouncementRequest> getAnnouncementRequest() {
        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();
        return announcementRequestRepository.getAnnouncementsRequest();
    }
    /**

     Returns a Business object by its description.

     @param priceDescription The price description of the Business object.

     @return The Business object with the specified price description.
     */
    public Business getBusinessByDescription(double priceDescription){

        Business priceByDescription = new Business(priceDescription);

        return priceByDescription;

    }
    /**

     Returns the AvailableEquipmentRepository instance, creating it if necessary.

     @return The AvailableEquipmentRepository instance.
     */
    private AvailableEquipmentRepository getAvailableEquipmentRepository() {
        if (availableEquipmentRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            availableEquipmentRepository = repositories.getAvailableEquipmentRepository();
        }
        return availableEquipmentRepository;
    }
    /**

     Returns an AvailableEquipment object by its description.

     @param availableEquipmentDescription The description of the AvailableEquipment object.

     @return The AvailableEquipment object with the specified description.
     */
    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();

        //Get the TaskCategory by its description
        AvailableEquipment availableEquipmentByDescription =
                availableEquipmentRepository.getAvailableEquipmentByDescription(availableEquipmentDescription);

        return availableEquipmentByDescription;

    }
    /**

     Returns a list of all Available Equipment objects.
     @return A List of AvailableEquipment objects.
     */
    public List<AvailableEquipment> getAvailableEquipment() {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();
        return availableEquipmentRepository.getAvailableEquipments();
    }
    /**

     Creates a new announcement request with the given parameters.
     @param date The date of the announcement request.
     @param typeOfBusiness The type of business for the announcement request.
     @param property The property for the announcement request.
     @param propertyType The property type for the announcement request.
     @param business The business for the announcement request.
     @param durationOfContract The duration of the contract for the announcement request.
     @return An optional containing the newly created announcement request if it was created successfully, or an empty optional if the announcement request already exists in the repository.
     */
    public Optional<AnnouncementRequest> createAnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract,Employee agent) {

        Optional<AnnouncementRequest> newAnnoucementRequest = Optional.empty();

        AnnouncementRequest announcementRequest = new AnnouncementRequest("",date, typeOfBusiness, property, propertyType, business, durationOfContract,agent);

        if (!getAnnouncementRequestRepository().getAnnouncementsRequest().contains(announcementRequest)) {
            newAnnoucementRequest = getAnnouncementRequestRepository().announcementRequest(date, typeOfBusiness, property, propertyType, business, durationOfContract,agent);
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
