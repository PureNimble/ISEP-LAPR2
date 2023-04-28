package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**

 The PublishedAnnouncement class represents a published announcement of a property's availability.
 */
public class PublishedAnnouncement {

    private Date date;
    private TypeOfBusiness typeOfBusiness;
    private Property property;
    private PropertyType propertyType;
    private Comission comission;
    private Business business;
    private int durationOfContract;

    public PublishedAnnouncement(Date date,TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType,Comission comission,Business business) {
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.date= date;
        this.business = business;

    }

    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.business = business;
        this.durationOfContract = durationOfContract;
    }

    public String toString() {
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,comission.toString(),business.toString(),property.toString());
    }

    public String toStringRent(){
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,comission.toString(),business.toString(),durationOfContract,property.toString());
    }



}
