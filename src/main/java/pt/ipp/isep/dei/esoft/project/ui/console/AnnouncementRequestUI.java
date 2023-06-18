package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

/**
 * The AnnouncementRequestUI class is responsible for handling the user interface of the Announcement Request functionality.
 * <p>
 * It implements the Runnable interface for concurrent execution of code.
 */
public class AnnouncementRequestUI implements Runnable {

    /**
     * Represents the controller for managing announcement requests.
     */
    private final AnnouncementRequestController controller = new AnnouncementRequestController();

    /**
     * Represents the contract type of the property.
     */
    private String contractType;

    /**
     * Represents the number of bedrooms in the property.
     */
    private int numberOfBedrooms;

    /**
     * Represents the number of bathrooms in the property.
     */
    private int numberOfBathrooms;

    /**
     * Represents the number of parking spaces available.
     */
    private int parkingSpaces;

    /**
     * Represents the description of available equipment in the property.
     */
    private String availableEquipmentDescription;

    /**
     * Represents the description of the property type.
     */
    private String propertyTypeDescription;

    /**
     * Represents the distance of the property from the city center.
     */
    private int distanceFromCityCenter;

    /**
     * Represents the area of the property.
     */
    private int area;

    /**
     * Represents the presence of a basement in the property.
     */
    private String basement;

    /**
     * Represents the presence of an inhabitable loft in the property.
     */
    private String inhabitableLoft;

    /**
     * Represents the sun exposure of the property.
     */
    private String sunExposure;

    /**
     * Represents the responsible agent for the announcement request.
     */
    private String responsibleAgent;

    /**
     * Represents the description of the state.
     */
    private String stateDescription;

    /**
     * Represents the description of the district.
     */
    private String districtDescription;

    /**
     * Represents the description of the city.
     */
    private String cityDescription;

    /**
     * Represents the ZIP code of the property.
     */
    private int zipCode;

    /**
     * Represents the street of the property.
     */
    private String street;

    /**
     * Represents the description of the business.
     */
    private double businessDescription;

    /**
     * Represents the date of the announcement request.
     */
    private Date date;

    /**
     * Represents the duration of the contract for the property.
     */
    private int durationOfContract;

    /**
     * Represents the ID of the announcement request.
     */
    private String ID;

    /**
     * Represents the photos associated with the announcement request.
     */
    private Photos photos;


    private Client client;
    /**
     * Implements the Runnable interface method to run the Announcement Request functionality.
     * Displays property types and available agents to the user and requests information such as the type of contract,
     * the property information, and the responsible agent information. Then, submits the information to the controller
     * to create a new announcement request and displays it to the user.
     */
    public void run() {

        System.out.println("Announcement Request: ");

        if (controller.getListAgents().size() > 0) {
            propertyTypeDescription = displayAndSelectPropertytype();

            responsibleAgent = displayAndSelectAgents();

            contractType = displayAndSelectTypeOfBusiness();

            if (contractType.equals("Rent")) {
                durationOfContract = requestDurationOfContract();
            }

            requestData();

            submitData();



        }else {
            System.out.println("There is no Agents on the system for you to choose one.");
        }

    }

