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
 * The PlaceOrderUI class represents the user interface for placing an order.
 * It implements the Runnable interface to support concurrent execution.
 */
public class PlaceOrderUI implements Runnable {
    private final PlaceOfferController controller = new PlaceOfferController();
    private final PublishAnnouncementController publishAnnouncementController = new PublishAnnouncementController();

    public void run() {
        System.out.println();
        System.out.println("Place an Order: ");
        System.out.println();

        Client client = controller.getClientEmail();
        List<Offer> pendingOffers = controller.getPendingOffersByClientEmail(client.getEmail());
        if (!pendingOffers.isEmpty()) {
            System.out.println("Please wait for your previous offer to be accepted or denied before making another one.");
            return;
        }

        PublishedAnnouncement publishedAnnouncement = requestChooseProperty(client);
        if (publishedAnnouncement == null) {
            return;
        }

        double offer = requestOffer(publishedAnnouncement);
        String clientName = requestClientName();

        if (offer <= publishedAnnouncement.getBusiness().getPrice()) {
            if (submitData(clientName, client, publishedAnnouncement, offer, OfferState.pending).isEmpty()) {
                System.out.println("The offer amount submitted has already been posted for this property. Please contact the agent that is responsible for this property.");
            } else {
                System.out.println("\n\nOffer sent successfully!\n\n");
            }
        } else {
            System.out.println("Invalid offer amount. The offer amount must be equal to or lower than the property price.");
        }
    }

    private PublishedAnnouncement requestChooseProperty(Client client) {
        Scanner input = new Scanner(System.in);
        List<PublishedAnnouncement> publishedAnnouncements = publishAnnouncementController.getAvailablePublishedAnnouncementsDesc();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < publishedAnnouncements.size(); i++) {
            sb.append(i + 1).append(" - ").append(publishedAnnouncements.get(i)).append("\n");
        }
        System.out.println(sb);

        int index;
        do {
            try {
                System.out.println("Choose one of the properties above.");
                index = input.nextInt() - 1;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value");
                input.nextLine();
                index = -1;
            }

            if (index > publishedAnnouncements.size() - 1) {
                System.out.printf("Invalid input. Please enter a value between 1 and %d\n", publishedAnnouncements.size());
            }
        } while (index < 0 || index > publishedAnnouncements.size() - 1);

        List<Offer> pendingOffers = controller.getPendingOffersByClientEmail(client.getEmail());
        if (!pendingOffers.isEmpty()) {
            System.out.println("Please wait for your previous offer to be accepted or denied before making another one.");
            return null;
        }

        return publishedAnnouncements.get(index);
    }

    private String requestClientName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name: ");
        return input.nextLine();
    }

    private double requestOffer(PublishedAnnouncement publishedAnnouncement) {
        Scanner input = new Scanner(System.in);
        double offerAmountDouble;

        do {
            try {
                System.out.println("Offer Amount: ");
                offerAmountDouble = input.nextDouble();
                if (offerAmountDouble > publishedAnnouncement.getBusiness().getPrice()) {
                    System.out.println("Invalid offer amount. The offer amount must be equal to or lower than the property price.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive value: ");
                input.nextLine();
                offerAmountDouble = -1;
            }
        } while (offerAmountDouble < 0 || offerAmountDouble > publishedAnnouncement.getBusiness().getPrice());

        return offerAmountDouble;
    }

    private Optional<Offer> submitData(String name, Client client, PublishedAnnouncement publishedAnnouncement, double offer, OfferState offerState) {
        return controller.createNewOfferToAgent(name, client, offer, publishedAnnouncement, offerState);
    }
}