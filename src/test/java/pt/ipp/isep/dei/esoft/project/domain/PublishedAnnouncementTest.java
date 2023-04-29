package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PublishedAnnouncementTest {

    private final TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
    private final PropertyType propertyType = new PropertyType("House");
    private final Comission comission = new Comission(34);
    private final Business business = new Business(100.0);
    private final Property property = new Property(3455, 56);
    private final int durationOfContract = 6;
    private final Date date = new Date();

    @Test
    void testToString() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business);
        String expectedString = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "%s",
                date.toString(), typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), property.toString());
        assertEquals(expectedString, announcement.toString());
    }

    @Test
    void toStringRent() {
        PublishedAnnouncement announcement = new PublishedAnnouncement(date, typeOfBusiness, property, propertyType, comission, business, durationOfContract);
        String expectedString = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Comission Selected:%s" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s",
                date.toString(), typeOfBusiness.toString(), propertyType, comission.toString(), business.toString(), durationOfContract, property.toString());
        assertEquals(expectedString, announcement.toStringRent());
    }
}