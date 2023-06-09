package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ListMessageController;
import pt.ipp.isep.dei.esoft.project.domain.BubbleSort;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MergeSort;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * The type List message ui.
 */
public class ListMessageUI implements Runnable{
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
@Override
    public void run() {
    System.out.println("Enter the begin date (dd-MM-yyyy): ");
    String beginDateString = input.nextLine();

    Date beginDate = parseDate(beginDateString);
    System.out.println("Enter the end date (dd-MM-yyyy):");
    String endDateString = input.nextLine();

    Date endDate = parseDate(endDateString);

    if (beginDate != null && endDate != null) {
        List<Message> messageRequests = controller.getBookingRequestsForPeriod(beginDate, endDate);
        try (InputStream input = new FileInputStream("C:\\Users\\35193\\Desktop\\PII\\config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            String sorting = (String) prop.getProperty("sortingAlgorithm");

            if(sorting.equals("bubbleSort")){
                BubbleSort<Message> bubbleSort = new BubbleSort<>(messageRequests);
                messageRequests = bubbleSort.sort(messageRequests);
            } else if(sorting.equals("mergeSort")) {
                MergeSort m = new MergeSort(messageRequests);
                m.divideArrayElements(0, messageRequests.size() -1);
                messageRequests = m.getArrayAfterSorting();
            }else {
                controller.sortBookingRequests(messageRequests);
            }
        } catch (IOException io) {
            controller.sortBookingRequests(messageRequests);
        }

        System.out.println("Booking Requests for the specified period (sorted by date in ascending order):");
        for (Message message : messageRequests) {
            System.out.println(message);
        }
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
