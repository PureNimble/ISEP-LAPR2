package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.UserRepository;
import pt.ipp.isep.dei.esoft.project.domain.SendSms;

import java.util.*;

/**
 * The PublishAnnouncementUI class represents a user interface for publishing
 * announcements of properties for sale or rent. This class implements the Runnable interface
 * for multi-threading purposes.
 */
public class PublishAnnouncementUI implements Runnable {

    /**
     * Represents a class that handles the publication of announcements.
     * It contains various properties related to the announcement details.
     */
    private final PublishAnnouncementController controller = new PublishAnnouncementController();

    /**
     * The email associated with the announcement.
     */
    private String email;

    /**
     * The contract type of the property.
     */
    private String contractType;

    /**
     * The number of bedrooms in the property.
     */
    private int numberOfBedrooms;

    /**
     * The number of bathrooms in the property.
     */
    private int numberOfBathrooms;

    private Photos photos;

    /**
     * The number of parking spaces available for the property.
     */
    private int parkingSpaces;

    /**
     * The description of available equipment in the property.
     */
    private String availableEquipmentDescription;

    /**
     * The description of the property type.
     */
    private String propertyTypeDescription;

    /**
     * The distance from the city center for the property.
     */
    private int distanceFromCityCenter;

    /**
     * The area of the property.
     */
    private int area;

    /**
     * The existence of the basement availability in the property.
     */
    private String basement;

    /**
     * The existence of the inhabitable loft availability in the property.
     */
    private String inhabitableLoft;

    /**
     * The description of the sun exposure for the property.
     */
    private String sunExposure;

    /**
     * The commission description for the announcement.
     */
    private double comissionDescription;

    /**
     * The business description for the announcement.
     */
    private double businessDescription;

    /**
     * The date associated with the announcement.
     */
    private Date date;

    /**
     * The duration of the contract for the property.
     */
    private int durationOfContract;
    /**
    * Represents an agent associated with the announcement request.
     */
    private Employee agent;
    /**
     The description of the state.
     */
    private String stateDescription;
    /**
     The description of the district.
     */
    private String districtDescription;
    /**
     The description of the city.
     */
    private String cityDescription;
    /**
     The ZIP code of the location.
     */
    private int zipCode;
    /**
     The street of the location.
     */
    private String street;
    /**
     The ID associated with the announcement request.
     */
    private int propertyID;


    /**
     * Executes the user interface for publishing announcements of properties for sale or rent.
     * This method retrieves input data from the user, submits it to the controller, retrieves
     * the list of published announcements from the controller, and prints them to the console.
     */
    public void run() {

        System.out.println("Publish Announcement ");

        if (controller.getListAgents().size() > 0) {

            propertyTypeDescription = displayandselectPropertyType();

            contractType = displayAndSelectTypeOfBusiness();

            email = requestEmail();

            if (propertyTypeDescription.equals("Rent")) {

                durationOfContract = requestDurationOfContract();
            }

            requestData();


            comissionDescription = displayAndSelectComission();

            submitDataHouse();



            List<PublishedAnnouncement> publishedAnnouncement = controller.getPublishedAnnoucement();

            StringBuilder st = new StringBuilder();


            for (PublishedAnnouncement p : publishedAnnouncement) {
                st.append(p.toString());
                st.append("\n");
            }

            System.out.println(st);

        } else {
            System.out.println("There is no Agents on the system for you to choose one. ");
        }
    }

