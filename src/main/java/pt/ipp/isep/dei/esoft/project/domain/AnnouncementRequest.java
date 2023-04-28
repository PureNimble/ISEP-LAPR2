package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;

public class AnnouncementRequest {
    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;

    private Business business;

    private int durationOfContract;

    public AnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
    }

    public AnnouncementRequest(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Business business, int durationOfContract) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.business = business;
        this.durationOfContract = durationOfContract;
    }

    public String toString() {
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),property.toString());
    }

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
