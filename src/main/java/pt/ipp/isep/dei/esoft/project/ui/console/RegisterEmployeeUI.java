package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class RegisterEmployeeUI implements Runnable {
    private final RegisterEmployeeController controller = new RegisterEmployeeController();

    private final AuthenticationController authenticationController = new AuthenticationController();

    private final AuthenticationRepository authenticationRepository = new AuthenticationRepository();

    private int zipCode;
    private String street;
    private String name;
    private String employeeEmail;
    private Integer phoneNumber;
    private String roleDescription;
    private String storeDescription;

    private String cityDescription;

    private String stateDescription;

    private String districtDescription;

    private int passportNumber;

    private int taxNumber;


    private RegisterEmployeeController getController() {
        return controller;
    }

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

        submitData(rolesDescriptions);

        for (Employee t : listEmpregados) {
            System.out.println(t.toString());
            System.out.println();

        }
    }

    private void submitData(List<String> rolesDescriptions) {

        List<Role> rolesEmployee = getController().getRolesByDescription(rolesDescriptions);

        State state = getController().getStateByDescription(stateDescription);
        District district = getController().getDistrictByDescription(districtDescription, state);
        City city = getController().getCityByDescription(cityDescription, district);

        Address address = new Address(street, zipCode, district, city, state);

        Optional<Employee> employee = getController().createEmployee(employeeEmail, name, phoneNumber, rolesEmployee, storeDescription, address, passportNumber, taxNumber);

        String password = authenticationRepository.passwordGenerator();

        User user = new User(employeeEmail, passportNumber, taxNumber, name, address, phoneNumber);

        controller.addUser(user);


        try {
            FileWriter fw = new FileWriter("email.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Email:" + employeeEmail);
            pw.println("Password:" + password);

            pw.close();

        } catch (IOException ex) {
            System.out.println("Error to write password to file:" + ex.getMessage());
        }


        if (employee.isPresent()) {
            System.out.println("Task successfully created!");
        } else {
            System.out.println("Task not created!");
        }
    }

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

    private String requestNameDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name:");
        return input.nextLine();

    }

    private String addMoreRoles() {
        Scanner input = new Scanner(System.in);
        String addRoles;
        do {
            System.out.println("Do you want to add more Roles:(Y/N)");
            addRoles = input.nextLine();
        } while (!addRoles.equals("Y") && !addRoles.equals("N"));

        return addRoles;
    }

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

            }while (passportNumberInt > 1);


            passportNumberString = Integer.toString(passportNumberInt);
            if (passportNumberString.length() != 9) {
                System.out.println("A Passport Number is a number with only 9 digits");
            }

        } while (passportNumberString.length() != 9);


        return passportNumberInt;

    }

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

            }while (taxNumberInt > 1);

            taxNumberString = Integer.toString(taxNumberInt);
            if (taxNumberString.length() != 9) {
                System.out.println("A Tax number is a number with only 9 digits");
            }

        } while (taxNumberString.length() != 9);


        return taxNumberInt;

    }

    private int requestPhoneNumberDescription() {
        Scanner input = new Scanner(System.in);

        String phoneNumberString;
        int phoneNumberInt;

        do {

            do {

                try {
                    System.out.println("Phone Number:");
                    phoneNumberInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    phoneNumberInt = -1;
                }

            }while (phoneNumberInt > 1);


            phoneNumberString = Integer.toString(phoneNumberInt);
            if (phoneNumberString.length() != 10) {
                System.out.println("A Phone Number is a number with only 10 digits");
            }

        } while (phoneNumberString.length() != 10);


        return phoneNumberInt;
    }

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

            }while (zipCodeInt > 1);

            zipCodeString = Integer.toString(zipCodeInt);
            if (zipCodeString.length() != 5) {
                System.out.println("A zipcode is a number with only 5 digits");
            }

        } while (zipCodeString.length() != 5);
        return zipCodeInt;
    }

    private String requestStreetDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Street:");
        return input.nextLine();
    }

    private String requestEmployeeEmailDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Employee Email:");
        return input.nextLine();
    }

    private String displayAndSelectRoles(List<String> rolesDescriptions) {
        //Display the list of task categories
        List<Role> roles = controller.getRoles();

        int listSize = roles.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayRoleOptions(roles, rolesDescriptions);
                System.out.println("Select a role:");
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

    private String displayAndSelectStore() {
        //Display the list of task categories
        List<Store> stores = controller.getStore();

        int listSize = stores.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {

            try {

                displayStoreOptions(stores);
                System.out.println("Select a Store:");
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

    private void displayStoreOptions(List<Store> stores) {
        int i = 1;

        for (Store store : stores) {
            System.out.println(i + "-" + store.getId());
            i++;
        }
    }

    private void displayCityOptions(District district) {
        int i = 1;

        for (City city : district.getCities()) {
            System.out.println(i + "-" + city.getCity());
            i++;
        }
    }

    private void displayDistrictOptions(State state) {
        int i = 1;
        for (District district : state.getDistricts()) {
            System.out.println(i + "-" + district.getDistrict());
            i++;

        }
    }


    private void displayStateOptions(List<State> states) {
        int i = 1;

        for (State state : states) {
            System.out.println(i + "-" + state.getState());
            i++;
        }
    }


}
