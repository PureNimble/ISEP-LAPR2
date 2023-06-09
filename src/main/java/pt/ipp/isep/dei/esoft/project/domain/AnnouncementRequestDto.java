package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.Objects;

/**
 * The type Announcement request dto.
 */
public class AnnouncementRequestDto {
    /**
     * The date associated with the object.
     */
    private Date date;
    /**
     * The TypeOfBusiness instance associated with the object.
     */
    private TypeOfBusiness typeOfBusiness;
    /**
     * The Property instance associated with the object.
     */
    private Property property;
    /**
     * The PropertyType instance associated with the object.
     */
    private PropertyType propertyType;
    /**
     * The Business instance associated with the object.
     */
    private Business business;

    /**
     * The duration of the contract associated with the object.
     */
    private int durationOfContract;
    /**
     * The Employee agent associated with the object.
     */
    private Employee agent;
    /**
     * The status associated with the object.
     */
    private String status;

    /**
     * Instantiates a new Announcement request dto.
     *
     * @param status             the status
     * @param date               the date
     * @param typeOfBusiness     the type of business
     * @param property           the property
     * @param propertyType       the property type
     * @param business           the business
     * @param durationOfContract the duration of contract
     * @param agent              the agent
     */
    public AnnouncementRequestDto(String status,Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract, Employee agent) {
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
     * Instantiates a new Announcement request dto.
     *
     * @param status         the status
     * @param date           the date
     * @param typeOfBusiness the type of business
     * @param property       the property
     * @param propertyType   the property type
     * @param business       the business
     * @param agent          the agent
     */
    public AnnouncementRequestDto(String status,Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business,Employee agent) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
        this.agent = agent;
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
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),property.toString());
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
                        "%s" +
                        "Address: %s",

                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),durationOfContract,property.toString());
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
     * Gets property.
     *
     * @return the property
     */
    public Property getProperty() {
        return property;
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
     * Checks if this AnnouncementRequestDto is equal to another object.
     *
     * @param o The object to compare against.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnouncementRequestDto that = (AnnouncementRequestDto) o;
        return durationOfContract == that.durationOfContract && date.equals(that.date) && typeOfBusiness.equals(that.typeOfBusiness) && property.equals(that.property) && propertyType.equals(that.propertyType) && business.equals(that.business) && agent.equals(that.agent) && status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, typeOfBusiness, property, propertyType, business, durationOfContract, agent, status);
    }
}
