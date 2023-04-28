package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static final Repositories instance = new Repositories();
    RoleRepository roleRepository = new RoleRepository();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    StoreRepository storeRepository = new StoreRepository();

    ComissionRepository comissionRepository = new ComissionRepository();
    StateRepository stateRepository = new StateRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    UserRepository userRepository = new UserRepository();
    PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
    PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
    TypeOfBusinessRepository typeOfBusinessRepository = new TypeOfBusinessRepository();
    AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();

    AvailableEquipmentRepository availableEquipmentRepository = new AvailableEquipmentRepository();
    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }


    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public StoreRepository getStoreRepository() {
        return storeRepository;
    }

    public StateRepository getStateRepository() {
        return stateRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public PropertyTypeRepository getPropertyTypeRepository() {
        return propertyTypeRepository;
    }

    public PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        return publishedAnnouncementRepository;
    }

    public TypeOfBusinessRepository getTypeOfBusinessRepository() {
        return typeOfBusinessRepository;
    }

    public AnnouncementRequestRepository getAnnouncementRequestRepository() {
        return announcementRequestRepository;
    }

    public ComissionRepository getComissionRepository() {
        return comissionRepository;
    }

    public AvailableEquipmentRepository getAvailableEquipmentRepository() {
        return availableEquipmentRepository;
    }
}
