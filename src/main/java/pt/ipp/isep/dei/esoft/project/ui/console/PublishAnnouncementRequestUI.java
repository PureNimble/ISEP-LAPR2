package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishedAnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The type Publish announcement request ui.
 */
public class PublishAnnouncementRequestUI implements Runnable {

    /**
     * The Published Announcement Request Controller used for managing published announcement requests.
     */
    private PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

    /**
     * The commission description for the published announcement request.
     */
    private Double comissionDescription;

    /**
     * The announcement request description for the published announcement request.
     */
    private int announcementRequestDescription;

    /**
     * The option for the published announcement request.
     */
    private String option;

    /**
     * The message justification for the published announcement request.
     */
    private String messageJustification;

    /**
     * The run method executes the logic for publishing an announcement request.
     * It displays options to the user, collects input, and performs the necessary operations.
     */
    public void run() {


        System.out.println("Publish Announcement Request: ");


        announcementRequestDescription = displayAndSelectAnnouncementRequests();

        option = requestOption();

        if (option.equals("A")) {
            comissionDescription = displayAndSelectComission();
            submitData();
        } else {
            requestData();
            sendEmailJustification();
        }


    }

    /**
     * Sends an email justification for a rejected announcement request.
     * It rejects the announcement request and writes the justification to a file.
     */

    private void sendEmailJustification() {

        controller.rejectPublishAnnouncementRequest(announcementRequestDescription);

        try {
            FileWriter fw = new FileWriter("emailJustificationAnnouncementRequest.txt");
            PrintWriter pw = new PrintWriter(fw);

            pw.println("----------Your announcement Request was declined-----");
            pw.println();
            pw.println(controller.getAnnouncementRequestByDescription(announcementRequestDescription).toString());
            pw.println();
            pw.println("The following Announcement Request was rejected by the following reason/s:");
            pw.println(messageJustification);
            pw.println();
            pw.println("Best regards");
            pw.println("Contact:");
            pw.close();

        } catch (IOException ex) {
            System.out.println("Error to write password to file:" + ex.getMessage());
        }

    }

    /**
     * Requests data for justification from the user.
     */

    private void requestData() {

        messageJustification = requestMessageJustification();


    }

    /**
     * Submits the data for publishing the announcement request.
     * It creates a new published announcement request based on the provided information.
     */

    private void submitData() {

        Optional<PublishedAnnouncement> publishedAnnouncement = controller.createPublishAnnouncementRequest(comissionDescription, announcementRequestDescription);

        if (publishedAnnouncement.isPresent()) {
            System.out.println("The ad was published successfully!!!");
        } else {
            System.out.println("Something went wrong...");
        }


    }

    /**
     * Requests the message justification from the user.
     *
     * @return The message justification provided by the user.
     */

    private String requestMessageJustification() {
        Scanner input = new Scanner(System.in);
        System.out.println("Give the reason to the owner justifying your decision");
        return input.nextLine();
    }

    /**
     * Requests the option (accept/reject) from the user.
     *
     * @return The option selected by the user.
     */

    private String requestOption() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to reject or accept this announcement(A-Accept/R-Reject):");
        return input.nextLine();
    }


    /**
     * Displays a list of announcement requests options and allows the user to select one.
     *
     * @return A double representing the selected commission rate.
     */
    private int displayAndSelectAnnouncementRequests() {

        List<AnnouncementRequestDto> announcementRequestsDtos = controller.toDto();


        int listSize = announcementRequestsDtos.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAnnouncementRequestOptions(announcementRequestsDtos);
                System.out.println("Select a Announcement Request:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        int description = answer - 1;

        return description;

    }

    /**
     * Displays the list of Announcement Requests options to the user.
     * A List of Announcement Requests objects containing the available commission values.
     */
    private void displayAnnouncementRequestOptions(List<AnnouncementRequestDto> announcementRequestDtos) {

        int i = 1;
        for (AnnouncementRequestDto announcementRequestDto : announcementRequestDtos) {
            System.out.println(i + " - " + announcementRequestDto.toString());
            i++;
        }

    }


    /**
     * Displays a list of commission options and allows the user to select one.
     *
     * @return A double representing the selected commission rate.
     */
    private Double displayAndSelectComission() {

        List<Comission> comissions = controller.getComission();

        int listSize = comissions.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayComissionOptions(comissions);
                System.out.println("Select a Comission:");
                answer = input.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
                answer = -1;
            }
        }

        Double description = comissions.get(answer - 1).getComission();

        return description;

    }

    /**
     * Displays the list of Comission options to the user.
     *
     * @param comissions A List of Comission objects containing the available commission values.
     */
    private void displayComissionOptions(List<Comission> comissions) {

        int i = 1;
        for (Comission comission : comissions) {
            System.out.println(i + " - " + comission.getComission());
            i++;
        }
    }
}
