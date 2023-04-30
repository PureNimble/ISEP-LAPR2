package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.*;

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

        propertyTypeDescription = displayAndSelectPropertytype();

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



    private String displayAndSelectPropertytype() {

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

    private void displayPropertyTypeOptions(List<PropertyType> propertyTypes) {

        int i = 1;
        for (PropertyType propertyType : propertyTypes) {
            System.out.println(i + " - " + propertyType.getDesignation());
            i++;
        }
    }


    private Employee displayAndSelectAgents() {

        List<Employee> agents = controller.getListAgents();

        int listSize = agents.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAgentOptions(agents);
                System.out.println("Select a Agent:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        Employee agent = agents.get(answer - 1);

        return agent;

    }

    private void displayAgentOptions(List<Employee> agents) {

        int i = 1;
        for (Employee employee : agents) {
            System.out.println(i + " - " + employee.toString());
            i++;
        }
    }


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

    private void displayTypeOfBusinessOptions(List<TypeOfBusiness> typeOfBusinesses) {

        int i = 1;
        for (TypeOfBusiness typeOfBusiness : typeOfBusinesses) {
            System.out.println(i + " - " + typeOfBusiness.getTypeOfBusiness());
            i++;
        }
    }


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

    private void displayAvailableEquipmentOptions(List<AvailableEquipment> availableEquipments) {

        int i = 1;
        for (AvailableEquipment availableEquipment : availableEquipments) {
            System.out.println(i + " - " + availableEquipment.getAvailableEquipment());
            i++;
        }
    }


}
