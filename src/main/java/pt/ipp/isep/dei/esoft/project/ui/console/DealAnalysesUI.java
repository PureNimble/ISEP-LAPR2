package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DealAnalysesController;
import pt.ipp.isep.dei.esoft.project.domain.RegressionDTO;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The PlaceOrderUI class represents the user interface for placing an order.
 * It implements the Runnable interface to support concurrent execution.
 */
public class DealAnalysesUI implements Runnable {

    /**
     * Scanner instance for user input.
     */
    private final Scanner input = new Scanner(System.in);

    /**
     * Controller for managing the placing of offers.
     */
    private final DealAnalysesController controller = new DealAnalysesController();

    public void run() {
        System.out.println("\nDeal Analysis:");

        String regressionModel = requestRegression();
        RegressionDTO regressionDTO;

        if (regressionModel != null) {
            if (regressionModel.equals("SimpleLinear")) {
                regressionDTO = controller.regressionModel(requestParam(), 0.05, singleValueToPredict());
            }
            else {
                regressionDTO = controller.regressionModel(-1, 0.05, multiValueToPredict());
            }

            if (regressionDTO.getReport() != null){
                System.out.println("\n" + regressionDTO.getReport());
            }

            if (regressionDTO.getPrediction() != null){
                System.out.println("Prediction: " + regressionDTO.getPrediction());
            }
        }

    }

    private String requestRegression() {
        int choice = -1;
        String regressionModel = null;

        do {

            System.out.println("\nWhich regression model do you wish to use?\n");
            System.out.println("1. Multi-Linear Regression");
            System.out.println("2. Simple Linear Regression");
            System.out.println("0. Cancel\n");
            System.out.print("Type your option: ");
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
            }

        } while (choice < 0 || choice > 2);

        if (choice == 1) {
            regressionModel = "MultiLinear";
        } else if (choice == 2) {
            regressionModel = "SimpleLinear";
        }

        return regressionModel;
    }

    private int requestParam() {
        int option = -1;

        do {

            System.out.println("Choose which parameter you wish to use:\n");
            System.out.println("1. Area in m2\n");
            System.out.println("2. Distance from the city center\n");
            System.out.println("3. Number of Bedrooms\n");
            System.out.println("4. Number of Bathrooms\n");
            System.out.println("5. Number of Parking Spaces\n");
            System.out.println("0. Cancel\n");
            System.out.print("Type your option: ");

            try {
                option = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value");
                input.nextLine();
            }

        } while (option < 0 || option > 5);

        if (option == 0) option = -1;

        return option;
    }

    private double[] singleValueToPredict() {
        double option = -1;
        do {
            System.out.print("Choose the value for the parameter you wish to predict:");

            try {
                option = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value");
                input.nextLine();
            }

        } while(option < 0);

        return new double[]{option};
    }

    private double[] multiValueToPredict() {
        double[] valueToPredict = new double[5];
        String[] params = {"area", "distance from city center", "number of bedrooms", "number of bathrooms", "number of parking spaces"};

        double option;
        for (int i = 0; i < params.length; i++){
            option = -1;
            do {
                System.out.print("Choose the value for the "+ params[i] +" you wish to predict:");

                try {
                    option = input.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value");
                    input.nextLine();
                }

            } while(option < 0);

            valueToPredict[i] = option;
        }

        return valueToPredict;
    }
}