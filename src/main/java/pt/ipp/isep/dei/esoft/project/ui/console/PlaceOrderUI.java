package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PlaceOfferController;
import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlaceOrderUI implements Runnable {

    private final PlaceOfferController controller = new PlaceOfferController();
    private final PublishAnnouncementController publishAnnouncementController = new PublishAnnouncementController();

    private int initialTime;

    @Override
    public void run() {
        System.out.println("Place Order");

        PublishedAnnouncement publishedAnnouncement = requestChooseProperty();
        double offer = requestOffer();


    }

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
                    System.out.println("Choose one of the properties above.");
                    index = input.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    index = -1;
                }
            } while (index < 0);

            if (index > publishedAnnouncements.size() + 1) {
                System.out.println(String.format("Invalid input. Please enter an value between 1 and %s:", publishedAnnouncements.size()));
                index = input.nextInt() - 1;
            }
        } while (index < 0);
        return publishedAnnouncements.get(index);
    }

    private double requestOffer() {
        Scanner input = new Scanner(System.in);
        String offerAmountString;
        double offerAmountDouble;


        do {
            try {
                System.out.println("Order Amount:");
                offerAmountDouble = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a positive value:");
                input.nextLine();
                offerAmountDouble = -1;
            }
        } while (offerAmountDouble < 0);
        offerAmountString = Double.toString(offerAmountDouble);

        return offerAmountDouble;
    }
}
