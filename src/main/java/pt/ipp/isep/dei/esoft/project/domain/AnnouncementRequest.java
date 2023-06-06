package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.Objects;

/**
 * This class represents an announcement request, which is created by a user who wants to advertise their business or property.
 */
public class AnnouncementRequest {
    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Business business;
    private int durationOfContract;

    private Employee agent;

    private String status;

    /**
     * Creates a new AnnouncementRequest object with the given parameters.
     *
     * @param status         the status
     * @param date           the date of the announcement request.
     * @param typeOfBusiness the type of business to be advertised.
     * @param property       the property to be advertised.
     * @param propertyType   the type of property.
     * @param business       the business to be advertised.
     * @param agent          the agent
     */
    public AnnouncementRequest(String status,Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business,Employee agent) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
        this.agent = agent;
        this.status = status;
    }

    /**
     * Creates a new AnnouncementRequest object with the given parameters.
     *
     * @param status             the status
     * @param date               the date of the announcement request.
     * @param typeOfBusiness     the type of business to be advertised.
     * @param property           the property to be advertised.
     * @param propertyType       the type of property.
     * @param business           the business to be advertised.
     * @param durationOfContract the duration of the contract for the advertisement.
     * @param agent              the agent
     * @param address            the address
     */
    public AnnouncementRequest(String status,Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract, Employee agent) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
        this.durationOfContract = durationOfContract;
        this.agent = agent;
        this.status = status;
    }

    /**
     * Instantiates a new Announcement request.
     *
     * @param announcementRequestDto the announcement request dto
     */
    public AnnouncementRequest (AnnouncementRequestDto announcementRequestDto) {
        this.date = announcementRequestDto.getDate();
        this.typeOfBusiness = announcementRequestDto.getTypeOfBusiness();
        this.property = announcementRequestDto.getProperty();
        this.propertyType = announcementRequestDto.getPropertyType();
        this.business = announcementRequestDto.getBusiness();
        this.durationOfContract = announcementRequestDto.getDurationOfContract();
        this.agent = announcementRequestDto.getAgent();
        this.status = announcementRequestDto.getStatus();
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
     * Gets agent.
     *
     * @return the agent
     */
    public Employee getAgent() {
        return agent;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
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
     * Sets agent.
     *
     * @param agent the agent
     */
    public void setAgent(Employee agent) {
        this.agent = agent;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**

     Returns a string representation of this AnnouncementRequest object.
     @return a string representation of this AnnouncementRequest object.
     */
    public String toString() {
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s\n",
                        
                date.toString(), typeOfBusiness.toString(), propertyType, business.toString(), property.toString());
    }

    /**
     * Returns a string representation of this AnnouncementRequest object for rental properties.
     *
     * @return a string representation of this AnnouncementRequest object for rental properties.
     */
    public String toStringRent(){
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s\n",

                date.toString(), typeOfBusiness.toString(), propertyType, business.toString(), durationOfContract, property.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementRequest that = (AnnouncementRequest) o;
        return durationOfContract == that.durationOfContract && date.equals(that.date) && typeOfBusiness.equals(that.typeOfBusiness) && property.equals(that.property) && propertyType.equals(that.propertyType) && business.equals(that.business) && agent.equals(that.agent) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, typeOfBusiness, property, propertyType, business, durationOfContract, agent, status);
    }
}