    /**
     * Submits the input data for a property listing to the controller.
     */
    private void submitDataHouse() {

        Client client = controller.getUserByEmail(email);

        String agentEmail = controller.getCurrentSessionEmail();

        agent = controller.getAgentByDescription(agentEmail);

        UserRepository UserRepositories = Repositories.getInstance().getUserRepository();

        if (UserRepositories.getUsers().contains(client)) {
            System.out.println(client);
        }

        PropertyType propertyType = controller.getPropertyTypeByDescription(propertyTypeDescription);
        Comission comission = controller.getComissionByDescription(comissionDescription);
        TypeOfBusiness typeOfBusiness = controller.getTypeOfBusinessByDescription(contractType);
        Business business = controller.getBusinessByDescription(businessDescription);
        State state = controller.getStateByDescription(stateDescription);
        District district = controller.getDistrictByDescription(districtDescription, state);
        City city = controller.getCityByDescription(cityDescription, district);
        AnnouncementState announcementState = AnnouncementState.available;
        Store store = agent.getStore();

        Address address = new Address(street, zipCode, district, city, state);

        Optional<PublishedAnnouncement> publishedAnnouncement = null;
        if (propertyTypeDescription.equals("Land")) {

            Property land = new Property(area, distanceFromCityCenter, photos, address);

            publishedAnnouncement = controller.createPublishmentAnnouncement(date, typeOfBusiness, land, propertyType, comission, business, durationOfContract, agent, client, propertyID, announcementState, store);

        } else {
            if (propertyTypeDescription.equals("Appartment")) {

                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                Residence appartment = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, address);

                publishedAnnouncement = controller.createPublishmentAnnouncement(date, typeOfBusiness, appartment, propertyType, comission, business, durationOfContract, agent, client, propertyID, announcementState, store);
            } else {
                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                House house = new House(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, photos, address);

                publishedAnnouncement = controller.createPublishmentAnnouncement(date, typeOfBusiness, house, propertyType, comission, business, durationOfContract, agent, client, propertyID, announcementState, store);

            }
        }
        if(publishedAnnouncement.isPresent()) {
            SendSms sendSms = new SendSms();

            String toWriteFile = "";
            toWriteFile = toWriteFile.
                    concat("Dear client ").
                    concat(publishedAnnouncement.get().getClient().getName()).
                    concat(",\n\nYour property located in ").
                    concat(publishedAnnouncement.get().getProperty().getAddress().toString()).
                    concat(" has been published on our website since ").
                    concat(publishedAnnouncement.get().getDate().toString()).
                    concat(". \n\nThe agent ").
                    concat(publishedAnnouncement.get().getAgent().getName()).
                    concat(" with phone number ").
                    concat(String.valueOf(publishedAnnouncement.get().getAgent().getPhoneNumber())).
                    concat(" will be responsible for your announcement. \nIf you have any doubts do not hesitate to contact us.\n\nBest regards,\nReal Estate USA");


            //METER O OWNER ( NUEMERO DE TELEFONE E EMAIL NA PROPRIEDADE)


            sendSms.createFile(String.valueOf(publishedAnnouncement.get().getClient().getPhoneNumber()));
            sendSms.writeFile(String.valueOf(publishedAnnouncement.get().getClient().getPhoneNumber()), toWriteFile);
        }
    }

    /**
     * Retrieves the input data from the user for a property listing.
     */
    private void requestData() {

        area = requestArea();

        businessDescription = requestPrice();

        distanceFromCityCenter = requestDistanceFromCityCenter();

        photos = new Photos(requestPhotos());

        Date now = new Date();

        date = now;

        stateDescription = displayAndSelectState();

        districtDescription = displayAndSelectDistrict();

        cityDescription = displayAndSelectCity();

        //Request the Zip Code from the console
        zipCode = requestZipcodeDescription();

        //Request the Street from the console
        street = requestStreetDescription();

        if (!propertyTypeDescription.equals("Land")) {

            numberOfBathrooms = requestNumberOfBathrooms();

            numberOfBedrooms = requestNumberOfBedrooms();

            parkingSpaces = requestparkingSpaces();

            availableEquipmentDescription = displayAndSelectAvailableEquipment();
        }

        if (propertyTypeDescription.equals("House")) {

            basement = requestBasement();

            inhabitableLoft = requestInhabitableLoft();

            do {
                sunExposure = requestSunExposure();
                if (!sunExposure.equals("North") && !sunExposure.equals("South") && !sunExposure.equals("West") && !sunExposure.equals("East")) {
                    System.out.println("Please select one of the coordinates North, South, West or East");
                }
            } while (!sunExposure.equals("North") && !sunExposure.equals("South") && !sunExposure.equals("West") && !sunExposure.equals("East"));


        }


    }

    /**
     * Retrieves the email input data from the user.
     *
     * @return A String representing the email address input by the user.
     */
    private String requestEmail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Owner Email: ");
        return input.nextLine();
    }

    /**
     * If the selected property type is for rent, this method retrieves the duration
     * of the rental agreement from the user.
     *
     * @return An int representing the duration of the rental agreement.
     */
    private int requestDurationOfContract() {
        Scanner input = new Scanner(System.in);
        int durationOfContract;

        do {

            try {
                System.out.println("Duration of Contract: ");
                durationOfContract = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                durationOfContract = -1;
            }

        } while (durationOfContract < 0);

        return durationOfContract;
    }

    /** Displays a list of states and prompts the user to select one.
     *
     * @return the string description of the selected state.
     */
    private String displayAndSelectState() {
        //Display the list of task categories
        List<State> states = controller.getState();

        int listSize = states.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            try {

                displayStateOptions(states);
                System.out.println("Select a State: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }

        }

        String description = states.get(answer - 1).getState();
        return description;

    }

    /**
     * Displays a list of cities and prompts the user to select one.
     *
     * @return the cities description of the selected state.
     */
    private String displayAndSelectCity() {
        //Display the list of task categories

        State state = controller.getStateByDescription(stateDescription);

        District district = controller.getDistrictByDescription(districtDescription, state);

        List<City> cities = district.getCities();

        int listSize = cities.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            try {

                displayCityOptions(district);
                System.out.println("Select a City: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }


        }

        String description = cities.get(answer - 1).getCity();

        return description;

    }

    /**
     * Displays a list of district and prompts the user to select one.
     *
     * @return the district description of the selected state.
     */
    private String displayAndSelectDistrict() {
        //Display the list of task categories

        List<State> states = controller.getState();

        State state = controller.getStateByDescription(stateDescription);

        int listSize = state.getDistricts().size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while ((answer < 1 || answer > listSize)) {

            try {

                displayDistrictOptions(state);
                System.out.println("Select a District: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }

        }

        String description = state.getDistricts().get(answer - 1).getDistrict();


        return description;

    }

    /** Requests the zip code from the user and validates if it is a 5-digit integer.
     *
     * @return the integer zip code.
     */
    private int requestZipcodeDescription() {
        Scanner input = new Scanner(System.in);
        String zipCodeString;
        int zipCodeInt;

        do {

            do {

                try {
                    System.out.println("Zip Code: ");
                    zipCodeInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    zipCodeInt = -1;
                }

            } while (zipCodeInt < 0);

            zipCodeString = Integer.toString(zipCodeInt);
            if (zipCodeString.length() != 5) {
                System.out.println("A zipcode is a number with only 5 digits");
            }

        } while (zipCodeString.length() != 5);
        return zipCodeInt;
    }

    /**
     * Requests the street designation from the user.
     *
     * @return the string street designation.
     */
    private String requestStreetDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street: ");
        return input.nextLine();
    }

    /**
     * Requests the number of bedrooms for the property from the user.
     *
     * @return the number of bedrooms entered by the user.
     */
    private int requestNumberOfBedrooms() {
        Scanner input = new Scanner(System.in);
        int numberOfBedrooms;

        do {

            try {
                System.out.println("Number Of Bedrooms: ");
                numberOfBedrooms = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                numberOfBedrooms = -1;
            }

        } while (numberOfBedrooms < 0);

        return numberOfBedrooms;
    }

    /**
     * Requests the number of bathrooms for the property from the user.
     *
     * @return the number of bathrooms entered by the user.
     */
    private int requestNumberOfBathrooms() {
        Scanner input = new Scanner(System.in);
        int numberOfBathrooms;

        do {

            try {
                System.out.println("Number Of Bathrooms: ");
                numberOfBathrooms = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                numberOfBathrooms = -1;
            }

        } while (numberOfBathrooms < 0);

        return numberOfBathrooms;
    }

    /**
     * Requests the number of parking spaces for the property from the user.
     *
     * @return the number of parking spaces entered by the user.
     */
    private int requestparkingSpaces() {
        Scanner input = new Scanner(System.in);
        int parkingSpaces;

        do {

            try {
                System.out.println("Parking Spaces: ");
                parkingSpaces = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                parkingSpaces = -1;
            }

        } while (parkingSpaces < 0);

        return parkingSpaces;
    }

    /**
     * Requests the distance of the property from the city center from the user.
     *
     * @return the distance from the city center entered by the user.
     */
    private int requestDistanceFromCityCenter() {
        Scanner input = new Scanner(System.in);
        int distanceFromCityCenter;

        do {

            try {
                System.out.println("Distance From City Center: ");
                distanceFromCityCenter = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                distanceFromCityCenter = -1;
            }

        } while (distanceFromCityCenter < 0);

        return distanceFromCityCenter;
    }

    /**
     * Requests the area of the property from the user.
     *
     * @return the area entered by the user.
     */
    private int requestArea() {
        Scanner input = new Scanner(System.in);
        int area;

        do {

            try {
                System.out.println("Area: ");
                area = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                area = -1;
            }

        } while (area < 0);

        return area;
    }
    /**
     * Requests the user to provide photos for a property.
     *
     * @return The list of property photos as a string representation.
     */
    private String requestPhotos() {
        Scanner input = new Scanner(System.in);
        int max = 30;
        List<String> photos = new ArrayList<>();

        try {
            String choice = "Y";
            while (choice.equalsIgnoreCase("Y") && photos.size() < max) {
                int nphoto = photos.size() + 1;
                System.out.print("Please enter the URI of a photo for the property (" + nphoto + "): \n");
                String uri = input.nextLine();
                photos.add(uri);

                if (photos.size() < max) {
                    boolean validChoice = false;
                    while (!validChoice) {
                        System.out.print("Do you want to add more photos? (Y/N): \n");
                        choice = input.nextLine();
                        try {
                            if (!choice.equalsIgnoreCase("Y") && !choice.equalsIgnoreCase("N")) {
                                throw new InputMismatchException();
                            }
                            validChoice = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please answer with \"Y\" if you want to add another photo and with \"N\" if you don't.\n");
                        }
                    }
                } else {
                    System.out.println("You have reached the maximum limit of " + max + " photos.\n");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again. \n");
            input.nextLine();
        }

        return photos.toString();
    }

    /**
     * Requests if the property has a basement from the user.
     *
     * @return the basement description entered by the user.
     */
    private String requestBasement() {
        Scanner input = new Scanner(System.in);
        String basementString;

        do {
            System.out.println("Basement (Y/N): ");
            basementString = input.nextLine();
            if (!basementString.equals("N") && !basementString.equals("Y")) {
                System.out.println("Please select N if the house doesn t have basement and Y if the house have basement. ");
            }

        } while (!basementString.equals("N") && !basementString.equals("Y"));

        return basementString;
    }

    /**
     * Requests if the property has an Inhabitable Loft from the user.
     *
     * @return the Inhabitable Loft description entered by the user.
     */
    private String requestInhabitableLoft() {
        Scanner input = new Scanner(System.in);
        String inhabitableLoftString;

        do {
            System.out.println("Inhabitable Loft (Y/N): ");
            inhabitableLoftString = input.nextLine();
            if (!basement.equals("N") && !basement.equals("Y")) {
                System.out.println("Please select N if the house doesn t have Inhabitable loft and Y if the house have Inhabitable Loft. ");
            }

        } while (!inhabitableLoftString.equals("N") && !inhabitableLoftString.equals("Y"));

        return inhabitableLoftString;
    }

    /**
     * Requests if the property has Sun Exposure from the user.
     *
     * @return the Sun Exposure description entered by the user.
     */
    private String requestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.println("Sun Exposure: ");
        return input.nextLine();
    }

    /**
     * Prompts the user to input the Price information.
     *
     * @return A double representing the Price information.
     */
    private double requestPrice() {
        Scanner input = new Scanner(System.in);
        double price;

        do {

            try {
                System.out.println("Price: ");
                price = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                price = -1;
            }

        } while (price < 0);

        return price;
    }

    /**
     * Displays a list of property types and allows the user to select one.
     *
     * @return A String representing the selected property type.
     */
    private String displayandselectPropertyType() {

        List<PropertyType> propertyTypes = controller.getPropertyType();

        int listSize = propertyTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayPropertyTypeOptions(propertyTypes);
                System.out.println("Select a Property Type: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                answer = -1;
            }
        }

        String description = propertyTypes.get(answer - 1).getDesignation();
        return description;

    }

    /**
     * Displays the list of PropertyType options to the user.
     *
     * @param propertyTypes A List of PropertyType objects containing the available property types.
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {

        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }

    /**
     * Displays a list of commission options and allows the user to select one.
     *
     * @return A double representing the selected commission rate.
     */
    private Double displayAndSelectComission() {

        List<Comission> comissions = controller.getComission();

        int listSize = comissions.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayComissionOptions(comissions);
                System.out.println("Select a Comission: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                answer = -1;
            }
        }

        double description;

//        if (answer != 5) {
        description = comissions.get(answer - 1).getComission();

//        } else {
//            Scanner ler = new Scanner(System.in);
//            System.out.println("Type the a percentage comission value.");
//            try {
//                description = ler.nextDouble();
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Please enter a number:");
//                description = ler.nextDouble();
//            }
//        }


        return description;

    }

    /**
     * Displays the list of Comission options to the user.
     *
     * @param comissions A List of Comission objects containing the available commission values.
     */
    private void displayComissionOptions(List<Comission> comissions) {

        int i = 1;
        for (Comission comission : comissions) {
            System.out.println(i + " - " + comission.getComission());
            i++;
        }
    }

    /**
     * Displays a list of business types and allows the user to select one.
     *
     * @return A String representing the selected business type.
     */
    private String displayAndSelectTypeOfBusiness() {

        List<TypeOfBusiness> typeOfBusinesses = controller.getTypeOfBusiness();

        int listSize = typeOfBusinesses.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayTypeOfBusinessOptions(typeOfBusinesses);
                System.out.println("Select a Type of Business: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                answer = -1;
            }
        }

        String description = typeOfBusinesses.get(answer - 1).getTypeOfBusiness();

        return description;

    }

    /**
     * Displays the list of TypeOfBusiness options to the user.
     *
     * @param typeOfBusinesses A List of TypeOfBusiness objects containing the available business types.
     */
    private void displayTypeOfBusinessOptions(List<TypeOfBusiness> typeOfBusinesses) {

        int i = 1;
        for (TypeOfBusiness typeOfBusiness : typeOfBusinesses) {
            System.out.println(i + " - " + typeOfBusiness.getTypeOfBusiness());
            i++;
        }
    }

    /**
     * Displays the available equipment options and prompts the user to select one.
     *
     * @return A String representing the selected available equipment option.
     */
    private String displayAndSelectAvailableEquipment() {

        List<AvailableEquipment> availableEquipments = controller.getAvailableEquipment();

        int listSize = availableEquipments.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAvailableEquipmentOptions(availableEquipments);
                System.out.println("Select a Available Equipment: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                answer = -1;
            }
        }

        String description = null;

        if (answer != 4) {
            description = availableEquipments.get(answer - 1).getAvailableEquipment();
        } else {
            Scanner ler = new Scanner(System.in);
            System.out.println("Write the Other available equipment: ");
            description = ler.nextLine();

            AvailableEquipment newEquipment = new AvailableEquipment(description);

            Optional<AvailableEquipment> addedEquipment = controller.getAvailableEquipmentRepository().add(newEquipment);

        }

        return description;

    }

    /**
     * Displays the available equipment options to the user.
     *
     * @param availableEquipments A List of AvailableEquipment objects containing the available equipment options.
     */
    private void displayAvailableEquipmentOptions(List<AvailableEquipment> availableEquipments) {

        int i = 1;
        for (AvailableEquipment availableEquipment : availableEquipments) {
            System.out.println(i + " - " + availableEquipment.getAvailableEquipment());
            i++;
        }
    }
    /**
     * Displays the available city options for a given district.
     *
     * @param district The district for which to display the city options.
     */
    private void displayCityOptions(District district) {
        int i = 1;

        for (City city : district.getCities()) {
            System.out.println(i + " - " + city.getCity());
            i++;
        }
    }
    /**
     * Displays the available district options for a given state.
     *
     * @param state The state for which to display the district options.
     */
    private void displayDistrictOptions(State state) {
        int i = 1;
        for (District district : state.getDistricts()) {
            System.out.println(i + " - " + district.getDistrict());
            i++;

        }
    }
    /**
     * Displays the available state options.
     *
     * @param states The list of states for which to display the options.
     */
    private void displayStateOptions(List<State> states) {
        int i = 1;

        for (State state : states) {
            System.out.println(i + " - " + state.getState());
            i++;
        }
    }


}