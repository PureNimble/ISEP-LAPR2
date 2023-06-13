package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ClientMessagesController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import java.util.*;

/**
 * The type List message ui.
 */
public class ClientMessagesUI implements Runnable {
    /**
     * Scanner instance for user input.
     */
    private final Scanner input = new Scanner(System.in);
    /**
     * Controller for managing booking requests.
     */
    private final ClientMessagesController controller = new ClientMessagesController();
    /**
     * Runs the message request UI.
     */

    public void run() {
        System.out.println("\nInbox:\n");
        Message selectedMessage = displayAndSelectMessage();
        if (selectedMessage != null) {
            if (selectedMessage.isApprovedByAgent() == true) {
                PublishedAnnouncement publishedAnnouncement = selectedMessage.getPublishedAnnouncement();
                respondToClientOrNo(selectedMessage, publishedAnnouncement);
            }
        }
    }

    private Message displayAndSelectMessage() {
        int choice = -1;
        int index = 1;
        Message selectedMessage = null;
        List<Message> messageList = controller.getMessageRequests();
        if (messageList.size() != 0) {
            for (Message message : messageList) {
                if (message.getIsApprovedByAgent() == true) {
                    System.out.println(index + ". Message From: " + message.getPublishedAnnouncement().getAgent().getName() + " Subject: Your Visit Booking Request Has Been Accepted");
                } else System.out.println(index + ". Message From: " + message.getName() + " Subject: Your Visit Booking Request Has Been Rejected");
                index++;     
            }

            do {
                System.out.print("\nSelect a message to open: ");
                try {
                    choice = input.nextInt() - 1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                }
            } while (choice < 0 || choice > messageList.size() - 1);
            System.out.println();
            selectedMessage = messageList.get(choice);
            PublishedAnnouncement publishedAnnouncement = selectedMessage.getPublishedAnnouncement();
            Client client = controller.getClient();
            String email = publishedAnnouncement.getAgent().getEmail();
            String subject = "Your messages has been opened";
            String body = "\nYour message for the client " + client.getName() + " has been opened!" +
                    "\n\nProperty ID: " + publishedAnnouncement.getPropertyID() +
                    "\nLocated at: " + publishedAnnouncement.getProperty().getAddress().toString() +
                    "\nDate of visit: " + selectedMessage.getInitialDate() +
                    "\nStarting time at " + selectedMessage.getInitialTime() +
                    " and ending at " + selectedMessage.getEndTime() + 
                    "\n\nThis is an automatically generated email. Please do not reply";

            System.out.println(messageList.get(choice).toString()); 
            controller.sendVisualizedEmail(email, subject, body);

        } else System.out.println("No new messages available\n");
        
        return selectedMessage;
    }

    private void respondToClientOrNo(Message message, PublishedAnnouncement publishedAnnouncement) {
        int choice = -1;

        do {
            System.out.println("Do you wish to respond?");
            System.out.println("1. Yes");
            System.out.println("0. No");
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value:");
                input.nextLine();
            }
        } while (choice < 0 || choice > 1);

        Client client = controller.getClient();

        if (choice == 1) {

            String email = publishedAnnouncement.getAgent().getEmail();
            
            System.out.println("Responding to: " + email + "\n");

            String subject = null;
            String body = null;
            int responseChoice = -1;
            do {
                System.out.println("Do you wish to accept or deny the appointment?");
                System.out.println("1. Accept");
                System.out.println("2. Deny");
                try {
                    responseChoice = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                }
            } while (responseChoice < 1 || responseChoice > 2);

            if (responseChoice == 1) {
                subject = "Your Visit Booking Has Been Confirmed";
                body = "Dear " + publishedAnnouncement.getAgent().getName() + ",\n" +
                        "Ill be there," + "\n\nBest Regards,\n" + client.getName();

            } else {
                subject = "Your Visit Booking Has Been Rejected";
                System.out.println("Reason for denying the visit request: ");
                input.nextLine();
                String reason = input.nextLine();
                body = "Dear " + publishedAnnouncement.getAgent().getName() + ",\n" +
                        "I am unable to attend the scheduled property visit that was planned." +
                        "\nReason: " + reason + "\n\nProperty ID: " + publishedAnnouncement.getPropertyID() +
                        "\nLocated at: " + publishedAnnouncement.getProperty().getAddress().toString() +
                        "\nDate of visit: " + message.getInitialDate() + "\nStarting time at " + message.getInitialTime() +
                        " and ending at " + message.getEndTime() + "\n\nBest Regards,\n" + client.getName();
            }

            controller.sendVisualizedEmail(email, subject, body);
            System.out.println("Email successfully sent!");
        }
    }
}
