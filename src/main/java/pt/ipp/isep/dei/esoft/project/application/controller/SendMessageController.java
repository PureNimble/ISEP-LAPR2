package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageState;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * The type Send message controller.
 */
public class SendMessageController {

    /**
     * The Message repository.
     */
    MessageRepository messageRepository = null;

    /**
     * Instantiates a new Send message controller.
     */
    public SendMessageController() {
        getMessageRepository();
    }
    /**
     * Retrieves the MessageRepository instance.
     *
     * @return The MessageRepository instance.
     */
    private MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the MessageRepository
            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }


    /**
     * Gets message.
     *
     * @return the message
     */
    public List<Message> getMessage() {
        MessageRepository messageRepository = getMessageRepository();
        return messageRepository.getMessages();
    }

    /**
     * Create new message to agent optional.
     *
     * @param name                  the name
     * @param description           the description
     * @param phoneNumber           the phone number
     * @param date                  the date
     * @param intialTime            the intial time
     * @param endTime               the end time
     * @param publishedAnnouncement the published announcement
     * @return the optional
     */
    public Optional<Message> createNewMessageToAgent (String name, String description, long phoneNumber, Date date, int intialTime, int endTime, PublishedAnnouncement publishedAnnouncement, MessageState messageState) {
        Message messageSent = new Message();
        messageSent.setName(name);
        messageSent.setInitialDate(date);
        messageSent.setDescription(description);
        messageSent.setInitialTime(intialTime);
        messageSent.setEndTime(endTime);
        messageSent.setPhoneNumber(phoneNumber);
        messageSent.setPublishedAnnouncement(publishedAnnouncement);
        messageSent.setMessageState(messageState);
        return messageRepository.add(messageSent);
    }
}
