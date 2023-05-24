package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    private AnnouncementRequest announcementRequest;

    /**
     * Constructs a new PublishedAnnouncement object for sale.
     *
     * @param date           the date the announcement was published
     * @param typeOfBusiness the type of business (buying or selling)
     * @param property       the property being advertised
     * @param propertyType   the type of property
     * @param comission      the commission chosen by the client
     * @param business       the business representing the client
     */
    public PublishedAnnouncement(Date date,TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType,Comission comission,Business business) {
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.date= date;
        this.business = business;
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
     */
    public PublishedAnnouncement(Date date, TypeOfBusiness typeOfBusiness, Property property, PropertyType propertyType, Comission comission, Business business, int durationOfContract) {
        this.date = date;
        this.typeOfBusiness = typeOfBusiness;
        this.property = property;
        this.propertyType = propertyType;
        this.comission = comission;
        this.business = business;
        this.durationOfContract = durationOfContract;
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

    public PublishedAnnouncement (AnnouncementRequestDto announcementRequestDto,Comission comission) {
        this.date = announcementRequestDto.getDate();
        this.typeOfBusiness = announcementRequestDto.getTypeOfBusiness();
        this.property = announcementRequestDto.getProperty();
        this.propertyType = announcementRequestDto.getPropertyType();
        this.business = announcementRequestDto.getBusiness();
        this.durationOfContract = announcementRequestDto.getDurationOfContract();
        this.comission = comission;
    }

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
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns a string representation of the announcement for sale.
     *
     * @return a string representation of the announcement for sale
     */
    public String toString() {
        return String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,comission.toString(),business.toString(),property.toString());
    }

    /**
     * Returns a string representation of the announcement for rent.
     *
     * @return a string representation of the announcement for rent
     */
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
