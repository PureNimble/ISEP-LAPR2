# US 006 - To create a Task 

# 4. Tests 

(...)

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

## Class ListMessageController 

```java
package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.emailServices.EmailService;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * The type List message controller.
 */
public class ListMessageController {

    private List<Message> messageRequests;
    /**
     * The Message repository.
     */
    MessageRepository messageRepository = null;
    /**
     * The Authentication repository.
     */
    AuthenticationRepository authenticationRepository = null;
    /**
     * The User repository.
     */
    UserRepository userRepository = null;
    //private UserRepository userRepository;

    /**
     * Instantiates a new List message controller.
     */
    public ListMessageController() {
        getMessageRepository();
        getAuthenticationRepository();
        getUserRepository();
        messageRequests = new ArrayList<>();

    }

    /**
     * Instantiates a new List message controller.
     *
     * @param messageRepository        the message repository
     * @param authenticationRepository the authentication repository
     * @param userRepository           the user repository
     */
    public ListMessageController(MessageRepository messageRepository, AuthenticationRepository authenticationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves the MessageRepository instance.
     *
     * @return The MessageRepository instance.
     */
    private MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();

            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }

    /**
     * Retrieves the AuthenticationRepository instance.
     *
     * @return The AuthenticationRepository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();

            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    /**
     * Retrieves the UserRepository instance.
     *
     * @return The UserRepository instance.
     */
    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }

    /**
     * Sort booking requests.
     *
     * @param messageRequests the booking requests
     */
    public void sortMessageRequests(List<Message> messageRequests) {
        Collections.sort(messageRequests, Comparator.comparing(Message::getInitialDate));
    }


    /**
     * Gets booking requests for period.
     *
     * @param beginDate the begin date
     * @param endDate   the end date
     * @return the booking requests for period
     */
    public List<Message> getMessageRequestsForPeriod(Date beginDate, Date endDate) {
        MessageRepository messageRepository = getMessageRepository();
        List<Message> messageRequests = new ArrayList<>();

        for (Message message : messageRepository.getMessages()) {
            if (message.getInitialDate().compareTo(beginDate) >= 0 && message.getInitialDate().compareTo(endDate) <= 0) {
                messageRequests.add(message);
            }
        }

        return messageRequests;
    }

    /**
     * Gets messages by ascending date.
     *
     * @return the messages by ascending date
     */
    public List<Message> getMessagesByAscendingDate() {
        MessageRepository messageRepository = getMessageRepository();
        return messageRepository.getMessagesByAscendingDate();
    }

    /**
     * Update message state.
     *
     * @param message the message
     */

    /**
     * Remove message.
     *
     * @param message the message
     */
    public void removeMessage(Message message) {
        messageRequests.remove(message);
    }

    /**
     * Sends an email using the EmailService.
     *
     * @param email   the email address of the client
     * @param subject the subject of the email
     * @param body    the body of the email
     */
    public void sendEmail(String email, String subject, String body) {
        // Load configuration properties
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Retrieve the email service class name from properties
        String emailServiceClass = properties.getProperty("emailService");

        // Instantiate the email service
        EmailService emailService;
        try {
            Class<?> serviceClass = Class.forName(emailServiceClass);
            emailService = (EmailService) serviceClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            System.out.println("Failed to instantiate the email service: " + e.getMessage());
            return;
        }

        // Send the email using the email service
        if (email != null) {
            emailService.sendEmail(email, subject, body);
            System.out.println("Email sent successfully.");
        }
    }




}
```


## Class ListMessageUI

```java
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
```

## Class MessageRepository

