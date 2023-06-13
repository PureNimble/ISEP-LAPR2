package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.repository.*;

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

/*    public void sendVisualizedEmail(String email, String subject, String body) {
        EmailNotificationAdapter.sendEmail(email, subject, body);
    }*/

    public Client getClient() {
        String email = getCurrentSessionEmail();
        return getUserRepository().getClientEmail(email);
    }

    public String getCurrentSessionEmail() {
        AuthenticationRepository authenticationRepository = getAuthenticationRepository();
        return authenticationRepository.getCurrentUserSession().getUserId().getEmail();
    }

    public List<Message> getMessageRequests() {
        MessageRepository messageRepository = getMessageRepository();
        List<Message> messageList = messageRepository.getMessages();
        List<Message> messageRequests = new ArrayList<>();
        
        for (Message message : messageList) {
            if (message.getMessageState().equals(MessageState.ANSWERED)) {
                messageRequests.add(message);
            }
        }

        return messageRequests;
    }

}
