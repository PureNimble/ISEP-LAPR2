package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageDto;
import pt.ipp.isep.dei.esoft.project.domain.MessageMapper;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.StoreEmployeeMapper;
import pt.ipp.isep.dei.esoft.project.domain.emailServices.EmailService;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * The type List message controller.
 */
public class ClientMessagesController {
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
    public ClientMessagesController() {
        getMessageRepository();
        getAuthenticationRepository();
        getUserRepository();
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

    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();

            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }

    public boolean sendVisualizedEmail(String email, String subject, String body) {
        // Load configuration properties
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the configuration file: " + e.getMessage());
            return false;
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
            return false;
        }

        if (email == null) {
            return false;
        } else {
            // Send the email using the email service
            emailService.sendEmail(email, subject, body);
        }
        return true;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        String email = getCurrentSessionEmail();
        return getUserRepository().getClientEmail(email);
    }

    /**
     * Gets current session email.
     *
     * @return the current session email
     */
    public String getCurrentSessionEmail() {
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

    /**
     * Gets message requests.
     *
     * @return the message requests
     */
    public List<MessageDto> getMessageRequests(Client client) {
        MessageMapper messageMapper = new MessageMapper();
        MessageRepository messageRepository = getMessageRepository();
        List<Message> messageRequests = messageRepository.getMessageRequests(client);
        return messageMapper.toDto(messageRequests);
    }

}
