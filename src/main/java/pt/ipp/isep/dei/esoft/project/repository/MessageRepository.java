package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.OfferState;


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
    public List<Message> getMessageRequests() {
        List<Message> messageList = messages;
        List<Message> messageRequests = new ArrayList<>();
        
        for (Message message : messageList) {
            if (message.getMessageState().equals(MessageState.ANSWERED)) {
                messageRequests.add(message);
            }
        }

        return messageRequests;
    }

    /**
     * Remove message boolean.
     *
     * @param message the message
     * @return the boolean
     */
    public boolean removeMessage(Message message) {
        return messages.remove(message);
    }

}