    /**
     * Submits the user's requested information to the controller to create a new announcement request.
     */
    private void submitData() {

        PropertyType propertyType = controller.getPropertyTypeByDescription(propertyTypeDescription);
        TypeOfBusiness typeOfBusiness = controller.getTypeOfBusinessByDescription(contractType);
        Business business = controller.getBusinessByDescription(businessDescription);
        Employee agent = controller.getAgentByDescription(responsibleAgent);
        State state = controller.getStateByDescription(stateDescription);
        District district = controller.getDistrictByDescription(districtDescription, state);
        City city = controller.getCityByDescription(cityDescription, district);
        client = controller.getClientEmail();

        Address address = new Address(street, zipCode, district, city, state);

        if (propertyTypeDescription.equals("Land")) {

            Property land = new Property(area, distanceFromCityCenter, photos, address);

            Optional<AnnouncementRequest> announcementRequest = controller.createAnnouncementRequest(date, typeOfBusiness, land, propertyType, business, durationOfContract,agent, client);

        } else {
            if (propertyTypeDescription.equals("Apartment")) {

                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                Residence appartment = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, photos, address);

                Optional<AnnouncementRequest> announcementRequest = controller.createAnnouncementRequest(date, typeOfBusiness, appartment, propertyType, business, durationOfContract,agent, client);
            } else {
                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                House house = new House(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure, photos, address);

                Optional<AnnouncementRequest> announcementRequest = controller.createAnnouncementRequest(date, typeOfBusiness, house, propertyType, business, durationOfContract,agent, client);

            }
        }


    }

    /**
     * Requests the necessary information from the user, such as the property type, the number of bathrooms and bedrooms,
     * the available equipment, etc.
     */
    private void requestData() {

        area = requestArea();

        distanceFromCityCenter = requestDistanceFromCityCenter();

        businessDescription = requestPrice();

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
                if (!sunExposure.equals("North") && !sunExposure.equals("South") && !sunExposure.equals("West")&& !sunExposure.equals("East")) {
                    System.out.println("Please select one of the coordinates North, South, West or East");
                }
            } while (!sunExposure.equals("North") && !sunExposure.equals("South") && !sunExposure.equals("West")&& !sunExposure.equals("East"));



        }

        photos = requestPhotos();
    }

    

    /**
     * Prompts the user to enter the duration of the rental contract, and returns the value as an integer.
     *
     * @return The duration of the rental contract as an integer.
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

    /**
     * Prompts the user to enter the price of the rental property, and returns the value as a double.
     *
     * @return The price of the rental property as a double.
     */
    private double requestPrice() {
        Scanner input = new Scanner(System.in);
        double price;

        do {

            try {
                System.out.println("Price: ");
                price = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double value: ");
                input.nextLine();
                price = -1;
            }

        } while (price < 0);

        return price;
    }


    /**
     * Prompts the user to enter the number of bedrooms in the rental property, and returns the value as an integer.
     *
     * @return The number of bedrooms in the rental property as an integer.
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
     * Prompts the user to enter the number of bathrooms in the rental property, and returns the value as an integer.
     *
     * @return The number of bathrooms in the rental property as an integer.
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
     * Prompts the user to enter the number of parking spaces available for the rental property, and returns the value as an integer.
     *
     * @return The number of parking spaces available for the rental property as an integer.
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
     * Prompts the user to enter the distance of the rental property from the city center, and returns the value as an integer.
     *
     * @return The distance of the rental property from the city center as an integer.
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
     * Requests user to input an integer value for the area of the property.
     *
     * @return The integer value of the area of the property.
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
     * Requests user to input a string value for the basement of the property.
     *
     * @return The string value of the basement of the property.
     */
    private String requestBasement() {
        Scanner input = new Scanner(System.in);
        String basementString;

        do {
            System.out.println("Basement (Y/N): ");
            basementString = input.nextLine();
            if (!basementString.equals("N") && !basementString.equals("Y")) {
                System.out.println("Please select N if the house doesn't have basement and Y if the house have basement.");
            }

        } while (!basementString.equals("N") && !basementString.equals("Y"));

        return basementString;
    }

    /**
     * Requests user to input a string value for the inhabitable loft of the property.
     *
     * @return The string value of the inhabitable loft of the property.
     */
    private String requestInhabitableLoft() {
        Scanner input = new Scanner(System.in);
        String inhabitableLoftString;

        do {
            System.out.println("Inhabitable Loft (Y/N): ");
            inhabitableLoftString = input.nextLine();
            if (!inhabitableLoftString.equals("N") && !inhabitableLoftString.equals("Y")) {
                System.out.println("Please select N if the house doesn't have Inhabitable loft and Y if the house have Inhabitable Loft.");
            }

        } while (!inhabitableLoftString.equals("N") && !inhabitableLoftString.equals("Y"));

        return inhabitableLoftString;
    }

    /**
     * Requests user to input a string value for the sun exposure of the property.
     *
     * @return The string value of the sun exposure of the property.
     */
    private String requestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.println("Sun Exposure: (North/South/West/East) ");
        return input.nextLine();
    }
    /**
     * Requests user input for photos and creates a new Photos object.
     *
     * @return The Photos object containing the list of URLs.
     */
    private Photos requestPhotos() {
        Scanner input = new Scanner(System.in);
        List<String> listPhotos = new ArrayList<>();
        String url;
        int counter = 0;
        do {
            try {
                System.out.print("\nPhotos URL: ");
                url = input.nextLine();
                listPhotos.add(url);
                counter++;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
            }
            if (counter == 30){
                System.out.println("You reached the limit of photos");
                break;
            }
            System.out.println("Do you want to add more photos? ");
    
        } while (addMorePhotos());

        return new Photos(listPhotos);
    }

    /**
     * Displays a list of property types and requests user to select one of them.
     *
     * @return The designation of the selected property type.
     */
    private String displayAndSelectPropertytype() {

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
     * Displays a list of property types.
     *
     * @param propertyTypes The list of property types to be displayed.
     */
    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {

        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }

    /**
     * Displays a list of agents and requests user to select one of them.
     *
     * @return The selected agent.
     */
    private String displayAndSelectAgents() {

        List<Employee> agents = controller.getListAgents();

        int listSize = agents.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAgentOptions(agents);
                System.out.println("Select an Agent: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        String agent = agents.get(answer - 1).toString();

        return agent;

    }

    /**
     * Displays a numbered list of employees and allows the user to select an employee by entering the corresponding number.
     *
     * @param agents a list of Employee objects to display
     */
    private void displayAgentOptions(List<Employee> agents) {

        int i = 1;
        for (Employee employee : agents) {
            System.out.println(i + " - " + employee.getName()+" Email: "+employee.getEmail());
            i++;
        }
    }

    /**
     * Displays a numbered list of TypeOfBusiness objects and allows the user to select a TypeOfBusiness object by entering the corresponding number.
     *
     * @return a String description of the selected TypeOfBusiness object
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
     * Displays a numbered list of TypeOfBusiness objects.
     *
     * @param typeOfBusinesses a list of TypeOfBusiness objects to display
     */
    private void displayTypeOfBusinessOptions(List<TypeOfBusiness> typeOfBusinesses) {

        int i = 1;
        for (TypeOfBusiness typeOfBusiness : typeOfBusinesses) {
            System.out.println(i + " - " + typeOfBusiness.getTypeOfBusiness());
            i++;
        }
    }

    /** Displays a list of states and prompts the user to select one.
     *
     * @return the string description of the selected state.
     **/
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
     * Displays a numbered list of AvailableEquipment objects and allows the user to select an AvailableEquipment object by entering the corresponding number.
     *
     * @return a String description of the selected AvailableEquipment object
     */
    private String displayAndSelectAvailableEquipment() {

        List<AvailableEquipment> availableEquipments = controller.getAvailableEquipment();

        int listSize = availableEquipments.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAvailableEquipmentOptions(availableEquipments);
                System.out.println("Select an Available Equipment: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                answer = -1;
            }
        }

        String description = availableEquipments.get(answer - 1).getAvailableEquipment();

        return description;

    }

    /**
     * Displays a numbered list of AvailableEquipment objects.
     *
     * @param availableEquipments a list of AvailableEquipment objects to display
     */
    private void displayAvailableEquipmentOptions(List<AvailableEquipment> availableEquipments) {

        int i = 1;
        for (AvailableEquipment availableEquipment : availableEquipments) {
            System.out.println(i + " - " + availableEquipment.getAvailableEquipment());
            i++;
        }
    }
    /**
     * Displays the city options for a given district.
     *
     * @param district The District object for which to display the city options.
     */
    private void displayCityOptions(District district) {
        int i = 1;

        for (City city : district.getCities()) {
            System.out.println(i + " - " + city.getCity());
            i++;
        }
    }
    /**
     * Displays the district options for a given state.
     *
     * @param state The State object for which to display the district options.
     */
    private void displayDistrictOptions(State state) {
        int i = 1;
        for (District district : state.getDistricts()) {
            System.out.println(i + " - " + district.getDistrict());
            i++;

        }
    }
    /**
     * Displays the state options for a given list of states.
     *
     * @param states The list of State objects for which to display the state options.
     */
    private void displayStateOptions(List<State> states) {
        int i = 1;

        for (State state : states) {
            System.out.println(i + " - " + state.getState());
            i++;
        }
    }

    /**
     * Prompts the user to add more photos.
     *
     * @return True if the user wants to add more photos, false otherwise.
     */
    private boolean addMorePhotos(){
        Scanner input = new Scanner(System.in);
        System.out.println("\n1. Yes");
        System.out.println("2. No");

        double choice;

        do {

            try {
                choice = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double value:");
                input.nextLine();
                choice = -1;
            }

            if (choice > 2){
                choice = -1;
            }
            else if (choice == 1){
                return true;
            }
            else if (choice == 2){
                return false;
            }

        } while (choice < 0);
        return false;
    }
}