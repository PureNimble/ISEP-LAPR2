package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.PublishAnnouncementController;
import pt.ipp.isep.dei.esoft.project.application.controller.SendMessageController;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The type Send message ui.
 */
public class SendMessageUI implements Runnable{

    /**
     * The Controller.
     */
    public final SendMessageController controller = new SendMessageController();

    private final PublishAnnouncementController controllerPublishAnnouncement = new PublishAnnouncementController();


    @Override
    public void run() {
        System.out.println("Send Message");


        PublishedAnnouncement announcement = requestChooseProperty();

        String message = requestMessage();
        String clientName = requestClientName();
        int clientsPhoneNumber = requestPhoneNumber();
        Date dateOfVisit = requestDate();
        int initialTime = requestInitialTime();
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
        for(int i=0; i<publishedAnnouncements.size(); i++) {
            sb.append(i+1 + " - ");
            sb.append(publishedAnnouncements.get(i).toString());
            sb.append("\n");
        }
        System.out.println(sb);
        System.out.println("Choose one of the properties above.");
        int index = Integer.parseInt(input.nextLine())-1;
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
        System.out.println("Phone Number:");
        return Integer.parseInt(input.nextLine());
    }

    private Date requestDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Date of visit:");
        String dateInput = input.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try{
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    private int requestInitialTime() {
        Scanner input = new Scanner(System.in);
        System.out.println("Initial time for visit in hours:");
        return Integer.parseInt(input.nextLine());
    }

    private int requestEndTime() {
        Scanner input = new Scanner(System.in);
        System.out.println("Final time for visit in hours:");
        return Integer.parseInt(input.nextLine());
    }


    private void submitData(String message, String clientName, int clientsPhoneNumber, Date dateOfVisit, int initialTime, int endTime, PublishedAnnouncement announcement) {
        controller.createNewMessageToAgent(clientName, message, clientsPhoneNumber, dateOfVisit, initialTime, endTime, announcement);
    }
}
