package pt.ipp.isep.dei.esoft.project.ui.console;


import pt.ipp.isep.dei.esoft.project.application.controller.ListDealsController;
import pt.ipp.isep.dei.esoft.project.domain.OfferDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ListDealsUI implements Runnable {


    private int algorithmDescription;

    private int criteriaDescription;

    private ListDealsController controller = new ListDealsController();


    public void run() {

        requestData();

        submitsData();


    }


    private void submitsData() {

        displayDealsMade();

    }

    private void requestData() {

        //Request the Algorithm from the console
        algorithmDescription = requestalgorithmDescription();

        //Request the Criteria from the console
        criteriaDescription = requestCriteriaDescription();


    }

    /**
     * Requests the algorithm from the user .
     *
     * @return the algorithm description.
     */
    private int requestalgorithmDescription() {
        Scanner input = new Scanner(System.in);
        int algorithmInt;

        do {
            do {

                try {
                    System.out.println("Algorithm: ");
                    System.out.println("1- Bubble Sort");
                    System.out.println("2- Sort Selection");
                    System.out.println("0- Cancel");
                    algorithmInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    algorithmInt = -1;
                }

            } while (algorithmInt < 0);

        } while (algorithmInt != 1 && algorithmInt != 2 && algorithmInt != 0);


        return algorithmInt;
    }

    /**
     * Requests the algorithm from the user .
     *
     * @return the algorithm description.
     */
    private int requestCriteriaDescription() {
        Scanner input = new Scanner(System.in);
        int criteriaInt;


        do {

            do {

                try {
                    System.out.println("Criteria: ");
                    System.out.println("1- Ascending");
                    System.out.println("2- Descending");
                    System.out.println("0- Cancel");
                    criteriaInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    criteriaInt = -1;
                }

            } while (criteriaInt < 0);
        } while (criteriaInt != 1 && criteriaInt != 2 && criteriaInt != 0);


        return criteriaInt;
    }

    /**
     * Displays a list of offer already accepted.
     *
     */
    private void displayDealsMade() {
        List<OfferDto> offerDtos = new ArrayList<>();
        if (algorithmDescription == 1 && criteriaDescription == 1){
            offerDtos = controller.toDtoAscendingAreaBubbleSort();
        }

        if (algorithmDescription == 1 && criteriaDescription == 2){
            offerDtos = controller.toDtoDescendingAreaBubbleSort();
        }

        if (algorithmDescription == 2 && criteriaDescription == 1){
            offerDtos = controller.toDtoAscendingAreaSortSelection();
        }

        if (algorithmDescription == 2 && criteriaDescription == 2){
            offerDtos = controller.toDtoDescendingAreaSortSelection();
        }

        if (algorithmDescription == 0 && criteriaDescription == 0){
            offerDtos = controller.toDtoOffersMostRecent();
        }

        displayDealsMadeOptions(offerDtos);

    }

    /**
     * Displays the list of Offers options to the user.
     */
    private void displayDealsMadeOptions(List<OfferDto> offerDtos) {

        int i = 1;
        for (OfferDto offerDto : offerDtos) {
            System.out.println(i + " - " + offerDto.toString());
            i++;
        }

    }
}
