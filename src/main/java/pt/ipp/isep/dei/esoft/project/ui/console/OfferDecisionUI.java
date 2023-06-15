package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import pt.ipp.isep.dei.esoft.project.application.controller.OfferDecisionController;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementOffersDTO;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementState;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Offer;
import pt.ipp.isep.dei.esoft.project.domain.OfferDto;
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
    List<Offer> offers = controller.getOffers();

    public void run() {
        System.out.println("List of Offers: ");

        List<AnnouncementOffersDTO> announcementOffersList = controller.getOffersByPropertyByHighestAmount();
        if (announcementOffersList.isEmpty()) {
            System.out.println("There are no offers");
        } else {
            int announcementIndex = 1;
            for (AnnouncementOffersDTO announcementOffers : announcementOffersList) {
                System.out.println(announcementIndex + ". " + announcementOffers.getPublishedAnnouncementDto().toString());
                List<OfferDto> offerDtoList = announcementOffers.getListOffersDto();
                if (offerDtoList.isEmpty()) {
                    System.out.println("\tNo offers for this announcement\n");
                } else {
                    int offerIndex = 1;
                    for (OfferDto offerDto : offerDtoList) {
                        System.out.println("\t" + offerIndex + ". " + offerDto.toString());
                        offerIndex++;
                    }
                }
                announcementIndex++;
            }

            int selectedAnnouncementIndex = -1;
            do {
                System.out.println("\nChoose an announcement: ");
                try {
                    selectedAnnouncementIndex = input.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                }
            } while (selectedAnnouncementIndex < 0 || selectedAnnouncementIndex > announcementOffersList.size() - 1);
            AnnouncementOffersDTO selectedAnnouncement = announcementOffersList.get(selectedAnnouncementIndex);

            List<OfferDto> offerDtoList = selectedAnnouncement.getListOffersDto();
            if (offerDtoList.isEmpty()) {
                System.out.println("No offers for this announcement.");
            } else {

                int selectedOfferIndex = -1;
                do {
                    try {
                        System.out.println("\nChoose an offer: ");
                        selectedOfferIndex = input.nextInt() - 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer value");
                        input.nextLine();
                    }

                } while (selectedOfferIndex > offerDtoList.size() - 1 || selectedOfferIndex < 0);

                OfferDto selectedOfferDto = offerDtoList.get(selectedOfferIndex);
                Offer selectedOffer = findOffer(selectedOfferDto.getName(), selectedOfferDto.getClient(), selectedOfferDto.getOrderAmount());
                if (selectedOffer == null) {
                    System.out.println("Offer not found.");
                } else {
                    acceptOrDecline(selectedOffer, offers);
                }
            }
        }   
    }

    private void acceptOrDecline(Offer offer, List<Offer> offersList){
        System.out.println("\n1. Accept");
        System.out.println("2. Decline");
        System.out.println("0. Cancel");
        int choice;
        String email = offer.getClient().getEmail();
        String subject = null;
        String body = null;
        String decision = null;
        String finalMessage = null;
        do {
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                choice = -1;
            }
            if (choice == 1){
                controller.declineOtherOffers(offer, offersList);
                controller.changeAnnouncementState(offer.getPublishedAnnouncement());
                controller.storeDecrement(offer.getPublishedAnnouncement().getStore());
                decision = "accepted";
                finalMessage = "Please contact us in order to finish all the paperwork and so that you can start this new chapter of your life as soon as possible.";
            }
            else if (choice == 2){
                controller.declineOffer(offer);
                decision = "rejected";
                finalMessage = "Feel free to contact us if you have any doubts.";
            }
            else if (choice == 0){
                break;
            }

            subject = "Your Offer has been " + decision;
            body = "Dear " + offer.getClient().getName() + ",\n" +
                    "\nYour Offer for the Announcement with the Property ID " + offer.getPublishedAnnouncement().getPropertyID() +
                    ", located at: " + offer.getPublishedAnnouncement().getProperty().getAddress().toString() +
                    " has been " + decision +
                    ".\n" + finalMessage +
                    "\n\nBest Regards," +
                    "\nReal Estate USA";

           if (controller.sendVisualizedEmail(email, subject, body) == false) {
                System.out.println("Couldn't send the email.");
            } else System.out.println("Email sent successfully.");    

        } while (choice < 0 || choice > 2);
    }

    private Offer findOffer(String name, Client client, double orderAmount) {

        for (Offer offer : offers) {
            if (offer.getName().equals(name) && offer.getClient().equals(client) && offer.getOrderAmount() == orderAmount) {
                return offer;
            }
        }

        return null; // Offer not found
    }

}
