package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AnnouncementRequestController {

    private UserRepository userRepository = null;

    PropertyTypeRepository propertyTypeRepository = null;

    AnnouncementRequestRepository announcementRequestRepository = null;

    EmployeeRepository employeeRepository = null;

    TypeOfBusinessRepository typeOfBusinessRepository = null;

    AvailableEquipmentRepository availableEquipmentRepository = null;

    public AnnouncementRequestController() {
        getUserRepository();
        getPropertyTypeRepository();
        getAnnouncementRequestRepository();
    }

    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }


    private PropertyTypeRepository getPropertyTypeRepository() {
        if (propertyTypeRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            propertyTypeRepository = repositories.getPropertyTypeRepository();
        }
        return propertyTypeRepository;
    }


    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();

        //Get the TaskCategory by its description
        PropertyType propertyTypeByDescription =
                getPropertyTypeRepository().getPropertyTypeByDescription(propertyTypeDescription);

        return propertyTypeByDescription;

    }

    public List<PropertyType> getPropertyType() {
        PropertyTypeRepository propertyTypeRepository = getPropertyTypeRepository();
        return propertyTypeRepository.getPropertyTypes();
    }

    private TypeOfBusinessRepository getTypeOfBusinessRepository() {
        if (typeOfBusinessRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the TaskCategoryRepository
            typeOfBusinessRepository = repositories.getTypeOfBusinessRepository();
        }
        return typeOfBusinessRepository;
    }

    public TypeOfBusiness getTypeOfBusinessByDescription(String typeOfBusinessDescription) {
        TypeOfBusinessRepository typeOfBusinessRepository = getTypeOfBusinessRepository();

        //Get the TaskCategory by its description
        TypeOfBusiness typeOfBusinessByDescription =
                typeOfBusinessRepository.getTypeOfBusinessByDescription(typeOfBusinessDescription);

        return typeOfBusinessByDescription;

    }

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
