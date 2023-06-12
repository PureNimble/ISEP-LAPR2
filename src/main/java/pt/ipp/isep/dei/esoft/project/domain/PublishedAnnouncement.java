package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * The type Published announcement.
 */
public class PublishedAnnouncement {
    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Comission comission;
    private Business business;

    private int durationOfContract;

    private Employee agent;

    private Client client;

    private int propertyID;

    private AnnouncementState state;

    private Store store;

    /**
     * Instantiates a new Published announcement.
     *
     * @param date           the date
     * @param typeOfBusiness the type of business
     * @param property       the property
     * @param propertyType   the property type
     * @param comission      the comission
     * @param business       the business
     * @param agent          the agent
     * @param client         the client
     * @param propertyID     the property id
     * @param state          the state
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, Employee agent, Client client, int propertyID, AnnouncementState state, Store store) {
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.date = date;
        this.business = business;
        this.agent = agent;
        this.client = client;
        this.propertyID = propertyID;
        this.state = state;
        this.store = store;
    }

    /**
     * Instantiates a new Published announcement.
     *
     * @param date               the date
     * @param typeOfBusiness     the type of business
     * @param property           the property
     * @param propertyType       the property type
     * @param comission          the comission
     * @param business           the business
     * @param durationOfContract the duration of contract
     * @param agent              the agent
     * @param client             the client
     * @param propertyID         the property id
     * @param state              the state
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract, Employee agent, Client client, int propertyID, AnnouncementState state, Store store) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.business = business;
        this.durationOfContract = durationOfContract;
        this.agent = agent;
        this.client = client;
        this.propertyID = propertyID;
        this.state = state;
        this.store = store;
    }

    /**
     * Instantiates a new Published announcement.
     *
     * @param announcementRequestDto the announcement request dto
     * @param comission              the comission
     */
    public PublishedAnnouncement(AnnouncementRequestDto announcementRequestDto, Comission comission) {
        this.date = announcementRequestDto.getDate();
        this.agent = announcementRequestDto.getAgent();
        this.typeOfBusiness = announcementRequestDto.getTypeOfBusiness();
        this.property = announcementRequestDto.getProperty();
        this.propertyType = announcementRequestDto.getPropertyType();
        this.business = announcementRequestDto.getBusiness();
        this.durationOfContract = announcementRequestDto.getDurationOfContract();
        this.comission = comission;
        
    }


    /**
     * Gets property.
     *
     * @return the property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return property.getAddress().getState().toString();
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return property.getAddress().getCity().toString();
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets client.
     *
     * @param client the client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Gets announcement state.
     *
     * @return the announcement state
     */
    public AnnouncementState getAnnouncementState() {
        return state;
    }

    /**
     * Sets announcement state.
     *
     * @param state the state
     */
    public void setAnnouncementState(AnnouncementState state) {
        this.state = state;
    }

    /**
     * Gets type of business.
     *
     * @return the type of business
     */
    public TypeOfBusiness getTypeOfBusiness() {
        return typeOfBusiness;
    }

    /**
     * Gets property type.
     *
     * @return the property type
     */
    public PropertyType getPropertyType() {
        return propertyType;
    }

    /**
     * Gets comission.
     *
     * @return the comission
     */
    public Comission getComission() {
        return comission;
    }

    /**
     * Gets business.
     *
     * @return the business
     */
    public Business getBusiness() {
        return business;
    }

    /**
     * Gets duration of contract.
     *
     * @return the duration of contract
     */
    public int getDurationOfContract() {
        return durationOfContract;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets type of business.
     *
     * @param typeOfBusiness the type of business
     */
    public void setTypeOfBusiness(TypeOfBusiness typeOfBusiness) {
        this.typeOfBusiness = typeOfBusiness;
    }

    /**
     * Sets property.
     *
     * @param property the property
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Sets property type.
     *
     * @param propertyType the property type
     */
    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * Sets comission.
     *
     * @param comission the comission
     */
    public void setComission(Comission comission) {
        this.comission = comission;
    }

    /**
     * Sets business.
     *
     * @param business the business
     */
    public void setBusiness(Business business) {
        this.business = business;
    }

    /**
     * Sets duration of contract.
     *
     * @param durationOfContract the duration of contract
     */
    public void setDurationOfContract(int durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    /**
     * Gets agent.
     *
     * @return the agent
     */
    public Employee getAgent() {
        return agent;
    }

    /**
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(Employee agent) {
        this.agent = agent;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Gets property id.
     *
     * @return the property id
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * Sets property id.
     *
     * @param propertyID the property id
     */
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String toString() {
        return String.format("Date: %s\n\n" + "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Client: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Property ID: %s\n" +
                        "Type Of Business: %s\n" +
                        "Property Type: %s\n" +
                        "Comission Selected: %s" +
                        "Price: %s\n" +
                        "%s\n",
                        
                date.toString(), agent.getEmployeeName(), agent.getEmployeeEmail(), agent.getPhoneNumber(), client.getName(), client.getEmail(), client.getPhoneNumber(), propertyID, typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), property.toString());
    }

    /**
     * To string rent string.
     *
     * @return the string
     */
    public String toStringRent() {
        return String.format("Date: %s\n\n" +  "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                "Client: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Property ID: %s\n" +
                        "Type Of Business: %s\n" +
                        "Property Type: %s\n" +
                        "Comission Selected: %s" +
                        "Price: %s\n" +
                        "Duration Of The Contract: %s\n" +
                        "%s\n",
                        
                date.toString(), agent.getEmployeeName(),
                agent.getEmployeeEmail(), agent.getPhoneNumber(),
                client.getName(), client.getEmail(), client.getPhoneNumber(),
                propertyID, typeOfBusiness.toString(),
                propertyType, comission.toString(), business.toString(), durationOfContract, property.toString());
    }

}
