package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

/**

 This class represents an announcement request, which is created by a user who wants to advertise their business or property.
 */
public class AnnouncementRequest {
    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Business business;
    private int durationOfContract;

    private Employee agent;

    /**

     Creates a new AnnouncementRequest object with the given parameters.
     @param date the date of the announcement request.
     @param typeOfBusiness the type of business to be advertised.
     @param property the property to be advertised.
     @param propertyType the type of property.
     @param business the business to be advertised.
     */
    public AnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business,Employee agent) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
        this.agent = agent;
    }
    /**

     Creates a new AnnouncementRequest object with the given parameters.
     @param date the date of the announcement request.
     @param typeOfBusiness the type of business to be advertised.
     @param property the property to be advertised.
     @param propertyType the type of property.
     @param business the business to be advertised.
     @param durationOfContract the duration of the contract for the advertisement.
     */
    public AnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract,Employee agent) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
        this.durationOfContract = durationOfContract;
        this.agent = agent;
    }


    public TypeOfBusiness getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public Business getBusiness() {
        return business;
    }

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

     Returns a string representation of this AnnouncementRequest object.
     @return a string representation of this AnnouncementRequest object.
     */
    public String toString() {
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),property.toString());
    }
    /**

     Returns a string representation of this AnnouncementRequest object for rental properties.
     @return a string representation of this AnnouncementRequest object for rental properties.
     */
    public String toStringRent(){
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),durationOfContract,property.toString());
    }
}