package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterEmployeeController;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RegisterEmployeeUI implements Runnable{
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


        if (getController().getStore().size() > 0) {
            storeDescription = displayAndSelectStore();
        }

        stateDescription = displayAndSelectState();

        districtDescription = displayAndSelectCountry();

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

        User user = new User(employeeEmail,passportNumber,taxNumber,name,address,phoneNumber);

        controller.addUser(user);



        try {
            FileWriter fw = new FileWriter("email.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Email:" + employeeEmail);
            pw.println("Password:" + password);

            pw.close();

        } catch (IOException ex) {
        }


        if (employee.isPresent()) {
            System.out.println("Task successfully created!");
        } else {
            System.out.println("Task not created!");
        }
    }

    private void requestData() {

        //Request the Task Reference from the console
        zipCode = requestZipcodeDescription();

        //Request the Task Description from the console
        street = requestStreetDescription();

        //Request the Task Informal Description from the console
        name = requestNameDescription();

        //Request the Task Technical Description from the console
        employeeEmail = requestEmployeeEmailDescription();

        //Request the Task Duration from the console
        phoneNumber = requestPhoneNumberDescription();

        passportNumber = requestPassportNumberDescription();

        taxNumber = requestTaxNumberDescription();


    }

    private String requestNameDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name:");
        return input.nextLine();

    }

    private String addMoreRoles() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to add more Roles:");
        return input.nextLine();

    }

    private int requestPassportNumberDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Passport Number:");
        return input.nextInt();

    }

    private int requestTaxNumberDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Tax Number:");
        return input.nextInt();

    }

    private int requestPhoneNumberDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Phone Number:");
        return input.nextInt();
    }

    private int requestZipcodeDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Zipcode:");
        return input.nextInt();
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
            displayRoleOptions(roles, rolesDescriptions);
            System.out.println("Select a role:");
            answer = input.nextInt();
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
            displayStoreOptions(stores);
            System.out.println("Select a Store:");
            answer = input.nextInt();
        }

        int numeroInteiro = stores.get(answer - 1).getId();

        String description =Integer.toString(numeroInteiro);

        return description;

    }

    private String displayAndSelectState() {
        //Display the list of task categories
        List<State> states = controller.getState();

        int listSize = states.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            displayStateOptions(states);
            System.out.println("Select a State:");
            answer = input.nextInt();
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
            displayCityOptions(district);
            System.out.println("Select a City:");
            answer = input.nextInt();
        }

        String description = cities.get(answer - 1).getCity();

        return description;

    }


    private String displayAndSelectCountry() {
        //Display the list of task categories

        List<State> states = controller.getState();

        State state = controller.getStateByDescription(stateDescription);

        int listSize = states.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while ((answer < 1 || answer > listSize)) {
            displayCountryOptions(state);
            System.out.println("Select a Country:");
            answer = input.nextInt();
        }

        String description = states.get(answer - 1).getDistricts().get(answer - 1).getDistrict();


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

    private void displayCountryOptions(State state) {
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
