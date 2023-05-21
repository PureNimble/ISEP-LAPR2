package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepository {

    private ArrayList<Message> messages = new ArrayList<>();

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
        for(Message message1: messages){
            if(message1.getPhoneNumber() == message.getPhoneNumber() && message1.getInitialDate().equals(message.getInitialDate()) &&
                    checkIfTimeOverlaps(message, message1)) {
                return false;
            }
        }
        return true;
    }


    private boolean checkIfTimeOverlaps(Message message1, Message message2) {
        return !(message1.getEndTime() < message2.getInitialTime()) && !(message1.getInitialTime() > message2.getEndTime());
    }

    public List<Message> getMessages() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return messages;
    }

}
