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

    /**

     Constructor for AnnouncementRequestController that initializes the required repositories.
     */
    public AnnouncementRequestController() {
        getUserRepository();
        getPropertyTypeRepository();
        getAnnouncementRequestRepository();
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


    public List<AnnouncementRequest> getAnnouncementRequest() {
        AnnouncementRequestRepository announcementRequestRepository = getAnnouncementRequestRepository();
        return announcementRequestRepository.getAnnouncementsRequest();
    }

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

    public AvailableEquipment getAvailableEquipmentByDescription(String availableEquipmentDescription) {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();

        //Get the TaskCategory by its description
        AvailableEquipment availableEquipmentByDescription =
                availableEquipmentRepository.getAvailableEquipmentByDescription(availableEquipmentDescription);

        return availableEquipmentByDescription;

    }

    public List<AvailableEquipment> getAvailableEquipment() {
        AvailableEquipmentRepository availableEquipmentRepository = getAvailableEquipmentRepository();
        return availableEquipmentRepository.getAvailableEquipments();
    }

    public Optional<AnnouncementRequest> createAnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract) {

        Optional<AnnouncementRequest> newAnnoucementRequest = Optional.empty();

        AnnouncementRequest announcementRequest = new AnnouncementRequest(date, typeOfBusiness, property, propertyType, business, durationOfContract);

        if (!getAnnouncementRequestRepository().getAnnouncementsRequest().contains(announcementRequest)) {
            newAnnoucementRequest = getAnnouncementRequestRepository().announcementRequest(date, typeOfBusiness, property, propertyType, business, durationOfContract);
        }
        return newAnnoucementRequest;
    }


}
