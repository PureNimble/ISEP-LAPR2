package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterStoreController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.StateRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The RegisterEmployeeUI class is responsible for user interaction related to employee registration.
 * It implements the Runnable interface to run the UI as a separate thread.
 */
public class RegisterStoreUI implements Runnable {
    private final RegisterStoreController controller = new RegisterStoreController();

    private final AuthenticationController authenticationController = new AuthenticationController();

    private final AuthenticationRepository authenticationRepository = new AuthenticationRepository();

    private int id;
    private String designation;
    private String email;
    private long phoneNumber;
    private String stateDescription;
    private String districtDescription;
    private String cityDescription;
    private int zipCode;
    private String street;
    private int listing;

    /**
     * Returns the RegisterEmployeeController instance associated with the UI.
     *
     * @return the RegisterEmployeeController instance associated with the UI.
     */
    private RegisterStoreController getController() {
        return controller;
    }

    /**
     * Runs the UI.
     */
    public void run() {

        System.out.println("Store");

        requestData();

        stateDescription = displayAndSelectState();

        districtDescription = displayAndSelectDistrict();

        cityDescription = displayAndSelectCity();

        //Request the Zip Code from the console
        zipCode = requestZipcodeDescription();

        //Request the Street from the console
        street = requestStreetDescription();

        listing = 0;

        submitData();

        List<Store> listStore = getController().getStore();

        for (Store s : listStore) {
            System.out.println(s.toString());
            System.out.println();

        }

    }

    /**
     * Submits the employee data to the RegisterEmployeeController for processing.
     *
     * @param rolesDescriptions the list of role descriptions selected by the employee.
     */
    private void submitData() {

        State state = getController().getStateByDescription(stateDescription);
        District district = getController().getDistrictByDescription(districtDescription, state);
        City city = getController().getCityByDescription(cityDescription, district);

        Address address = new Address(street, zipCode, district, city, state);

        Optional<Store> store = getController().registerStore(id, designation, address, email, phoneNumber, listing);

        if (store.isPresent()) {
            System.out.println("\nStore created with success!");
        } else {
            System.out.println("\nStore not created!");
        }
    }

    /**
     * Requests the employee data from the user through the console.
     */
    private void requestData() {

        //Request the designation Description from the console
        id = requestIdDescription();

        //Request the designation Description from the console
        designation = requestDesignationDescription();

        //Request the Employee Email Description from the console
        email = requestStoreEmailDescription();

        //Request the Phone Number from the console
        phoneNumber = requestPhoneNumberDescription();

    }

    /**
     * Requests the user's designation.
     *
     * @return the user's designation as a String.
     */
    private int requestIdDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Id: ");
        return input.nextInt();

    }

    /**
     * Requests the user's designation.
     *
     * @return the user's designation as a String.
     */
    private String requestDesignationDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("designation: ");
        return input.nextLine();

    }

    /**
     * Requests the user's phone number, which must be a 10-digit integer.
     *
     * @return the user's phone number as an integer.
     */
    private long requestPhoneNumberDescription() {
        Scanner input = new Scanner(System.in);

        String phoneNumberString;
        long phoneNumberLong;

        do {

            do {

                try {
                    System.out.println("Phone Number: ");
                    phoneNumberLong = input.nextLong();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    phoneNumberLong = -1;
                }

            } while (phoneNumberLong < 0);


            phoneNumberString = Long.toString(phoneNumberLong);
            if (phoneNumberString.length() != 10) {
                System.out.println("A Phone Number is a number with only 10 digits");
            }

        } while (phoneNumberString.length() != 10);


        return phoneNumberLong;
    }

    /**
     * Requests the zip code from the user and validates if it is a 5-digit integer.
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
     * Requests the employee email from the user.
     *
     * @return the string employee email.
     */
    private String requestStoreEmailDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Store Email: ");
        return input.nextLine();
    }

    /**
     * Displays a list of states and prompts the user to select one.
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

        State state = getController().getStateByDescription(stateDescription);

        District district = getController().getDistrictByDescription(districtDescription, state);

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

    /**
     * Displays a menu of cities within a given district as numbered options.
     *
     * @param district the district containing the cities to display
     */
    private void displayCityOptions(District district) {
        int i = 1;

        for (City city : district.getCities()) {
            System.out.println(i + " - " + city.getCity());
            i++;
        }
    }

    /**
     * Displays a menu of districts within a given state as numbered options.
     *
     * @param state the state containing the districts to display
     */
    private void displayDistrictOptions(State state) {
        int i = 1;
        for (District district : state.getDistricts()) {
            System.out.println(i + " - " + district.getDistrict());
            i++;

        }
    }

    /**
     * Displays a menu of states as numbered options.
     *
     * @param states a list of states to display
     */
    private void displayStateOptions(List<State> states) {
        int i = 1;

        for (State state : states) {
            System.out.println(i + " - " + state.getState());
            i++;
        }
    }


}