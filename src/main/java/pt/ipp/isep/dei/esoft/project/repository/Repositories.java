package pt.ipp.isep.dei.esoft.project.repository;


/**
 * The Repositories class represents a singleton instance that manages all the repositories.
 * This class contains methods to retrieve instances of each repository.
 */
public class Repositories {

    /**

     The instance of the Repositories class.
     */
    private static final Repositories instance = new Repositories();
    /**

     The RoleRepository instance.
     */
    private RoleRepository roleRepository = new RoleRepository();
    /**

     The AuthenticationRepository instance.
     */
    private AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**

     The StoreRepository instance.
     */
    private StoreRepository storeRepository = new StoreRepository();
    /**

     The ComissionRepository instance.
     */
    private ComissionRepository comissionRepository = new ComissionRepository();
    /**

     The StateRepository instance.
     */
    private StateRepository stateRepository = new StateRepository();
    /**

     The EmployeeRepository instance.
     */
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    /**

     The UserRepository instance.
     */
    private UserRepository userRepository = new UserRepository();
    /**

     The PropertyTypeRepository instance.
     */
    private PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
    /**

     The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
    /**

     The TypeOfBusinessRepository instance.
     */
    private TypeOfBusinessRepository typeOfBusinessRepository = new TypeOfBusinessRepository();
    /**

     The AnnouncementRequestRepository instance.
     */
    private AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
    /**

     The AvailableEquipmentRepository instance.
     */
    private AvailableEquipmentRepository availableEquipmentRepository = new AvailableEquipmentRepository();

    private MessageRepository messageRepository = new MessageRepository();

    private OfferRepository offerRepository = new OfferRepository();
    /**

     Private constructor to prevent instantiation from outside the class.
     */
    private Repositories() {
    }

    /**
     * Returns the instance of the Repositories class.
     *
     * @return the instance of the Repositories class.
     */
    public static Repositories getInstance() {
        return instance;
    }

    /**
     * Returns the RoleRepository instance.
     *
     * @return the RoleRepository instance.
     */
    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    /**
     * Returns the AuthenticationRepository instance.
     *
     * @return the AuthenticationRepository instance.
     */
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    /**
     * Returns the StoreRepository instance.
     *
     * @return the StoreRepository instance.
     */
    public StoreRepository getStoreRepository() {
        return storeRepository;
    }

    /**
     * Returns the StateRepository instance.
     *
     * @return the StateRepository instance.
     */
    public StateRepository getStateRepository() {
        return stateRepository;
    }

    /**
     * Returns the EmployeeRepository instance.
     *
     * @return the EmployeeRepository instance.
     */
    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    /**
     * Returns the UserRepository instance.
     *
     * @return the UserRepository instance.
     */
    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Returns the PropertyTypeRepository instance.
     *
     * @return the PropertyTypeRepository instance.
     */
    public PropertyTypeRepository getPropertyTypeRepository() {
        return propertyTypeRepository;
    }

    /**
     * Returns the PublishedAnnouncementRepository instance.
     *
     * @return the PublishedAnnouncementRepository instance.
     */
    public PublishedAnnouncementRepository getPublishedAnnouncementRepository() {
        return publishedAnnouncementRepository;
    }

    /**
     * Returns the TypeOfBusinessRepository instance.
     *
     * @return the TypeOfBusinessRepository instance.
     */
    public TypeOfBusinessRepository getTypeOfBusinessRepository() {
        return typeOfBusinessRepository;
    }

    /**
     * Returns the AnnouncementRequestRepository instance.
     *
     * @return the AnnouncementRequestRepository instance.
     */
    public AnnouncementRequestRepository getAnnouncementRequestRepository() {
        return announcementRequestRepository;
    }

    /**
     * Returns the ComissionRepository instance.
     *
     * @return the ComissionRepository instance.
     */
    public ComissionRepository getComissionRepository() {
        return comissionRepository;
    }

    /**
     * Returns the AvailableEquipmentRepository instance.
     *
     * @return the AvailableEquipmentRepository instance.
     */
    public AvailableEquipmentRepository getAvailableEquipmentRepository() {
        return availableEquipmentRepository;
    }

    /**
     * Gets message repository.
     *
     * @return the message repository
     */
    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    /**
     * Sets message repository.
     *
     * @param messageRepository the message repository
     */
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public OfferRepository getOfferRepository() {
        return offerRepository;
    }

    public void  setOfferRepository (OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }



}
