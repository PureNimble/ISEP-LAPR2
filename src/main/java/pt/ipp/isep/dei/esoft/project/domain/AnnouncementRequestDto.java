package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.Objects;

public class AnnouncementRequestDto {

    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Business business;
    private int durationOfContract;
    private Employee agent;

    private String status;

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

     Returns a string representation of this AnnouncementRequest object for rental properties.
     @return a string representation of this AnnouncementRequest object for rental properties.
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

    public Date getDate() {
        return date;
    }

    public TypeOfBusiness getTypeOfBusiness() {
        return typeOfBusiness;
    }

    public Property getProperty() {
        return property;
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

    public Employee getAgent() {
        return agent;
    }

    public String getStatus() {
        return status;
    }

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
