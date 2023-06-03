package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PlaceOfferController;
import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**

 The PlaceOrderUI class represents the user interface for placing an order.

 It implements the Runnable interface to support concurrent execution.
 */
public class PlaceOrderUI implements Runnable {

    private final PlaceOfferController controller = new PlaceOfferController();
    private final PublishAnnouncementController publishAnnouncementController = new PublishAnnouncementController();
    /**

     Executes the logic for placing an order.
     */
    @Override
    public void run() {

        System.out.println();
        System.out.println("Place an Order: ");
        System.out.println();

        PublishedAnnouncement publishedAnnouncement = requestChooseProperty();

        double offer = requestOffer();
        String clientName = requestClientName();

        if (submitData(clientName, publishedAnnouncement, offer,OfferState.pending).isEmpty()) {
            System.out.println("The offer amount submitted has already been posted for this property. Please contact the agent that is responsible for this property. ");
        } else System.out.println("\n\nOffer sent with success!\n\n");
    }
    /**

     Requests the user to choose a property from the published announcements.
     @return the selected PublishedAnnouncement object
     */
    private PublishedAnnouncement requestChooseProperty() {
        Scanner input = new Scanner(System.in);
        List<PublishedAnnouncement> publishedAnnouncements = publishAnnouncementController.getPublishedAnnouncementsDesc();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < publishedAnnouncements.size(); i++) {
            sb.append(i + 1 + " - ");
            sb.append(publishedAnnouncements.get(i).toString());
            sb.append("\n");
        }
        System.out.println(sb);
        int index;
        do {
            do {
                try {
                    System.out.println("Choose one of the properties above. ");
                    index = input.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value: ");
                    input.nextLine();
                    index = -1;
                }
            } while (index < 0);

            if (index > publishedAnnouncements.size() + 1) {
                System.out.println(String.format("Invalid input. Please enter an value between 1 and %s: ", publishedAnnouncements.size()));
                index = input.nextInt() - 1;
            }
        } while (index < 0);
        return publishedAnnouncements.get(index);
    }
    /**

     Requests the user to enter the client name.
     @return the entered client name
     */
    private String requestClientName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }
    /**

     Requests the user to enter the offer amount.

     @return the entered offer amount
     */
    private double requestOffer() {
        Scanner input = new Scanner(System.in);
        String offerAmountString;
        double offerAmountDouble;

        do {
            try {
                System.out.println("Offer Amount: ");
                offerAmountDouble = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive value: ");
                input.nextLine();
                offerAmountDouble = -1;
            }
        } while (offerAmountDouble < 0);
        offerAmountString = Double.toString(offerAmountDouble);

        return offerAmountDouble;
    }

//    private Client requestClient() {
//        Scanner input = new Scanner(System.in);
//        String clientString;
//        return null;
//    }
    /**

     Submits the order data to the controller for creating a new offer.
     @param name the client name
     @param publishedAnnouncement the selected published announcement
     @param offer the offer amount
     */
    private Optional<Offer> submitData(String name, PublishedAnnouncement publishedAnnouncement, double offer, OfferState offerState) {
        return controller.createNewOfferToAgent(name, offer, publishedAnnouncement,offerState);
    }
}