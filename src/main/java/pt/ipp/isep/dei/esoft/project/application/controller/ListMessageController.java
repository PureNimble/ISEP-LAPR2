package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageDto;
import pt.ipp.isep.dei.esoft.project.domain.MessageMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.*;

public class ListMessageController {
    private MessageRepository messageRepository = new MessageRepository();
    private UserRepository userRepository;

    public ListMessageController() {
        getMessageRepository();
    }

    private MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();

            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }

    public void sortBookingRequests(List<Message> bookingRequests) {
        Collections.sort(bookingRequests, Comparator.comparing(Message::getInitialDate));
    }

    public List<Message> getBookingRequestsForPeriod(Date beginDate, Date endDate) {
        List<Message> bookingRequests = new ArrayList<>();
        for (Message message : messageRepository.getMessages()) {
            if (message.getInitialDate().compareTo(beginDate) >= 0 && message.getInitialDate().compareTo(endDate) <= 0) {
                bookingRequests.add(message);
            }
        }
        return bookingRequests;
    }

    public List<Message> getMessagesByAscendingDate() {
        MessageRepository messageRepository = getMessageRepository();
        return messageRepository.getMessagesByAscendingDate();
    }

    public List<MessageDto> toDtoAscendingArea() {
        MessageMapper messageMapper = new MessageMapper();

        return messageMapper.toDto(getMessagesByAscendingDate());
    }


}
