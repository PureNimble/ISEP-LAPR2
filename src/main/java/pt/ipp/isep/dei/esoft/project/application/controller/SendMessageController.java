package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.Property;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.User;
import pt.ipp.isep.dei.esoft.project.repository.ComissionRepository;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.PublishedAnnouncementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SendMessageController {

    MessageRepository messageRepository = null;

    public SendMessageController() {
        getMessageRepository();
    }

    private MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();

            //Get the MessageRepository
            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }


    public List<Message> getMessage() {
        MessageRepository messageRepository = getMessageRepository();
        return messageRepository.getMessages();
    }

    public Optional<Message> createNewMessageToAgent (String name, String description, int phoneNumber, Date date, int intialTime, int endTime, PublishedAnnouncement publishedAnnouncement) {
        Message messageSent = new Message();
        messageSent.setName(name);
        messageSent.setInitialDate(date);
        messageSent.setDescription(description);
        messageSent.setInitialTime(intialTime);
        messageSent.setEndTime(endTime);
        messageSent.setPhoneNumber(phoneNumber);
        messageSent.setPublishedAnnouncement(publishedAnnouncement);
        return messageRepository.add(messageSent);
    }
}
