package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.SendMessageController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The type Send message ui.
 */
public class SendMessageUI implements Runnable {

    /**
     * The Controller.
     */
    public final SendMessageController controller = new SendMessageController();

    private final PublishAnnouncementController controllerPublishAnnouncement = new PublishAnnouncementController();

    private int initialTime;


    @Override
    public void run() {
        System.out.println("Send Message");


        PublishedAnnouncement announcement = requestChooseProperty();

        String message = requestMessage();
        String clientName = requestClientName();
        int clientsPhoneNumber = requestPhoneNumber();
        Date dateOfVisit = requestDate();
        initialTime = requestInitialTime();
        int endTime = requestEndTime();

        submitData(message, clientName, clientsPhoneNumber, dateOfVisit, initialTime, endTime, announcement);

        List<Message> messages = controller.getMessage();

        StringBuilder st = new StringBuilder();

        for (Message m : messages) {
            st.append(m.toString());
            st.append("\n");
        }

        System.out.println(st);
    }

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
                    index = input.nextInt()-1;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    index = -1;
                }
            } while (index < 0);

            if (index > publishedAnnouncements.size() + 1) {
                System.out.println(String.format("Invalid input. Please enter an value between 1 and %s:", publishedAnnouncements.size()));
                index = input.nextInt()-1;
            }
        } while (index < 0);
        return publishedAnnouncements.get(index);
    }

    private String requestClientName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Name:");
        return input.nextLine();
    }

    private String requestMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Write message to the agent:");
        return input.nextLine();
    }

    private int requestPhoneNumber() {
        Scanner input = new Scanner(System.in);
        String phoneNumberString;
        int phoneNumberInt;

        do {
            do {
                try {
                    System.out.println("Phone Number:");
                    phoneNumberInt = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer value:");
                    input.nextLine();
                    phoneNumberInt = -1;
                }
            } while (phoneNumberInt < 0);
            phoneNumberString = Integer.toString(phoneNumberInt);
            if (phoneNumberString.length() != 10) {
                System.out.println("A Phone Number is a number with 10 digits");
            }


        } while (phoneNumberString.length() != 10);
        return phoneNumberInt;
    }

    private Date requestDate() {
        Date date = null;

        boolean correct = false;
        do {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Date of visit (dd-MM-yyyy):");
                String dateInput = input.next();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                date = dateFormat.parse(dateInput);
                correct = true;
            } catch (Exception e) {
                System.out.println("The date is in the wrong format, please try again.");
            }
        } while(!correct);

        return date;
    }

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


    private void submitData(String message, String clientName, int clientsPhoneNumber, Date dateOfVisit,
                            int initialTime, int endTime, PublishedAnnouncement announcement) {
        controller.createNewMessageToAgent(clientName, message, clientsPhoneNumber, dateOfVisit, initialTime, endTime, announcement);
    }
}
