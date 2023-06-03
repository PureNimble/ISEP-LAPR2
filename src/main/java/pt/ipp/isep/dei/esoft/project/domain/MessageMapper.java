package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Message mapper.
 */
public class MessageMapper {

    /**
     * To dto list.
     *
     * @param messageList the message list
     * @return the list
     */
    public List<MessageDto> toDto(List<Message> messageList) {

        List<MessageDto> messageDtos = new ArrayList<>();

        for (Message message : messageList) {
            MessageDto messageDto;
            String name = message.getName();
            long phoneNumber = message.getPhoneNumber();
            String description = message.getDescription();
            Date initialDate = message.getInitialDate();
            int initialTime = message.getInitialTime();
            int endTime = message.getEndTime();
            PublishedAnnouncement publishedAnnouncement = message.getPublishedAnnouncement();
            messageDto = toDtoObject(name,phoneNumber, description, initialDate, initialTime, endTime, publishedAnnouncement);
            messageDtos.add(messageDto);


        }

        return messageDtos;

    }

    /**
     * To dto object message dto.
     *
     * @param name                  the name
     * @param phoneNumber           the phone number
     * @param description           the description
     * @param initialDate           the initial date
     * @param initialTime           the initial time
     * @param endTime               the end time
     * @param publishedAnnouncement the published announcement
     * @return the message dto
     */
    public MessageDto toDtoObject(String name, long phoneNumber, String description, Date initialDate, int initialTime, int endTime, PublishedAnnouncement publishedAnnouncement) {

        MessageDto messageDto;
        messageDto = new MessageDto(name,phoneNumber, description, initialDate, initialTime, endTime, publishedAnnouncement);
        return messageDto;
    }
}
