package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementRequestTest {

    @Test
    void testToString() {
        Date date = new Date();
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("sale");
        Property property = new Property(123, 289);
        PropertyType propertyType = new PropertyType("apartment");
        Business business = new Business(34566);
        AnnouncementRequest announcementRequest = new AnnouncementRequest(date, typeOfBusiness, property, propertyType, business);

        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),property.toString());

        assertEquals(expectedOutput, announcementRequest.toString());
    }

    @Test
    void toStringRent() {
        Date date = new Date();
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("rent");
        Property property = new Property(100,167);
        PropertyType propertyType = new PropertyType("house");
        Business business = new Business(900);
        int durationOfContract = 12;
        AnnouncementRequest announcementRequest = new AnnouncementRequest(date, typeOfBusiness, property, propertyType, business, durationOfContract);

        String expectedOutput = String.format("Date:%s\n" +
                        "Type of business:%s\n" +
                        "Property Type:%s\n" +
                        "Price:%s\n" +
                        "DurationOfContract:%s\n" +
                        "%s",
                date.toString(),typeOfBusiness.toString(),propertyType,business.toString(),durationOfContract,property.toString());

        assertEquals(expectedOutput, announcementRequest.toStringRent());
    }
}