package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.UserRepository;

import java.util.*;
/**

 The PublishAnnouncementUI class represents a user interface for publishing

 announcements of properties for sale or rent. This class implements the Runnable interface

 for multi-threading purposes.
 */
public class PublishAnnouncementUI implements Runnable {


    private final PublishAnnouncementController controller = new PublishAnnouncementController();
    private String email;
    private String contractType;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private int parkingSpaces;
    private String availableEquipmentDescription;
    private String propertyTypeDescription;
    private int distanceFromCityCenter;
    private int area;
    private String basement;
    private String inhabitableLoft;
    private String sunExposure;
    private double comissionDescription;
    private double businessDescription;
    private Date date;

    private int durationOfContract;
    /**

     Executes the user interface for publishing announcements of properties for sale or rent.

     This method retrieves input data from the user, submits it to the controller, retrieves

     the list of published announcements from the controller, and prints them to the console.
     */
    public void run() {

        System.out.println("Publish Announcement ");

        email = requestEmail();

        propertyTypeDescription = displayandselectPropertyType();

        if (propertyTypeDescription.equals("Rent")) {
            durationOfContract = requestDurationOfContract();
        }

        comissionDescription = displayAndSelectComission();

        contractType = displayAndSelectTypeOfBusiness();

        requestData();

        submitDataHouse();

        List<PublishedAnnouncement> publishedAnnouncement = controller.getPublishedAnnoucement();

        StringBuilder st = new StringBuilder();

        for (PublishedAnnouncement p : publishedAnnouncement) {
            st.append(p.toString());
            st.append("\n");
        }

        System.out.println(st);


    }
    /**

     Submits the input data for a property listing to the controller.
     */
    private void submitDataHouse() {

        User user = controller.getUserByEmail(email);

        UserRepository UserRepositories = Repositories.getInstance().getUserRepository();

        if (UserRepositories.getUsers().contains(user)) {
            System.out.println(user);
        }

        PropertyType propertyType = controller.getPropertyTypeByDescription(propertyTypeDescription);
        Comission comission = controller.getComissionByDescription(comissionDescription);
        TypeOfBusiness typeOfBusiness = controller.getTypeOfBusinessByDescription(contractType);
        Business business = controller.getBusinessByDescription(businessDescription);

        if (propertyTypeDescription.equals("Land")) {

            Property land = new Property(area, distanceFromCityCenter);

            Optional<PublishedAnnouncement> publishedAnnouncement = controller.createPublishmentAnnouncement(date, typeOfBusiness, land, propertyType, comission, business, durationOfContract);

        } else {
            if (propertyTypeDescription.equals("Appartment")) {

                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                Residence appartment = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);

                Optional<PublishedAnnouncement> publishedAnnouncement = controller.createPublishmentAnnouncement(date, typeOfBusiness, appartment, propertyType, comission, business, durationOfContract);
            } else {
                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                House house = new House(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure);

                Optional<PublishedAnnouncement> publishedAnnouncement = controller.createPublishmentAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, durationOfContract);

            }
        }


    }
    /**

     Retrieves the input data from the user for a property listing.
     */
    private void requestData() {

        area = requestArea();

        businessDescription = requestPrice();

        distanceFromCityCenter = requestDistanceFromCityCenter();

        Date now = new Date();

        date = now;

        if (!propertyTypeDescription.equals("Land")) {

            numberOfBathrooms = requestNumberOfBathrooms();

            numberOfBedrooms = requestNumberOfBedrooms();

            parkingSpaces = requestparkingSpaces();

            availableEquipmentDescription = displayAndSelectAvailableEquipment();
        }

        if (propertyTypeDescription.equals("House")) {

            basement = requestBasement();

            inhabitableLoft = requestInhabitableLoft();

            sunExposure = requestSunExposure();


        }


    }
    /**

     Retrieves the email input data from the user.
     @return A String representing the email address input by the user.
     */
    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Owner Email:");
        return input.nextLine();
    }
    /**

     If the selected property type is for rent, this method retrieves the duration
     of the rental agreement from the user.
     @return An int representing the duration of the rental agreement.
     */
    private int requestDurationOfContract() {
        Scanner input = new Scanner(System.in);
        int durationOfContract;

        do {

            try {
                System.out.println("Duration of Contract:");
                durationOfContract = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                durationOfContract = -1;
            }

        } while (durationOfContract > 0);

        return durationOfContract;
    }
    /**

     Requests the number of bedrooms for the property from the user.

     @return the number of bedrooms entered by the user.
     */
    private int requestNumberOfBedrooms() {
        Scanner input = new Scanner(System.in);
        int numberOfBedrooms;

        do {

            try {
                System.out.println("Number Of Bedrooms:");
                numberOfBedrooms = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                numberOfBedrooms = -1;
            }

        } while (numberOfBedrooms > -1);

        return numberOfBedrooms;
    }
    /**

     Requests the number of bathrooms for the property from the user.

     @return the number of bathrooms entered by the user.
     */
    private int requestNumberOfBathrooms() {
        Scanner input = new Scanner(System.in);
        int numberOfBathrooms;

        do {

            try {
                System.out.println("Number Of Bathrooms:");
                numberOfBathrooms = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                numberOfBathrooms = -1;
            }

        } while (numberOfBathrooms > -1);

        return numberOfBathrooms;
    }
    /**

     Requests the number of parking spaces for the property from the user.

     @return the number of parking spaces entered by the user.
     */
    private int requestparkingSpaces() {
        Scanner input = new Scanner(System.in);
        int parkingSpaces;

        do {

            try {
                System.out.println("Parking Spaces:");
                parkingSpaces = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                parkingSpaces = -1;
            }

        } while (parkingSpaces > -1);

        return parkingSpaces;
    }
    /**

     Requests the distance of the property from the city center from the user.

     @return the distance from the city center entered by the user.
     */
    private int requestDistanceFromCityCenter() {
        Scanner input = new Scanner(System.in);
        int distanceFromCityCenter;

        do {

            try {
                System.out.println("Distance From City Center:");
                distanceFromCityCenter = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                distanceFromCityCenter = -1;
            }

        } while (distanceFromCityCenter > -1);

        return distanceFromCityCenter;
    }
    /**

     Requests the area of the property from the user.

     @return the area entered by the user.
     */
    private int requestArea() {
        Scanner input = new Scanner(System.in);
        int area;

        do {

            try {
                System.out.println("Area:");
                area = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                area = -1;
            }

        } while (area > -1);

        return area;
    }
    /**

     Requests if the property has a basement from the user.
     @return the basement description entered by the user.
     */
    private String requestBasement() {
        Scanner input = new Scanner(System.in);
        System.out.println("Basement:");
        return input.nextLine();
    }
    /**

     Requests if the property has an Inhabitable Loft from the user.
     @return the Inhabitable Loft description entered by the user.
     */
    private String requestInhabitableLoft() {
        Scanner input = new Scanner(System.in);
        System.out.println("InhabitableLoft:");
        return input.nextLine();
    }
    /**

     Requests if the property has Sun Exposure from the user.
     @return the Sun Exposure description entered by the user.
     */
    private String requestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.println("Sun Exposure:");
        return input.nextLine();
    }
    /**

     Prompts the user to input the Price information.
     @return A double representing the Price information.
     */
    private double requestPrice() {
        Scanner input = new Scanner(System.in);
        double price;

        do {

            try {
                System.out.println("Price:");
                price = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                price = -1;
            }

        } while (price > -1);

        return price;
    }

    /**

     Displays a list of property types and allows the user to select one.
     @return A String representing the selected property type.
     */
    private String displayandselectPropertyType() {

        List<PropertyType> propertyTypes = controller.getPropertyType();

        int listSize = propertyTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayPropertyTypeOptions(propertyTypes);
                System.out.println("Select a Property Type:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        String description = propertyTypes.get(answer - 1).getDesignation();
        return description;

    }
    /**

     Displays the list of PropertyType options to the user.

     @param propertyTypes A List of PropertyType objects containing the available property types.
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {

        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }

    /**

     Displays a list of commission options and allows the user to select one.
     @return A double representing the selected commission rate.
     */
    private Double displayAndSelectComission() {

        List<Comission> comissions = controller.getComission();

        int listSize = comissions.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayComissionOptions(comissions);
                System.out.println("Select a Comission:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        Double description = comissions.get(answer - 1).getComission();

        return description;

    }
    /**

     Displays the list of Comission options to the user.

     @param comissions A List of Comission objects containing the available commission values.
     */
    private void displayComissionOptions(List<Comission> comissions) {

        int i = 1;
        for (Comission comission : comissions) {
            System.out.println(i + " - " + comission.getComission());
            i++;
        }
    }
    /**

     Displays a list of business types and allows the user to select one.
     @return A String representing the selected business type.
     */
    private String displayAndSelectTypeOfBusiness() {

        List<TypeOfBusiness> typeOfBusinesses = controller.getTypeOfBusiness();

        int listSize = typeOfBusinesses.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayTypeOfBusinessOptions(typeOfBusinesses);
                System.out.println("Select a Type of Business:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        String description = typeOfBusinesses.get(answer - 1).getTypeOfBusiness();

        return description;

    }
    /**

     Displays the list of TypeOfBusiness options to the user.

     @param typeOfBusinesses A List of TypeOfBusiness objects containing the available business types.
     */
    private void displayTypeOfBusinessOptions(List<TypeOfBusiness> typeOfBusinesses) {

        int i = 1;
        for (TypeOfBusiness typeOfBusiness : typeOfBusinesses) {
            System.out.println(i + " - " + typeOfBusiness.getTypeOfBusiness());
            i++;
        }
    }

    /**

     Displays the available equipment options and prompts the user to select one.

     @return A String representing the selected available equipment option.
     */
    private String displayAndSelectAvailableEquipment() {

        List<AvailableEquipment> availableEquipments = controller.getAvailableEquipment();

        int listSize = availableEquipments.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAvailableEquipmentOptions(availableEquipments);
                System.out.println("Select a Available Equipment:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        String description = availableEquipments.get(answer - 1).getAvailableEquipment();

        return description;

    }
    /**

     Displays the available equipment options to the user.

     @param availableEquipments A List of AvailableEquipment objects containing the available equipment options.
     */
    private void displayAvailableEquipmentOptions(List<AvailableEquipment> availableEquipments) {

        int i = 1;
        for (AvailableEquipment availableEquipment : availableEquipments) {
            System.out.println(i + " - " + availableEquipment.getAvailableEquipment());
            i++;
        }
    }


}