```java
package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;


import java.io.Serializable;
import java.util.*;

/**
 * The type Message repository.
 */
public class MessageRepository implements Serializable {

    private ArrayList<Message> messages = new ArrayList<>();

    /**
     * Add optional.
     *
     * @param message the message
     * @return the optional
     */
    public Optional<Message> add(Message message) {

        Optional<Message> newMessage = Optional.empty();
        boolean operationSuccess = false;

        if (validateMessage(message)) {
            newMessage = Optional.of(message);
            operationSuccess = messages.add(newMessage.get());
        }

        if (!operationSuccess) {
            newMessage = Optional.empty();
        }
        return newMessage;
    }

    private boolean validateMessage(Message message) {
        for (Message message1 : messages) {
            if (message1.getPhoneNumber() == message.getPhoneNumber() && checkIfDateTimeOverlaps(message, message1)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfDateTimeOverlaps(Message message1, Message message2) {
        Date initialDate1 = message1.getInitialDate();
        int initialTime1 = message1.getInitialTime();
        int endTime1 = message1.getEndTime();

        Date initialDate2 = message2.getInitialDate();
        int initialTime2 = message2.getInitialTime();
        int endTime2 = message2.getEndTime();

        // Check if the time ranges overlap
        return !(endTime1 <= initialTime2 || initialTime1 >= endTime2);
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    /**
     * Gets messages by ascending date.
     *
     * @return the messages by ascending date
     */
    public List<Message> getMessagesByAscendingDate() {
        List<Message> resultList = new ArrayList<Message>();

        for (Message message : messages) {
            if (message.getMessageState().equals(MessageState.UNANSWERED)) {
                resultList.add(message);
            }
        }

/**
 * Sorts a list of messages based on their initial date in ascending order.
 *
 * @param messages The list of messages to be sorted.
 * @return The sorted list of messages.
 */
        resultList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                Date date1 = o1.getInitialDate();
                Date date2 = o2.getInitialDate();

                return date1.compareTo(date2);

            }
        });
        return resultList;
    }

    /**
     * @return A List of Message objects representing the answered message requests.
     */
    public List<Message> getMessageRequests(Client client) {
        List<Message> messageList = messages;
        List<Message> messageRequests = new ArrayList<>();

        for (Message message : messageList) {
            if (message.getMessageState().equals(MessageState.ANSWERED) && message.getName().equals(client.getName())) {
                messageRequests.add(message);
            }
        }

        return messageRequests;
    }


    /**
     * Update message state.
     *
     * @param message the message
     */
    public void updateMessageState(Message message) {
        message.setMessageState(MessageState.ANSWERED);
    }


}
```

## Class Message

```java
    package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * The Message class represents a messsage with a description, a name, a phone number, an initial date, initial time, an end date and an end time.
 */
public class Message implements Serializable {

    /**
     The name of the client.
     */

    private String name;

    /**

     The phone number of the client.
     */

    private long phoneNumber;

    /**

     The description of the message.
     */

    private String description;

    /**

     The initial date for visiting.
     */

    private Date initialDate;

    /**

     The initial time for visiting.
     */

    private int initialTime;

    /**

     The end time for visiting.
     */

    private int endTime;

    private PublishedAnnouncement publishedAnnouncement;

    private MessageState messageState;

    private boolean isApprovedByAgent;


    /**
     * Constructs a new Message object with the specified parameters.
     *
     * @param name                  the client's name associated with the message
     * @param phoneNumber           the client's phone number associated with the message
     * @param description           the description of the message
     * @param initialDate           the initial date for visiting
     * @param initialTime           the initial time for visiting
     * @param endTime               the end time for visiting
     * @param publishedAnnouncement the published announcement
     * @param messageState          the message state
     * @param isApprovedByAgent     the is approved by agent
     */
    public Message(String name, long phoneNumber, String description, Date initialDate, int initialTime, int endTime, PublishedAnnouncement publishedAnnouncement, MessageState messageState, boolean isApprovedByAgent) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.initialDate = initialDate;
        this.initialTime = initialTime;
        this.endTime = endTime;
        this.publishedAnnouncement = publishedAnnouncement;
        this.messageState = messageState;
        this.isApprovedByAgent = isApprovedByAgent;

    }

    /**
     * Constructs an empty Message object.
     */
    public Message(){
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets is approved by agent.
     *
     * @return the is approved by agent
     */
    public Boolean getIsApprovedByAgent() {
        return isApprovedByAgent;
    }

    /**
     * Sets is approved by agent.
     *
     * @param isApprovedByAgent the is approved by agent
     */
    public void setIsApprovedByAgent(Boolean isApprovedByAgent) {
        this.isApprovedByAgent = isApprovedByAgent;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets initial date.
     *
     * @return the initial date
     */
    public Date getInitialDate() {
        return initialDate;
    }

    /**
     * Sets initial date.
     *
     * @param initialDate the initial date
     */
    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    /**
     * Gets initial time.
     *
     * @return the initial time
     */
    public int getInitialTime() {
        return initialTime;
    }

    /**
     * Sets initial time.
     *
     * @param initialTime the initial time
     */
    public void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets published announcement.
     *
     * @return the published announcement
     */
    public PublishedAnnouncement getPublishedAnnouncement() {
        return publishedAnnouncement;
    }

    /**
     * Sets published announcement.
     *
     * @param publishedAnnouncement the published announcement
     */
    public void setPublishedAnnouncement(PublishedAnnouncement publishedAnnouncement) {
        this.publishedAnnouncement = publishedAnnouncement;
    }

    /**
     * Gets message state.
     *
     * @return the message state
     */
    public MessageState getMessageState() {
        return messageState;
    }

    /**
     * Sets message state.
     *
     * @param messageState the message state
     */
    public void setMessageState(MessageState messageState) {
        this.messageState = messageState;
    }

    /**
     * Is approved by agent boolean.
     *
     * @return the boolean
     */
    public boolean isApprovedByAgent() {
        return isApprovedByAgent;
    }

    /**
     * Sets approved by agent.
     *
     * @param approvedByAgent the approved by agent
     */
    public void setApprovedByAgent(boolean approvedByAgent) {
        isApprovedByAgent = approvedByAgent;
    }

    /**
     * Returns a string representation of the Message object.
     *
     * @return a string containing information about the client's visit schedule and description
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(initialDate);
        return String.format("Message: \nThe client %s, with phone number %s, wants to schedule a visit from %s until %s at %s. \n\nDescription: \n%s \n\nProperty: \n%s", name, phoneNumber, initialTime, endTime, date, description, publishedAnnouncement.toString(), messageState);
    }

    /**
     * Checks if the given object is equal to this Message object.
     *
     * @param o The object to compare with.
     * @return True if the object is equal to this Message object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;
        return phoneNumber == message.phoneNumber && initialTime == message.initialTime && endTime == message.endTime && Objects.equals(name, message.name) && Objects.equals(description, message.description) && Objects.equals(initialDate, message.initialDate) && Objects.equals(publishedAnnouncement, message.publishedAnnouncement) && messageState == message.messageState;
    }
    /**
     * Generates the hash code for this Message object.
     *
     * @return The hash code value for this Message object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, description, initialDate, initialTime, endTime, publishedAnnouncement, messageState);
    }
}

```

