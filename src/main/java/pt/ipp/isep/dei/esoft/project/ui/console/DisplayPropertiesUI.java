package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DisplayPropertiesController;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfBusiness;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The PlaceOrderUI class represents the user interface for placing an order.
 * It implements the Runnable interface to support concurrent execution.
 */
public class DisplayPropertiesUI implements Runnable {
    /**
     * Scanner instance for user input.
     */
    private final Scanner input = new Scanner(System.in);
    /**
     * The DisplayPropertiesController instance used for retrieving and filtering property data.
     */
    private final DisplayPropertiesController controller = new DisplayPropertiesController();
    /**
     * The type of property to search for.
     */
    private String propertyType;

    /**
     * The number of rooms to search for.
     */
    private int numberOfRooms;
    /**
     * The type of business associated with the property.
     */
    private String businessType;
    /**
     * A flag indicating the search method to use.
     */
    private boolean method;
    /**
     * The sorting option for the displayed properties.
     */
    private int sorting;
    /**
     * Runs the property display user interface.
     * Takes user input for property search criteria, retrieves the matching properties,
     * and displays them according to the specified sorting option.
     */

    @Override
    public void run() {
        System.out.println("\nList of Properties (Sorted By most recent):");

        var filteredList = controller.getAvailablePublishedAnnouncementsDesc();

        for (int i = 0; i < filteredList.size(); i++){
            PublishedAnnouncement publishedAnnouncement = filteredList.get(i);
            System.out.println(i+1 + ". " + publishedAnnouncement.toString());
        }

        if(filteredList.isEmpty()){
            System.out.println("\nThere are no properties");
        } else {
            System.out.println("\nDo you wish to filter the list?");
            if (acceptOrDecline() == true) {
                filterCriteria();
                filteredList = controller.filterList(propertyType, businessType, numberOfRooms, filteredList);
                if(filteredList.isEmpty()){
                    System.out.println("\nThere are no properties matching those requirements");
                } else {
                    for (int i = 0; i < filteredList.size(); i++){
                        PublishedAnnouncement publishedAnnouncement = filteredList.get(i);
                        System.out.println(i+1 + ". " + publishedAnnouncement.toString());
                    }
                }
            } 
            var sortedList = filteredList;
            if (!sortedList.isEmpty()) {
                System.out.println("\nDo you wish to sort the list?");
                if (acceptOrDecline() == true) {
                    sorting = sortingCriteria();
                    if (method == true) {
                        if (sorting == 1) {
                            sortedList = controller.sortAnnouncementsAscPrice(filteredList);
                        } else if (sorting == 2){
                            sortedList = controller.sortAnnouncementsAscCity(filteredList);
                        } else {
                            sortedList = controller.sortAnnouncementsAscState(filteredList);
                        }
    
                    } else {
                        if (sorting == 1) {
                            sortedList = controller.sortAnnouncementsDesPrice(filteredList);
                        } else if (sorting == 2){
                            sortedList = controller.sortAnnouncementsDesCity(filteredList);
                        } else {
                            sortedList = controller.sortAnnouncementsDesState(filteredList);
                        }
                    }
                    for (int i = 0; i < sortedList.size(); i++){
                        PublishedAnnouncement publishedAnnouncement = sortedList.get(i);
                        System.out.println(i+1 + ". " + publishedAnnouncement.toString());
                    }
                }
            }

        }

    }
    /**
     * Displays the options for accepting or declining.
     *
     * @return True if the user accepts, False if the user declines.
     */
    private boolean acceptOrDecline(){
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

        } while (choice < 1);
        return false;
    }

    /**
     * Requests and filters the search criteria for properties.
     */
    private void filterCriteria(){

        propertyType = requestPropertyType();
        businessType = requestBusinessType();
        if (propertyType != "Land") {
            numberOfRooms = requestNumberOfRooms();
        }

    }
    /**
     * Requests the property type from the user.
     *
     * @return The selected property type.
     */
    private String requestPropertyType() {
        List<PropertyType> propertyTypes = controller.getPropertyType();
        int listSize = propertyTypes.size(), choice;
        System.out.print("\nProperty Types:");

        do {
            try {
                System.out.println();
                displayPropertyTypeOptions(propertyTypes);
                System.out.print("Select a Property Type: ");
                choice = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                choice = -1;
            }

        } while (choice < 1 || choice > listSize);

        String description = propertyTypes.get(choice - 1).getDesignation();
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
     * Requests the business type from the user.
     *
     * @return The selected business type.
     */
    private String requestBusinessType() {

        List<TypeOfBusiness> typeOfBusinesses = controller.getTypeOfBusiness();
        int listSize = typeOfBusinesses.size(), choice;
        System.out.print("\nBusiness Types:");

        do {
            try {
                System.out.println();
                displayTypeOfBusinessOptions(typeOfBusinesses);
                System.out.print("Select a Type of Business: ");
                choice = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                choice = -1;
            }

        } while (choice < 1 || choice > listSize);

        String description = typeOfBusinesses.get(choice - 1).getTypeOfBusiness();
        if (description == "Buy") {
            description = "Sale";
        }

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
     * Requests the number of rooms from the user.
     *
     * @return The number of rooms.
     */
    private int requestNumberOfRooms() {

        int roomNum;

        do {
            try {
                System.out.print("\nNumber of Rooms: ");
                roomNum = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                roomNum = -1;
            }

        } while (roomNum < 1);

        return roomNum;

    }
    /**
     * Prompts the user to choose a sorting criteria.
     *
     * @return The chosen sorting criteria.
     */
    private int sortingCriteria(){
        System.out.println("\nSorting Options: ");
        System.out.println("\n1. Price");
        System.out.println("2. City");
        System.out.println("3. State");
        System.out.print("Choose a sorting criteria: ");

        int choice;

        do {

            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                choice = -1;
            }

        } while (choice < 1 || choice > 3);

        method = requestAscDes();
        return choice;
    }
    /**
     * Requests the sorting method from the user.
     *
     * @return The chosen sorting method.
     */
    private boolean requestAscDes(){
        boolean meth = false;
        int choice;
        System.out.print("\nSorting methods: ");

        do {
            try {
                System.out.println("\n1. Ascending");
                System.out.println("2. Descending");
                System.out.print("\nChoose a sorting method: ");
                choice = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value: ");
                input.nextLine();
                choice = -1;
            }

        } while (choice < 1 || choice > 2);

        if (choice == 1) {
            meth = true;
        }

        return meth;
    }
}
