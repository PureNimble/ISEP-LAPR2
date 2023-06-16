package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.sortAlgorithms.SortAlgorithm;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User interface for listing and responding to messages.
 */

public class ListMessageUI implements Runnable {
    private final Scanner input = new Scanner(System.in);
    private final ListMessageController controller = new ListMessageController();

    private final MessageRepository repository = new MessageRepository();

    /**
     * Runs the user interface.
     */

    public void run() {
        System.out.println("Enter the begin date (dd-MM-yyyy): ");
        Date beginDate = parseDate(input.nextLine());
        while (beginDate == null) {
            System.out.println("Enter the begin date (dd-MM-yyyy): ");
            beginDate = parseDate(input.nextLine());
        }

        System.out.println("Enter the end date (dd-MM-yyyy): ");
        Date endDate = parseDate(input.nextLine());
        while (endDate == null) {
            System.out.println("Enter the end date (dd-MM-yyyy): ");
            endDate = parseDate(input.nextLine());
        }

        if (beginDate != null && endDate != null) {
            if (endDate.before(beginDate)) {
                System.out.println("\n\nInvalid date range. The end date must be after the begin date.");
                return;
            }

            List<Message> messageRequests = controller.getMessageRequestsForPeriod(beginDate, endDate);

            if (messageRequests.isEmpty()) {
                System.out.println("\n\nNo booking requests found for the specified period.");
                return;
            }

            try (FileInputStream input = new FileInputStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                String sortingClass = prop.getProperty("sortingAlgorithmClass");

                if (sortingClass != null) {
                    Class<?> sortAlgorithmClass = Class.forName(sortingClass);
                    Constructor<?> constructor = sortAlgorithmClass.getConstructor(List.class);
                    SortAlgorithm<Message> sortAlgorithm = (SortAlgorithm<Message>) constructor.newInstance(messageRequests);
                    messageRequests = sortAlgorithm.sort(messageRequests);
                } else {
                    controller.sortMessageRequests(messageRequests);
                }
            } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                     IllegalAccessException | InvocationTargetException e) {
                controller.sortMessageRequests(messageRequests);
            }

            System.out.println("\n\nBooking Requests for the specified period (sorted by date in ascending order):\n");
            int count = 0;
            for (int i = 0; i < messageRequests.size(); i++) {
                Message message = messageRequests.get(i);
                if (message.getMessageState() == MessageState.ANSWERED) {
                    continue; // Skip the message with ANSWERED state
                }
                count++;
                System.out.println(count + ". " + message);
            }

            int choice;
            do {
                System.out.println("\nEnter the message number to respond (0 to cancel): ");
                choice = input.nextInt();
                input.nextLine(); // Consume the newline character

                if (choice >= 1 && choice <= messageRequests.size()) {
                    Message selectedMessage = messageRequests.get(choice - 1);
                    PublishedAnnouncement publishedAnnouncement = selectedMessage.getPublishedAnnouncement();
                    respondToClientOrNo(selectedMessage, publishedAnnouncement);
                    break; // Exit the loop and return to the menu
                } else if (choice != 0) {
                    System.out.println("Invalid message number. Please try again.");
                }
            } while (choice != 0);
        }
    }

    /**
     * Responds to the client by accepting or denying the visitation request.
     *
     * @param message              the message to respond to
     * @param publishedAnnouncement the published announcement related to the message
     */

    public void respondToClientOrNo(Message message, PublishedAnnouncement publishedAnnouncement) {
        System.out.println("\n1. Respond");
        System.out.println("2. Cancel");

        int choice;
        do {
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (choice == 1) {
                processClientResponse(message, publishedAnnouncement);
            } else if (choice == 2) {
                cancelOperation();
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 1 && choice != 2);
    }

    /**
     * Processes the client's response to the visitation request.
     *
     * @param message              the message to respond to
     * @param publishedAnnouncement the published announcement related to the message
     */

    private void processClientResponse(Message message, PublishedAnnouncement publishedAnnouncement) {
        String email = getClientEmail();
        if (email != null) {
            int responseChoice = getResponseChoice();
            if (responseChoice == 1) {
                acceptVisitation(message, publishedAnnouncement, email);
            } else if (responseChoice == 2) {
                denyVisitation(message, publishedAnnouncement, email);
            } else {
                System.out.println("Invalid choice. Please enter a valid response choice.");
            }
        }
    }

    /**
     * Gets the email address of the client.
     *
     * @return the client's email address
     */

    private String getClientEmail() {
        String email = null;
        boolean isValidEmail = false;

        while (!isValidEmail) {
            System.out.println("Enter client email: ");
            email = input.nextLine();

            // Validate email format
            if (email.matches("^[A-Za-z0-9]+[A-Za-z0-9._]*@[A-Za-z0-9]+(\\.[A-Za-z]+[A-Za-z0-9]*)+[A-Za-z]$")) {
                isValidEmail = true;
            } else {
                System.out.println("Please enter a valid email address (e.g., example@example.com).");
            }
        }

        return email;
    }

    /**
     * Gets the response choice from the client.
     *
     * @return the response choice (1 for accept, 2 for deny)
     */

    private int getResponseChoice() {
        System.out.println("\n1. Accept");
        System.out.println("2. Deny");

        int responseChoice;
        do {
            System.out.println("Enter your response: ");
            try {
                responseChoice = input.nextInt();
                input.nextLine(); // Consume the newline character

                if (responseChoice != 1 && responseChoice != 2) {
                    System.out.println("Invalid response. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid response (1 or 2).");
                input.nextLine(); // Clear the input buffer
                responseChoice = -1; // Set responseChoice to an invalid value to repeat the loop
            }
        } while (responseChoice != 1 && responseChoice != 2);

        return responseChoice;
    }

    /**
     * Accepts the visitation request and sends a confirmation email to the client.
     *
     * @param message              the message to respond to
     * @param publishedAnnouncement the published announcement related to the message
     * @param email                the email address of the client
     */

    private void acceptVisitation(Message message, PublishedAnnouncement publishedAnnouncement, String email) {
        String subject = "Your Visit Booking Request Has Been Accepted";
        String body = "Dear Customer, \n\n" +
                "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                "You had requested a visit for the date: " + message.getInitialDate() +
                ", starting at: " + message.getInitialTime() +
                " and ending at: " + message.getEndTime() + ".\n\n" +
                "We are pleased to inform you that your booking request has been accepted. You will be greeted by our agent " + publishedAnnouncement.getAgent().getName() + ".\n" +
                "In case of any changes or queries, you may contact the agent at the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                "We look forward to welcoming you for the visit!\n\n" +
                "Best Regards,\n" +
                publishedAnnouncement.getAgent().getName();

        message.setMessageState(MessageState.ANSWERED);
        message.setApprovedByAgent(true);
        repository.updateMessageState(message);

        // Send the email
        controller.sendEmail(email, subject, body);

        // Remove the message from the list
        controller.removeMessage(message);
    }

    /**
     * Denies the visitation request and sends a rejection email to the client.
     *
     * @param message              the message to respond to
     * @param publishedAnnouncement the published announcement related to the message
     * @param email                the email address of the client
     */

    private void denyVisitation(Message message, PublishedAnnouncement publishedAnnouncement, String email) {
        String subject = "Your Visit Booking Request Has Been Rejected";
        String reason = getRejectionReason();

        String body = "Dear Customer, \n\n" +
                "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                "You had requested a visit for the date: " + message.getInitialDate() +
                ", starting at: " + message.getInitialTime() +
                " and ending at: " + message.getEndTime() + ".\n\n" +
                "We regret to inform you that your booking request has been rejected for the following reason:\n\n" +
                reason + "\n\n" +
                "If you have any doubts and need help, you may contact the agent " + publishedAnnouncement.getAgent().getName() +
                " at the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                "Thank you for your understanding.\n\n" +
                "Best Regards,\n" +
                publishedAnnouncement.getAgent().getName();

        message.setMessageState(MessageState.ANSWERED);
        message.setApprovedByAgent(false);
        repository.updateMessageState(message); // Add this line to update the message state

        // Send the email
        controller.sendEmail(email, subject, body);

        // Remove the message from the list
        controller.removeMessage(message);
    }

    /**
     * Gets the reason for rejecting the visitation request.
     *
     * @return the reason for rejection
     */

    private String getRejectionReason() {
        System.out.println("Enter the reason for rejection: ");
        return input.nextLine();
    }

    /**
     * Cancels the operation and returns to the message list.
     */

    private void cancelOperation() {
        System.out.println("Operation canceled.");
    }

    /**
     * Parses a string date in the format dd-MM-yyyy and returns a Date object.
     *
     * @param dateString the string representation of the date
     * @return the parsed Date object, or null if the date format is invalid
     */

    private Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the format dd-MM-yyyy.");
            return null;
        }
    }
}
