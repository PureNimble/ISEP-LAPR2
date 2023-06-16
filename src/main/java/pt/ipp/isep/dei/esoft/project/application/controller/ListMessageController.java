package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageDto;
import pt.ipp.isep.dei.esoft.project.domain.MessageMapper;
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

    public void updateMessageState(Message message) {
        message.setMessageState(MessageState.ANSWERED);
    }

    public void removeMessage(Message message) {
        messageRequests.remove(message);
        messageRepository.removeMessage(message);
    }
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
