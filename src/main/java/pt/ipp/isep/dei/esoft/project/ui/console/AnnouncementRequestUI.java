package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AnnouncementRequestUI implements Runnable{

    private final AnnouncementRequestController controller = new AnnouncementRequestController();
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
    private Employee responsibleAgent;

    private double businessDescription;

    private Date date;

    private int durationOfContract;

    public void run() {

        System.out.println("Announcement Request: ");

        propertyTypeDescription = displayAndSelectTaskCategory();

        if (propertyTypeDescription.equals("Rent")){
            durationOfContract = requestDurationOfContract();
        }

        if (controller.getListAgents().size() > 0) {
            responsibleAgent = displayAndSelectAgents();

            contractType = displayAndSelectTypeOfBusiness();

            requestData();

            submitDataHouse();

            List<AnnouncementRequest> announcementRequests = controller.getAnnouncementRequest();

            StringBuilder st = new StringBuilder();

            for (AnnouncementRequest p : announcementRequests) {
                st.append(p.toString());
                st.append("\n");
            }

            System.out.println(st);

        }
    }

    private void submitDataHouse() {

        PropertyType propertyType = controller.getPropertyTypeByDescription(propertyTypeDescription);
        TypeOfBusiness typeOfBusiness = controller.getTypeOfBusinessByDescription(contractType);
        Business business = controller.getBusinessByDescription(businessDescription);

        if (propertyTypeDescription.equals("Land")) {

            Property land = new Property(area, distanceFromCityCenter);

            Optional<AnnouncementRequest> announcementRequest = controller.createAnnouncementRequest(date,typeOfBusiness,land,propertyType,business,durationOfContract);

        } else {
            if (propertyTypeDescription.equals("Appartment")) {

                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                Residence appartment = new Residence(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment);

                Optional<AnnouncementRequest> announcementRequest = controller.createAnnouncementRequest(date,typeOfBusiness, appartment,propertyType, business,durationOfContract);
            } else {
                AvailableEquipment availableEquipment = controller.getAvailableEquipmentByDescription(availableEquipmentDescription);

                House house = new House(area, distanceFromCityCenter, numberOfBedrooms, numberOfBathrooms, parkingSpaces, availableEquipment, basement, inhabitableLoft, sunExposure);

                Optional<AnnouncementRequest> announcementRequest = controller.createAnnouncementRequest(date,typeOfBusiness, house,propertyType,business,durationOfContract);

            }
        }


    }

    private void requestData() {

        area = requestArea();

        distanceFromCityCenter = requestDistanceFromCityCenter();

        businessDescription = requestPrice();

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

    private int requestDurationOfContract() {
        Scanner input = new Scanner(System.in);
        System.out.println("Duration of Contract:");
        return input.nextInt();
    }

    private double requestPrice() {
        Scanner input = new Scanner(System.in);
        System.out.println("Price:");
        return input.nextDouble();
    }



    private int requestNumberOfBedrooms() {
        Scanner input = new Scanner(System.in);
        System.out.println("Number of Bedrooms:");
        return input.nextInt();
    }

    private int requestNumberOfBathrooms() {
        Scanner input = new Scanner(System.in);
        System.out.println("Number Of Bathrooms:");
        return input.nextInt();
    }

    private int requestparkingSpaces() {
        Scanner input = new Scanner(System.in);
        System.out.println("Parking Spaces:");
        return input.nextInt();
    }

    private int requestDistanceFromCityCenter() {
        Scanner input = new Scanner(System.in);
        System.out.println("Distance from City Center:");
        return input.nextInt();
    }

    private int requestArea() {
        Scanner input = new Scanner(System.in);
        System.out.println("Area:");
        return input.nextInt();
    }

    private String requestBasement() {
        Scanner input = new Scanner(System.in);
        System.out.println("Basement:");
        return input.nextLine();
    }

    private String requestInhabitableLoft() {
        Scanner input = new Scanner(System.in);
        System.out.println("InhabitableLoft:");
        return input.nextLine();
    }

    private String requestSunExposure() {
        Scanner input = new Scanner(System.in);
        System.out.println("Sun Exposure:");
        return input.nextLine();
    }

    private String requestAvailableEquipment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Available Equipment:");
        return input.nextLine();
    }


    private String displayAndSelectTaskCategory() {
        //Display the list of task categories
        List<PropertyType> propertyTypes = controller.getPropertyType();

        int listSize = propertyTypes.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayPropertyTypeOptions(propertyTypes);
            System.out.println("Select a Property Type:");
            answer = input.nextInt();
        }

        String description = propertyTypes.get(answer - 1).getDesignation();
        return description;

    }

    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }


    private Employee displayAndSelectAgents() {
        //Display the list of task categories
        List<Employee> agents = controller.getListAgents();

        int listSize = agents.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayAgentOptions(agents);
            System.out.println("Select a Agent:");
            answer = input.nextInt();
        }

        Employee agent = agents.get(answer - 1);

        return agent;

    }

    private void displayAgentOptions(List<Employee> agents) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (Employee employee : agents) {
            System.out.println(i + " - " + employee.toString());
            i++;
        }
    }


    private String displayAndSelectTypeOfBusiness() {
        //Display the list of task categories
        List<TypeOfBusiness> typeOfBusinesses = controller.getTypeOfBusiness();

        int listSize = typeOfBusinesses.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayTypeOfBusinessOptions(typeOfBusinesses);
            System.out.println("Select a TypeOfBusiness:");
            answer = input.nextInt();
        }

        String  description = typeOfBusinesses.get(answer - 1).getTypeOfBusiness();

        return description;

    }

    private void displayTypeOfBusinessOptions(List<TypeOfBusiness> typeOfBusinesses) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (TypeOfBusiness typeOfBusiness : typeOfBusinesses) {
            System.out.println(i + " - " + typeOfBusiness.getTypeOfBusiness());
            i++;
        }
    }


    private String displayAndSelectAvailableEquipment() {
        //Display the list of task categories
        List<AvailableEquipment> availableEquipments = controller.getAvailableEquipment();

        int listSize = availableEquipments.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayAvailableEquipmentOptions(availableEquipments);
            System.out.println("Select a AvailableEquipment:");
            answer = input.nextInt();
        }

        String  description = availableEquipments.get(answer - 1).getAvailableEquipment();

        return description;

    }

    private void displayAvailableEquipmentOptions(List<AvailableEquipment> availableEquipments) {
        //display the task categories as a menu with number options to select
        int i = 1;
        for (AvailableEquipment availableEquipment : availableEquipments) {
            System.out.println(i + " - " + availableEquipment.getAvailableEquipment());
            i++;
        }
    }


}
