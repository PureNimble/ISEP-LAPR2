package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import pt.ipp.isep.dei.esoft.project.application.controller.OfferDecisionController;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferState;

/**
 * The type Offer decision ui.
 */
public class OfferDecisionUI implements Runnable{

    /**
     * Scanner instance for user input.
     */
    private final Scanner input = new Scanner(System.in);
    /**
     * Controller for managing offer decisions.
     */
    private final OfferDecisionController controller = new OfferDecisionController();
    /**
     * Runs the offer decision UI.
     */
    public void run() {
        System.out.println("List of Offers: ");

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
        if(offersList.isEmpty()){
            System.out.println("\nThere are no offers");
        } else{
            System.out.println("\nChoose an offer: \n");
            acceptOrDecline(offersList.get(input.nextInt() - 1), offersList);
        }
        
    }
    /**
     * Prompts the user to accept or decline an offer.
     *
     * @param offer      The offer to accept or decline.
     * @param offersList The list of offers.
     */
    private void acceptOrDecline(Offer offer, List<Offer> offersList){
        System.out.println("\n1. Accept");
        System.out.println("2. Decline");
        System.out.println("0. Cancel");

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
                offer.setOfferState(OfferState.accepted);
                controller.declineOtherOffers(offer, offersList);
            }
            else if (choice == 2){
                offer.setOfferState(OfferState.rejected);
            }
            else if (choice == 0){
                break;
            }

        } while (choice < 0);

    }
}
