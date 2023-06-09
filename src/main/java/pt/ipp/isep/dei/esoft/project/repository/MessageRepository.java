package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Message;


import java.util.*;

/**
 * The type Message repository.
 */
public class MessageRepository {

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
            System.out.println("\n\nMessage sent with success!\n\n");
        }

        if (!operationSuccess) {
            System.out.println("Could not send message, a visit is already scheduled for the same hours.");
            newMessage = Optional.empty();
        }
        return newMessage;
    }

    private boolean validateMessage(Message message) {
        for (Message message1 : messages) {
            if (message1.getPhoneNumber() == message.getPhoneNumber() && message1.getInitialDate().equals(message.getInitialDate()) &&
                    checkIfTimeOverlaps(message, message1)) {

                return false;
//            }else {
//                Message messageSent = new Message();
//                Optional<Message> result = add(messageSent);
//                if (result.isPresent()) {
//                    Message lastMessage = getLastMessageSent();
//                    System.out.println(lastMessage.toString());
//                }
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
        return new ArrayList<>(messages);
    }

    public List<Message> getMessagesByAscendingDate() {
        List<Message> resultList = new ArrayList<Message>();

        for (Message message : messages) {
            resultList.add(message);
        }


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
     * Gets last message sent.
     *
     * @return the last message sent
     */
//    public Message getLastMessageSent() {
//        if (messages.isEmpty()) {
//            return null;
//        } else {
//            return messages.get(messages.size() - 1);
//        }
//    }

}