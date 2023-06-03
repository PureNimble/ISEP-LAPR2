package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The RegisterEmployeeUI class is responsible for user interaction related to employee registration.
 * It implements the Runnable interface to run the UI as a separate thread.
 */
public class RegisterEmployeeUI implements Runnable {
    private final RegisterEmployeeController controller = new RegisterEmployeeController();

    private final AuthenticationController authenticationController = new AuthenticationController();

    private final AuthenticationRepository authenticationRepository = new AuthenticationRepository();

    private int zipCode;
    private String street;
    private String name;
    private String employeeEmail;
    private long phoneNumber;
    private String roleDescription;
    private String storeDescription;

    private String cityDescription;

    private String stateDescription;

    private String districtDescription;

    private int passportNumber;

    private int taxNumber;

    /**
     * Returns the RegisterEmployeeController instance associated with the UI.
     *
     * @return the RegisterEmployeeController instance associated with the UI.
     */
    private RegisterEmployeeController getController() {
        return controller;
    }

    /**
     * Runs the UI.
     */
    public void run() {
        System.out.println("Employee");

        String confirmacao;

        List<String> rolesDescriptions = new ArrayList<>();

        do {
            roleDescription = displayAndSelectRoles(rolesDescriptions);

            rolesDescriptions.add(roleDescription);

            confirmacao = addMoreRoles();

        } while (!confirmacao.equals("N") || rolesDescriptions.size() == 4);

        storeDescription = displayAndSelectStore();

        stateDescription = displayAndSelectState();

        districtDescription = displayAndSelectDistrict();

        cityDescription = displayAndSelectCity();


        List<Employee> listEmpregados = getController().getEmployee();


        requestData();

        String[] rolesString = new String[rolesDescriptions.size()];

        int i = 0;

        for (String roleDescription : rolesDescriptions) {
            rolesString[i] = roleDescription;
            i++;
        }

        submitData(rolesDescriptions,rolesString);

        for (Employee t : listEmpregados) {
            System.out.println(t.toString());
            System.out.println();

        }
    }

    /**
     * Submits the employee data to the RegisterEmployeeController for processing.
     *
     * @param rolesDescriptions the list of role descriptions selected by the employee.
     */
    private void submitData(List<String> rolesDescriptions,String[] rolesString) {

        List<Role> rolesEmployee = getController().getRolesByDescription(rolesDescriptions);

        State state = getController().getStateByDescription(stateDescription);
        District district = getController().getDistrictByDescription(districtDescription, state);
        City city = getController().getCityByDescription(cityDescription, district);

        Address address = new Address(street, zipCode, district, city, state);

        Optional<Employee> employee = getController().createEmployee(employeeEmail, name, phoneNumber, rolesEmployee, storeDescription, address, passportNumber, taxNumber);

        String password = authenticationRepository.passwordGenerator();

        Client client = new Client(employeeEmail, passportNumber, taxNumber, name, address, phoneNumber);

        controller.addUser(client);

        controller.addUser(name,employeeEmail,password,rolesString);


        try {
            FileWriter fw = new FileWriter("email.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Email: " + employeeEmail);
            pw.println("Password: " + password);

            pw.close();

        } catch (IOException ex) {
            System.out.println("Error to write password to file: " + ex.getMessage());
        }


        if (employee.isPresent()) {
            System.out.println("Task successfully created!");
        } else {
            System.out.println("Task not created!");
        }
    }

    /**
     * Requests the employee data from the user through the console.
     */
    private void requestData() {

        //Request the Zip Code from the console
        zipCode = requestZipcodeDescription();

        //Request the Street from the console
        street = requestStreetDescription();

        //Request the Name Description from the console
        name = requestNameDescription();

        //Request the Employee Email Description from the console
        employeeEmail = requestEmployeeEmailDescription();

        //Request the Phone Number from the console
        phoneNumber = requestPhoneNumberDescription();

        //Request the Passport Number from the console
        passportNumber = requestPassportNumberDescription();

        //Request the Tax Number from the console
        taxNumber = requestTaxNumberDescription();


    }

    /**
     * Requests the user's name.
     *
     * @return the user's name as a String.
     */
    private String requestNameDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();

    }

    /**
     * Requests the user whether they want to add more roles.
     *
     * @return "Y" if the user wants to add more roles, "N" otherwise.
     */
    private String addMoreRoles() {
        Scanner input = new Scanner(System.in);
        String addRoles;
        do {
            System.out.println("Do you want to add more Roles (Y/N): ");
            addRoles = input.nextLine();
        } while (!addRoles.equals("Y") && !addRoles.equals("N"));

        return addRoles;
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
                    System.out.println("Passport Number: ");
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
                    System.out.println("Tax Number: ");
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
     * Requests the street name from the user.
     *
     * @return the string street name.
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
    private String requestEmployeeEmailDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Employee Email: ");
        return input.nextLine();
    }

    /**
     * Displays a list of roles and prompts the user to select one.
     *
     * @param rolesDescriptions the list of descriptions of roles.
     * @return the string description of the selected role.
     */
    private String displayAndSelectRoles(List<String> rolesDescriptions) {
        //Display the list of task categories
        List<Role> roles = controller.getRoles();

        int listSize = roles.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayRoleOptions(roles, rolesDescriptions);
                System.out.println("Select a role: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }

        }

        String description = roles.get(answer - 1).getDescription();

        return description;

    }

    /**
     * Displays a list of stores and prompts the user to select one.
     *
     * @return the string description of the selected store.
     */
    private String displayAndSelectStore() {
        //Display the list of task categories
        List<Store> stores = controller.getStore();

        int listSize = stores.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            try {

                displayStoreOptions(stores);
                System.out.println("Select a Store: ");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }

        }

        int numeroInteiro = stores.get(answer - 1).getId();

        String description = Integer.toString(numeroInteiro);

        return description;

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
     * Displays a menu of task categories as numbered options. If a list of role descriptions
     * is provided, only displays the task categories that do not have a description matching
     * any of the descriptions in the list.
     *
     * @param taskCategories    a list of roles representing task categories
     * @param rolesDescriptions a list of descriptions of roles to exclude from the menu
     */
    private void displayRoleOptions(List<Role> taskCategories, List<String> rolesDescriptions) {
        //display the task categories as a menu with number options to select
        int i = 1;
        int auxDiferente = 0;
        for (Role role : taskCategories) {
            if (rolesDescriptions.size() > 0) {
                for (String rD : rolesDescriptions) {
                    if (!rD.equals(role.getDescription())) {
                        auxDiferente++;
                    }
                }

                if (auxDiferente == rolesDescriptions.size()) {
                    System.out.println(taskCategories.indexOf(role) + 1 + "-" + role.getDescription());
                    i++;
                }
                auxDiferente = 0;
            } else {
                System.out.println(i + " - " + role.getDescription());
                i++;
            }

        }
    }

    /**
     * Displays a menu of store IDs as numbered options.
     *
     * @param stores a list of stores to display
     */
    private void displayStoreOptions(List<Store> stores) {
        int i = 1;

        for (Store store : stores) {
            System.out.println(i + " - " + store.getId());
            i++;
        }
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