## Class GmailService

```java
package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.io.*;
import java.util.Properties;

/**
 * The type Gmail service.
 */
public class GmailService implements EmailService {

    private static final String FILE_NAME = "GmailNotification.txt";

    public void sendEmail(String email, String subject, String body) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String from = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {
            writer.println("From: " + from);
            writer.println("To: " + email);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
            writer.println();
            writer.println("-----------------------------------------------------------------------");
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }
}

```

## Class HotmailService

```java
package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.io.*;
import java.util.Properties;

/**
 * The type Hotmail service.
 */
public class HotmailService implements EmailService {

    private static final String FILE_NAME = "HotmailNotification.txt";

    public void sendEmail(String email, String subject, String body) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String from = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {
            writer.println("From: " + from);
            writer.println("To: " + email);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
            writer.println();
            writer.println("-----------------------------------------------------------------------");
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }
}

```

## Class DEIService

```java
package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.io.*;
import java.util.Properties;

/**
 * The type Dei service.
 */
public class DEIService implements EmailService,Serializable {

    private static final String FILE_NAME = "DEINotification.txt";

    public void sendEmail(String email, String subject, String body) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String from = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {
            writer.println("From: " + from);
            writer.println("To: " + email);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
            writer.println();
            writer.println("-----------------------------------------------------------------------");
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }
}

```

## Class YahooService

```java
package pt.ipp.isep.dei.esoft.project.domain.emailServices;

import java.io.*;
import java.util.Properties;

/**
 * The type Yahoo service.
 */
public class YahooService implements EmailService {

    private static final String FILE_NAME = "YahooNotification.txt";

    public void sendEmail(String email, String subject, String body) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return;
        }

        // Get the agent's email from the properties file
        String from = properties.getProperty("from");
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(FILE_NAME, true))) {
            writer.println("From: " + from);
            writer.println("To: " + email);
            writer.println("Subject: " + subject);
            writer.println("Body: " + body);
            writer.println();
            writer.println("-----------------------------------------------------------------------");
            writer.println();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write email to file: " + e.getMessage());
        }
    }
}
```

# 6. Integration and Demo 

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain. 

Is there any way to avoid this to happen?





