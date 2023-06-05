package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**
 * Represents a real estate announcement that has been published.
 */
public class PublishedAnnouncement {

    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Comission comission;
    private Business business;
    private int durationOfContract;
    private Residence residence;
    private Employee agent;

    private AnnouncementRequest announcementRequest;
    private Address address;

    /**
     * Constructs a new PublishedAnnouncement object for sale.
     *
     * @param date           the date the announcement was published
     * @param typeOfBusiness the type of business (buying or selling)
     * @param property       the property being advertised
     * @param propertyType   the type of property
     * @param comission      the commission chosen by the client
     * @param business       the business representing the client
     * @param agent          the agent
     * @param address        the address
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, Employee agent, Address address) {
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.date = date;
        this.business = business;
        this.agent = agent;
        this.address = address;
    }

    /**
     * Constructs a new PublishedAnnouncement object for rent.
     *
     * @param date               the date the announcement was published
     * @param typeOfBusiness     the type of business (renting)
     * @param property           the property being advertised
     * @param propertyType       the type of property
     * @param comission          the commission chosen by the client
     * @param business           the business representing the client
     * @param durationOfContract the duration of the rental contract
     * @param agent              the agent
     * @param address        the address
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract, Employee agent, Address address) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.business = business;
        this.durationOfContract = durationOfContract;
        this.agent = agent;
        this.address = address;
    }

    /**
     * Instantiates a new Published announcement.
     *
     * @param comission           the comission
     * @param announcementRequest the announcement request
     */
    public PublishedAnnouncement(Comission comission, AnnouncementRequest announcementRequest) {
        this.comission = comission;
        this.announcementRequest = announcementRequest;
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
        this.address = announcementRequestDto.getAddress();
    }

    /**
     * Gets announcement request.
     *
     * @return the announcement request
     */
    public AnnouncementRequest getAnnouncementRequest() {
        return announcementRequest;
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
    
    public Address getAddress() {
        return address;
    }
    
    public Residence getResidence() {
        return residence;
    }

    public String getState() {
        return address.getState().toString();
    }

    public String getCity() {
        return address.getCity().toString();
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    /**
     * Sets announcement request.
     *
     * @param announcementRequest the announcement request
     */
    public void setAnnouncementRequest(AnnouncementRequest announcementRequest) {
        this.announcementRequest = announcementRequest;
    }

    /**
     * Returns a string representation of the announcement for sale.
     *
     * @return a string representation of the announcement for sale
     */
    public String toString() {
        return String.format("Date: %s\n\n" + "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Type Of Business: %s\n" +
                        "Property Type: %s\n" +
                        "Comission Selected: %s" +
                        "Price: %s\n" +
                        "%s" +
                        "Address: %s\n",
                        
                date.toString(), agent.getEmployeeName(), agent.getEmployeeEmail(), agent.getPhoneNumber(), typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), property.toString(), address.toString());
    }

    /**
     * Returns a string representation of the announcement for rent.
     *
     * @return a string representation of the announcement for rent
     */
    public String toStringRent() {
        return String.format("Date: %s\n\n" +  "Responsible Agent: \n" + "Name: %s\n" + "Email: %s\n" + "Phone Number: %s\n\n" +
                        "Type Of Business: %s\n" +
                        "Property Type: %s\n" +
                        "Comission Selected: %s" +
                        "Price: %s\n" +
                        "Duration Of The Contract: %s\n" +
                        "%s" +
                        "Address: %s\n",
                        
                date.toString(), agent.getEmployeeName(),
                agent.getEmployeeEmail(), agent.getPhoneNumber(), typeOfBusiness.toString(),
                propertyType, comission.toString(), business.toString(), durationOfContract, property.toString(), address.toString());
    }

}
