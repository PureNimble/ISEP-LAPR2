package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.UnregisterUserController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The type Resgister user ui.
 */
public class ResgisterUserUI implements Runnable {

    /**
     * Controller for unregister user
     */
    private final UnregisterUserController controller = new UnregisterUserController();

    /**
     * The zip code of the user's address.
     */
    private int zipCode;

    /**
     * The street name of the user's address.
     */
    private String street;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String employeeEmail;

    /**
     * The phone number of the user.
     */
    private long phoneNumber;

    /**
     * The description of the city where the user resides.
     */
    private String cityDescription;

    /**
     * The description of the state where the user resides.
     */
    private String stateDescription;

    /**
     * The description of the district where the user resides.
     */
    private String districtDescription;

    /**
     * The passport number of the user.
     */
    private int passportNumber;

    /**
     * The tax number of the user.
     */
    private int taxNumber;

    /**
     * The password of the user.
     */
    private String password;

    private String confirmacao;

    /**
     * Returns the RegisterEmployeeController instance associated with the UI.
     *
     * @return the RegisterEmployeeController instance associated with the UI.
     */
    private UnregisterUserController getController() {
        return controller;
    }

    /**
     * Runs the UI.
     */
    public void run() {
        System.out.println("Register");

         confirmacao = enterAdress();

        if (!confirmacao.equals("N")) {
            stateDescription = displayAndSelectState();

            districtDescription = displayAndSelectDistrict();

            cityDescription = displayAndSelectCity();
        }

        requestData();

        submitData();

    }

    /**
     * Submits the data provided by the user for registration.
     * If the state description is not null, registers the client with the provided information,
     * including the address. Otherwise, registers the client without an address.
     * Registers the user with the provided name, email, and password.
     * Writes the user's email and password to a file named "emailRegistration.txt".
     */
    private void submitData() {

        if (stateDescription != null) {
            State state = controller.getStateByDescription(stateDescription);
            District district = controller.getDistrictByDescription(districtDescription, state);
            City city = controller.getCityByDescription(cityDescription, district);

            Address address = new Address(street, zipCode, district, city, state);
            controller.registerClient(name, employeeEmail, passportNumber, taxNumber, phoneNumber, address);
        }
        controller.registerClient(name, employeeEmail, passportNumber, taxNumber, phoneNumber, null);
        controller.registerUser(name, employeeEmail, password, "Client");

        try {
            FileWriter fw = new FileWriter("emailRegistration.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Email:" + employeeEmail);
            pw.println("Password:" + password);

            pw.close();

        } catch (IOException ex) {
            System.out.println("Error to write password to file:" + ex.getMessage());
        }


    }
    /**
     * Requests the necessary data from the user for registration.
     * Requests the Zip Code from the console.
     * Requests the Street from the console.
     * Requests the Name Description from the console.
     * Requests the Employee Email Description from the console.
     * Requests the Phone Number from the console.
     * Requests the Passport Number from the console.
     * Requests the Tax Number from the console.
     * Retrieves the password from the controller.
     */
    private void requestData() {

        if (!confirmacao.equals("N")) {
            //Request the Zip Code from the console
            zipCode = requestZipcodeDescription();

            //Request the Street from the console
            street = requestStreetDescription();
        }
        //Request the Name Description from the console
        name = requestNameDescription();

        //Request the Employee Email Description from the console
        employeeEmail = requestEmailDescription();

        //Request the Phone Number from the console
        phoneNumber = requestPhoneNumberDescription();

        //Request the Passport Number from the console
        passportNumber = requestPassportNumberDescription();

        //Request the Tax Number from the console
        taxNumber = requestTaxNumberDescription();

        password = requestsPassword();

    }


    /**
     * Prompts the user to enter whether they want to register with their address.
     *
     * @return The user's choice of whether to register with their address as a string ("Y" or "N").
     */
    private String requestsPassword() {
        Scanner input = new Scanner(System.in);
        String password;
        int sumCapitalCharacters;
        int digits;
        do {
            System.out.println("Choose your password(7 characters,3 capital letters,2 digits):");
            password = input.nextLine();

            digits = 0;
            sumCapitalCharacters = 0;

            for (int i = 0; i < password.length() ; i++) {
                if (Character.isUpperCase(password.charAt(i))){
                    sumCapitalCharacters++;
                }else if (Character.isDigit(password.charAt(i))){
                    digits++;
                }
            }


        } while (password.length() != 7 || sumCapitalCharacters != 3 || digits != 2);

        return password;
    }



    /**
     * Prompts the user to enter whether they want to register with their address.
     *
     * @return The user's choice of whether to register with their address as a string ("Y" or "N").
     */
    private String enterAdress() {
        Scanner input = new Scanner(System.in);
        String address;
        do {
            System.out.println("Do you want to register with you address:(Y/N)");
            address = input.nextLine();
        } while (!address.equals("Y") && !address.equals("N"));

        return address;
    }

    /**
     * Requests the user's name.
     *
     * @return the user's name as a String.
     */
    private String requestNameDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name:");
        return input.nextLine();

    }

    /**
     * Requests the user's passport number, which must be a 9-digit integer.
     *
     * @return the user's passport number as an integer.
     */
    private int requestPassportNumberDescription() {
        Scanner input = new Scanner(System.in);
        String passportNumberString;
        int passportNumberInt;

        do {


            do {

                try {
                    System.out.println("Passport Number:");
                    passportNumberInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    passportNumberInt = -1;
                }

            } while (passportNumberInt < 0);


            passportNumberString = Integer.toString(passportNumberInt);
            if (passportNumberString.length() != 9) {
                System.out.println("A Passport Number is a number with only 9 digits");
            }

        } while (passportNumberString.length() != 9);


        return passportNumberInt;

    }

    /**
     * Requests the user's tax number, which must be a 9-digit integer.
     *
     * @return the user's tax number as an integer.
     */
    private int requestTaxNumberDescription() {
        Scanner input = new Scanner(System.in);
        String taxNumberString;
        int taxNumberInt;

        do {
            do {

                try {
                    System.out.println("Tax Number:");
                    taxNumberInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    taxNumberInt = -1;
                }

            } while (taxNumberInt < 0);

            taxNumberString = Integer.toString(taxNumberInt);
            if (taxNumberString.length() != 9) {
                System.out.println("A Tax number is a number with only 9 digits");
            }

        } while (taxNumberString.length() != 9);


        return taxNumberInt;

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
                    System.out.println("Phone Number:");
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
                    System.out.println("Zip Code:");
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
     * Requests the street name from the user.
     *
     * @return the string street name.
     */
    private String requestStreetDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street:");
        return input.nextLine();
    }

    /**
     * Requests the employee email from the user.
     *
     * @return the string employee email.
     */
    private String requestEmailDescription() {
        Scanner input = new Scanner(System.in);
        System.out.print("Email: ");
        String email = input.nextLine();

        while (!isValidEmail(email)) {
            System.out.println("Invalid email. Please enter a valid email address like x@x.xx");
            System.out.println("Email: ");
            email = input.nextLine();
        }

        return email;
    }
    /**
     * Checks if the provided email is valid based on a regular expression pattern.
     *
     * @param email The email to be validated.
     * @return true if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
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
                System.out.println("Select a State:");
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
                System.out.println("Select a City:");
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
                System.out.println("Select a District:");
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
            System.out.println(i + "-" + city.getCity());
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
            System.out.println(i + "-" + district.getDistrict());
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
            System.out.println(i + "-" + state.getState());
            i++;
        }
    }

}