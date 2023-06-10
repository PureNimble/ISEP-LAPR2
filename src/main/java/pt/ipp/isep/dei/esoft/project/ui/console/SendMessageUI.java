package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.SendMessageController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The type Send message ui.
 */
public class    SendMessageUI implements Runnable {

    /**
     * The Controller.
     */
    public final SendMessageController controller = new SendMessageController();

    /**
     * The controller responsible for publishing announcements.
     */
    private final PublishAnnouncementController controllerPublishAnnouncement = new PublishAnnouncementController();

    /**
     * The initial time for the announcement.
     */
    private int initialTime;

    /**
     * Executes the "run" method of the class.
     * This method is responsible for sending a message by gathering necessary information from the user and interacting with the controller and repository.
     * It prompts the user to choose a property, enter a message, client name, phone number, date of visit, initial time, and end time.
     * It then submits the data to the controller for processing and retrieval of messages.
     * The retrieved messages are formatted into a StringBuilder and printed to the console.
     */
    @Override
    public void run() {
        System.out.println("Send Message");


        PublishedAnnouncement announcement = requestChooseProperty();

        String message = requestMessage();
        String clientName = requestClientName();
        long clientsPhoneNumber = requestPhoneNumber();
        Date dateOfVisit = requestDate();
        initialTime = requestInitialTime();
        int endTime = requestEndTime();

        submitData(message, clientName, clientsPhoneNumber, dateOfVisit, initialTime, endTime, announcement, MessageState.UNANSWERED);

        List<Message> messages = controller.getMessage();

        /*StringBuilder st = new StringBuilder();*/

//        for (Message m : messages) {
//            st.append(m.toString());
//            st.append("\n");
//        }

//        System.out.println(st);
    }

    /**
     * Requests the user to choose a property from the published announcements.
     *
     * @return The selected published announcement.
     */

    private PublishedAnnouncement requestChooseProperty() {
        Scanner input = new Scanner(System.in);
        List<PublishedAnnouncement> publishedAnnouncements = controllerPublishAnnouncement.getPublishedAnnouncementsDesc();
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

    /**
     * Requests the client name from the user.
     *
     * @return The client name.
     */

    private String requestClientName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name:");
        return input.nextLine();
    }

    /**
     * Requests the message to be sent to the agent from the user.
     *
     * @return The message.
     */

    private String requestMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Write message to the agent:");
        return input.nextLine();
    }

    /**
     * Requests the client's phone number from the user.
     *
     * @return The client's phone number.
     */

    private long requestPhoneNumber() {
        Scanner input = new Scanner(System.in);
        String phoneNumberString;
        long phoneNumberLong;

        do {
            do {
                try {
                    System.out.println("Phone Number:");
                    phoneNumberLong = input.nextLong();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    phoneNumberLong = -1;
                }
            } while (phoneNumberLong < 0);
            phoneNumberString = Long.toString(phoneNumberLong);
            if (phoneNumberString.length() != 10) {
                System.out.println("A Phone Number is a number with 10 digits");
            }


        } while (phoneNumberString.length() != 10);
        return phoneNumberLong;
    }

    /**
     * Requests the date of the visit from the user.
     *
     * @return The date of the visit.
     */

    private Date requestDate() {
        Date date = null;

        boolean correct = false;
        do {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Date of visit (dd-MM-yyyy):");
                String dateInput = input.next();

                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate selectedDate = LocalDate.parse(dateInput, formatter);

                if (selectedDate.isAfter(currentDate)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    date = dateFormat.parse(dateInput);
                    correct = true;
                } else {
                    System.out.println("That day has already passed. Please choose another date:");
                }
            } catch (Exception e) {
                System.out.println("The date is in the wrong format, please try again.");
            }
        } while (!correct);

        return date;
    }

    /**
     * Requests the initial time for the visit from the user.
     *
     * @return The initial time.
     */

    private int requestInitialTime() {
        Scanner input = new Scanner(System.in);

        int initialTimeInt;

        do {
            do {
                try {
                    System.out.println("Initial time for visit in hours (from 0 to 24):");
                    initialTimeInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    initialTimeInt = -1;
                }

            } while (initialTimeInt < 0);

            if (initialTimeInt >= 25) {
                System.out.println("Please insert an hour from 0 to 24.");
            }
        } while (initialTimeInt < 0);
        return initialTimeInt;
    }

    /**
     * Requests the end time for the visit from the user.
     *
     * @return The end time.
     */

    private int requestEndTime() {
        Scanner input = new Scanner(System.in);
        int endTimeInt;

        do {
            do {
                do {

                    try {
                        System.out.println("End time for visit in hours (from 0 to 24):");
                        endTimeInt = input.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer value:");
                        input.nextLine();
                        endTimeInt = -1;
                    }

                } while (endTimeInt < 0);

                if (endTimeInt >= 25) {
                    System.out.println("Please insert an hour from 0 to 24.");
                    endTimeInt = input.nextInt();
                }

            } while (endTimeInt < 0);

            if (endTimeInt <= initialTime) {
                System.out.println("Please enter a time that is after the previously selected initial time.");
                endTimeInt = input.nextInt();
            }
        } while (endTimeInt < 0);

        return endTimeInt;
    }

    /**
     * Submits the collected data to create a new message to the agent.
     *
     * @param message            The message.
     * @param clientName         The client name.
     * @param clientsPhoneNumber The client's phone number.
     * @param dateOfVisit        The date of the visit.
     * @param initialTime        The initial time of the visit.
     * @param endTime            The end time of the visit.
     * @param announcement       The published announcement.
     */

    private void submitData(String message, String clientName, long clientsPhoneNumber, Date dateOfVisit,
                            int initialTime, int endTime, PublishedAnnouncement announcement, MessageState messageState) {
        controller.createNewMessageToAgent(clientName, message, clientsPhoneNumber, dateOfVisit, initialTime, endTime, announcement, messageState);
    }
}
