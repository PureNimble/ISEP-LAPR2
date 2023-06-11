package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type List message ui.
 */
public class ListMessageUI implements Runnable {
    /**
     * Scanner instance for user input.
     */
    private final Scanner input = new Scanner(System.in);
    /**
     * Controller for managing booking requests.
     */
    private final ListMessageController controller = new ListMessageController();
    /**
     * Runs the message request UI.
     */

    public void run() {
        System.out.println("Enter the begin date (dd-MM-yyyy): ");
        String beginDateString = input.nextLine();

        Date beginDate = parseDate(beginDateString);

        System.out.println("Enter the end date (dd-MM-yyyy): ");
        String endDateString = input.nextLine();
        Date endDate = parseDate(endDateString);

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

            try (InputStream input = new FileInputStream("C:\\Users\\35193\\Desktop\\PII\\config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                String sorting = prop.getProperty("sortingAlgorithm");

                if (sorting.equals("bubbleSort")) {
                    BubbleSort<Message> bubbleSort = new BubbleSort<>(messageRequests);
                    messageRequests = bubbleSort.sort(messageRequests);
                } else if (sorting.equals("mergeSort")) {
                    MergeSort<Message> mergeSort = new MergeSort<>(messageRequests);
                    messageRequests = mergeSort.sort(messageRequests);
                } else {
                    controller.sortMessageRequests(messageRequests);
                }
            } catch (IOException e) {
                controller.sortMessageRequests(messageRequests);
            }

            System.out.println("\n\nBooking Requests for the specified period (sorted by date in ascending order):\n");
            for (int i = 0; i < messageRequests.size(); i++) {
                Message message = messageRequests.get(i);
                System.out.println((i + 1) + ". " + message.toString());
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

    private void respondToClientOrNo(Message message, PublishedAnnouncement publishedAnnouncement) {
        System.out.println("\n1. Respond");
        System.out.println("2. Cancel");

        int choice;
        do {
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    boolean isValidDomain = false;
                    String email;
                    do {
                        System.out.println("Enter client email: ");
                        email = input.nextLine();
                        isValidDomain = controller.isValidEmailDomain(email);
                        if (!isValidDomain) {
                            System.out.println("Invalid client email domain. Please provide an email with one of the supported domains.");
                        }
                    } while (!isValidDomain);

                    if (controller.isValidEmailDomain(email)) {
                        // Continue with the code for responding to the message
                        String subject = null;
                        String body = null;

                        // Prompt for acceptance or denial
                        System.out.println("\n1. Accept");
                        System.out.println("2. Deny");

                        int responseChoice;
                        do {
                            System.out.println("Enter your response: ");
                            responseChoice = input.nextInt();
                            input.nextLine(); // Consume the newline character

                            switch (responseChoice) {
                                case 1:
                                    // Accept the visitation
                                    subject = "Your Visit Booking Request Has Been Accepted";
                                    body = "Dear Customer, \n\n" +
                                            "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                                            " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                                            "You had requested a visit for the date: " + message.getInitialDate() +
                                            " with a start time at: " + message.getInitialTime() +
                                            " and ending at: " + message.getEndTime() + ".\n\n" +
                                            "We are pleased to inform you that your booking request has been accepted. You will be greeted by our agent " + publishedAnnouncement.getAgent().getName() + ".\n" +
                                            "In case of any changes or queries, you may contact them at the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                                            "We look forward to welcoming you for the visit.\n\n" +
                                            "Best Regards,\n" +
                                            publishedAnnouncement.getAgent().getName();

                                    message.setApprovedByAgent(true);
                                    break;
                                case 2:
                                    // Deny the visitation
                                    subject = "Your Visit Booking Request Has Been Rejected";
                                    System.out.println("Reason for denying the visit request: ");
                                    String reason = input.nextLine();
                                    body = "Dear Customer, \n\n" +
                                            "Thank you for your interest in the property listed with ID: " + publishedAnnouncement.getPropertyID() +
                                            " and located at: " + publishedAnnouncement.getProperty().getAddress().toString() + ".\n\n" +
                                            "You had requested a visit for the date: " + message.getInitialDate() +
                                            " with a start time at: " + message.getInitialTime() +
                                            " and ending at: " + message.getEndTime() + ".\n\n" +
                                            "We regret to inform you that your booking request has been rejected for the following reason:\n\n" +
                                            reason + "\n\n" +
                                            "If you have any doubts and need help, you may contact the agent " + publishedAnnouncement.getAgent().getName() +
                                            " with the following number: " + publishedAnnouncement.getAgent().getPhoneNumber() + ".\n\n" +
                                            "Best Regards,\n" +
                                            publishedAnnouncement.getAgent().getName();

                                    message.setApprovedByAgent(false);
                                    break;
                                default:
                                    System.out.println("Invalid response. Please enter 1 or 2.");
                                    break;
                            }
                        } while (responseChoice != 1 && responseChoice != 2);

                        // Send the email
                        sendEmail(email, subject, body);
                        controller.removeBookingRequest(message);
                        return;
                    } else {
                        System.out.println("Invalid client email domain. Please provide an email with one of the supported domains.");
                    }
                    break;
                case 2:
                    // Cancel and return to the menu
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    break;
            }
        } while (true);
    }


    private void sendEmail(String clientEmail, String subject, String body) {
        String fileName = "EmailNotification - " + clientEmail + ".txt";
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String agentEmail = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("From: " + agentEmail);
            writer.println("To: " + clientEmail);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }

    /**
     * Parses a string to a Date object.
     *
     * @param dateString The string representing the date.
     * @return The parsed Date object, or null if the parsing failed.
     */
    private Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            return null;
        }
    }
}
