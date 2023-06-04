package pt.ipp.isep.dei.esoft.project.repository;

import org.controlsfx.control.ListActionView;
import pt.ipp.isep.dei.esoft.project.domain.Message;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Message repository.
 */
public class MessageRepository {

    private ArrayList<Message> messages = new ArrayList<>();
    private AuthenticationRepository authenticationRepository = null;

    /**
     * Add optional.
     *
     * @param message the message
     * @return the optional
     */
    public Optional<Message> add(Message message) {

        Optional<Message> newMessage = Optional.empty();
        boolean operationSuccess = false;

// Validate the Employee object and add it to the repository if it is valid
        if (validateMessage(message)) {
            newMessage = Optional.of(message);
            operationSuccess = messages.add(newMessage.get());
            System.out.println("\n\nMessage sent with success!\n\n");
        }

// Return an empty Optional if the operation was not successful
        if (!operationSuccess) {
            System.out.println("Could not send message, a visit is already scheduled for the same hours \n\n All Messages: \n");
            newMessage = Optional.empty();
        }

        return newMessage;
    }

    private boolean validateMessage(Message message) {
        for (Message message1 : messages) {
            if (message1.getPhoneNumber() == message.getPhoneNumber() && message1.getInitialDate().equals(message.getInitialDate()) &&
                    checkIfTimeOverlaps(message, message1)) {
                return false;
            }
        }
        return true;
    }


    private boolean checkIfTimeOverlaps(Message message1, Message message2) {
        return !(message1.getEndTime() < message2.getInitialTime()) && !(message1.getInitialTime() > message2.getEndTime());
    }

    /**
     * Gets messages.
     *
     * @return the messages
     */
    public List<Message> getMessages() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return new ArrayList<>(messages);
    }


}
