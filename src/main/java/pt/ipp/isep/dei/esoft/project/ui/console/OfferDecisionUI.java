package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import pt.ipp.isep.dei.esoft.project.application.controller.OfferDecisionController;
import pt.ipp.isep.dei.esoft.project.domain.Offer;

public class OfferDecisionUI implements Runnable{


    private final Scanner input = new Scanner(System.in);
    private final OfferDecisionController controller = new OfferDecisionController();

    public void run() {
        System.out.println("List of Orders: ");

        var offersList = controller.getOffersByPropertyByHighestAmount();

        for (int i = 0; i < offersList.size(); i++){
            Offer offer = offersList.get(i);
            System.out.println(i+1 + ". " + offer.toString());
        }
        /*
        int choice;

        do {

            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a double value:");
                input.nextLine();
                choice = -1;
            }

            if (choice >= offersList.size()){
                choice = -1;
            }

        } while (choice < 0);
        */
        System.out.println("Choose an offer");
        acceptOrDecline(offersList.get(input.nextInt() - 1));

    }

    private void acceptOrDecline(Offer offer){
        System.out.println("1. Accept");
        System.out.println("2. Decline");

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

        } while (choice < 0);

    }
}
