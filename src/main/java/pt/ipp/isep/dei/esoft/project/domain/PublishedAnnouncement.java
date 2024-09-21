package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * The type Published announcement.
 */
public class PublishedAnnouncement implements Serializable {
    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Comission comission;
    private Business business;

    private int durationOfContract;

    private Employee agent;

    private Client client;

    private AnnouncementState announcementState = AnnouncementState.available;

    private Store store;

    /**
     * Instantiates a new Published announcement.
     *
     * @param date              the date
     * @param typeOfBusiness    the type of business
     * @param property          the property
     * @param propertyType      the property type
     * @param comission         the comission
     * @param business          the business
     * @param agent             the agent
     * @param client            the client
     * @param announcementState the announcement state
     * @param store             the store
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, Employee agent, Client client, AnnouncementState announcementState, Store store) {
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.date = date;
        this.business = new Business((business.getPrice() * (comission.getComission()/100))+business.getPrice());
        this.agent = agent;
        this.client = client;
        this.announcementState = announcementState;
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
     * @param announcementState  the announcement state
     * @param store              the store
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract, Employee agent, Client client, AnnouncementState announcementState, Store store) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.business = new Business(business.getPrice() * (comission.getComission()/100));
        this.durationOfContract = durationOfContract;
        this.agent = agent;
        this.client = client;
        this.announcementState = announcementState;
        this.store = store;
    }

    /**
     * Instantiates a new Published announcement.
     *
     * @param announcementRequestDto the announcement request dto
     * @param comission              the comission
     * @param store                  the store
     * @param announcementState  the announcement state
     */
    public PublishedAnnouncement(AnnouncementRequestDto announcementRequestDto, Comission comission, Store store, AnnouncementState announcementState) {
        this.date = announcementRequestDto.getDate();
        this.agent = announcementRequestDto.getAgent();
        this.typeOfBusiness = announcementRequestDto.getTypeOfBusiness();
        this.property = announcementRequestDto.getProperty();
        this.propertyType = announcementRequestDto.getPropertyType();
        this.comission = comission;
        this.business = new Business(announcementRequestDto.getBusiness().getPrice()*(comission.getComission()/100));
        this.durationOfContract = announcementRequestDto.getDurationOfContract();
        this.client = announcementRequestDto.getClient();
        this.announcementState = announcementState;
        this.store = store;

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
        return announcementState;
    }

    /**
     * Sets announcement state.
     *
     * @param announcementState the state
     */
    public void setAnnouncementState(AnnouncementState announcementState) {
        this.announcementState = announcementState;
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

    /**
     * Gets store.
     *
     * @return the store
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets store.
     *
     * @param store the store
     */
    public void setStore(Store store) {
        this.store = store;
    }

    public String toString() {
        if (!typeOfBusiness.getTypeOfBusiness().equals("Rent") && !typeOfBusiness.getTypeOfBusiness().equals("rent") ){
            return String.format("Date: %s\n\n" + "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                    "Client: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                    "Type Of Business: %s\n" +
                    "Property Type: %s\n" +
                    "Price: %s\n" +
                    "%s\n",date.toString(), agent.getEmployeeName(), agent.getEmployeeEmail(), agent.getPhoneNumber(), client.getName(), client.getEmail(), client.getPhoneNumber(),
                    typeOfBusiness.toString(), propertyType, business.toString(), property.toString());
        }else {
            return String.format("Date: %s\n\n" +  "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                            "Client: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                            "Type Of Business: %s\n" +
                            "Property Type: %s\n" +
                            "Price: %s\n" +
                            "Duration Of The Contract: %s\n" +
                            "%s\n",

                    date.toString(), agent.getEmployeeName(),
                    agent.getEmployeeEmail(), agent.getPhoneNumber(),
                    client.getName(), client.getEmail(), client.getPhoneNumber(),
                    typeOfBusiness.toString(),
                    propertyType,business.toString(), durationOfContract, property.toString());
        }

                        

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishedAnnouncement that = (PublishedAnnouncement) o;
        return durationOfContract == that.durationOfContract && date.equals(that.date) && typeOfBusiness.equals(that.typeOfBusiness) && property.equals(that.property) && propertyType.equals(that.propertyType) && comission.equals(that.comission) && business.equals(that.business) && agent.equals(that.agent) && client.equals(that.client) && announcementState == that.announcementState && store.equals(that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract, agent, client, announcementState, store);
    }
}
