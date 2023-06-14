package pt.ipp.isep.dei.esoft.project.repository;


import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.io.*;

/**
 * The Repositories class represents a singleton instance that manages all the repositories.
 * This class contains methods to retrieve instances of each repository.
 */
public class Repositories implements Serializable {

    /**
     * The instance of the Repositories class.
     */
    private static final Repositories instance = new Repositories();
    /**
     * The RoleRepository instance.
     */
    private RoleRepository roleRepository = new RoleRepository();
    /**
     * The AuthenticationRepository instance.
     */
    private AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    /**
     * The StoreRepository instance.
     */
    private StoreRepository storeRepository = new StoreRepository();
    /**
     * The ComissionRepository instance.
     */
    private ComissionRepository comissionRepository = new ComissionRepository();
    /**
     * The StateRepository instance.
     */
    private StateRepository stateRepository = new StateRepository();
    /**
     * The EmployeeRepository instance.
     */
    private EmployeeRepository employeeRepository = new EmployeeRepository();
    /**
     * The UserRepository instance.
     */
    private UserRepository userRepository = new UserRepository();
    /**
     * The PropertyTypeRepository instance.
     */
    private PropertyTypeRepository propertyTypeRepository = new PropertyTypeRepository();
    /**
     * The PublishedAnnouncementRepository instance.
     */
    private PublishedAnnouncementRepository publishedAnnouncementRepository = new PublishedAnnouncementRepository();
    /**
     * The TypeOfBusinessRepository instance.
     */
    private TypeOfBusinessRepository typeOfBusinessRepository = new TypeOfBusinessRepository();
    /**
     * The AnnouncementRequestRepository instance.
     */
    private AnnouncementRequestRepository announcementRequestRepository = new AnnouncementRequestRepository();
    /**
     * The AvailableEquipmentRepository instance.
     */
    private AvailableEquipmentRepository availableEquipmentRepository = new AvailableEquipmentRepository();

    private MessageRepository messageRepository = new MessageRepository();

    private OfferRepository offerRepository = new OfferRepository();

    private static final String SERIALIZATION_FILE_NAME = "RealEstateUSAAPP.ser";


    /**
     * Private constructor to prevent instantiation from outside the class.
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

    /**
     * Gets offer repository.
     *
     * @return the offer repository
     */
    public OfferRepository getOfferRepository() {
        return offerRepository;
    }

    /**
     * Sets offer repository.
     *
     * @param offerRepository the offer repository
     */
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    public void serialize() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(SERIALIZATION_FILE_NAME);
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(typeOfBusinessRepository);
        out.writeObject(roleRepository);
        out.writeObject(stateRepository);
        out.writeObject(employeeRepository);
        out.writeObject(messageRepository);
        out.writeObject(offerRepository);
        out.writeObject(comissionRepository);
        out.writeObject(userRepository);
        out.writeObject(propertyTypeRepository);
        out.writeObject(announcementRequestRepository);
        out.writeObject(availableEquipmentRepository);
        for (Store store : storeRepository.getStores()) {
            out.writeObject(store);
        }


        out.close();
        fileOutputStream.close();


    }


    public void deserialize() {
        try {

            FileInputStream fileInputStream = new FileInputStream(SERIALIZATION_FILE_NAME);
           /* ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(publishedAnnouncementRepository);
            out.writeObject(employeeRepository);
            out.writeObject(storeRepository);
            out.writeObject(messageRepository);
            out.writeObject(offerRepository);
            out.writeObject(typeOfBusinessRepository);
            out.writeObject(roleRepository);
            out.writeObject(authenticationRepository);
            out.writeObject(comissionRepository);
            out.writeObject(stateRepository);
            out.writeObject(userRepository);
            out.writeObject(propertyTypeRepository);
            out.writeObject(announcementRequestRepository);
            out.writeObject(availableEquipmentRepository);

            out.close();
            fileOutputStream.close();*/

        } catch (Exception e) {
            System.out.println("Serialization Error");
        }
    }


}
