package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishedAnnouncementRequestController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PublishAnnouncementRequestUI implements Runnable {


    private PublishedAnnouncementRequestController controller = new PublishedAnnouncementRequestController();

    private Double comissionDescription;

    private int announcementRequestDescription;


    public void run() {

        System.out.println("Publish Announcement Request: ");


            announcementRequestDescription = displayAndSelectAnnouncementRequests(controller.getEmployeeByEmail(controller.getCurrentSessionEmail()));


            comissionDescription = displayAndSelectComission();

            submitData();


    }


    private void submitData() {

        AnnouncementRequest announcementRequestByDescription = controller.getAnnouncementRequestByDescription(announcementRequestDescription);

        Comission comissionByDescription = controller.getComissionByDescription(comissionDescription);

        Optional<PublishedAnnouncement> publishedAnnouncement = controller.createPublishAnnouncementRequest(comissionByDescription, announcementRequestByDescription);

        if (publishedAnnouncement.isPresent()) {
            System.out.println("O anúncio foi publicado com êxito!!!");
        } else {
            System.out.println("Aconteceu algum erro...");
        }

    }


    /**
     * Displays a list of announcement requests options and allows the user to select one.
     *
     * @return A double representing the selected commission rate.
     */
    private int displayAndSelectAnnouncementRequests(Employee agent) {

        List<AnnouncementRequest> announcementRequests = controller.getAnnouncementRequestByMostRecent(agent);

        int listSize = announcementRequests.size();
        int answer = -1;

        Scanner input = new Scanner(System.in);

        while (answer < 1 || answer > listSize) {
            try {

                displayAnnouncementRequestOptions(announcementRequests);
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
     *
     * @param announcementRequests A List of Announcement Requests objects containing the available commission values.
     */
    private void displayAnnouncementRequestOptions(List<AnnouncementRequest> announcementRequests) {

        int i = 1;
        for (AnnouncementRequest announcementRequest : announcementRequests) {
            System.out.println(i + " - " + announcementRequest.toString());
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
