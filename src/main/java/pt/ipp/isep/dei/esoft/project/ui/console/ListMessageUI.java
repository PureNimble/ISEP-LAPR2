package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ListMessageUI implements Runnable{

    private final Scanner input = new Scanner(System.in);

    private final ListMessageController controller = new ListMessageController();

@Override
    public void run() {
    System.out.println("Enter the begin date (dd-MM-yyyy): ");
    String beginDateString = input.nextLine();

    Date beginDate = parseDate(beginDateString);
    System.out.println("Enter the end date (dd-MM-yyyy):");
    String endDateString = input.nextLine();

    Date endDate = parseDate(endDateString);

    if (beginDate != null && endDate != null) {
        List<Message> bookingRequests = controller.getBookingRequestsForPeriod(beginDate, endDate);
        controller.sortBookingRequests(bookingRequests);

        System.out.println("Booking Requests for the specified period (sorted by date in ascending order):");
        for (Message message : bookingRequests) {
            System.out.println(message);
        }
    }

//    List<Message> messages = controller.getMessage();
//      StringBuilder st = new StringBuilder();
//
//        for (Message m : messages) {
//            st.append(m.toString());
//            st.append("\n");
//        }
//
//        System.out.println(st);
}

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
