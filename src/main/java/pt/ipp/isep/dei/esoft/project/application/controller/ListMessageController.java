package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageDto;
import pt.ipp.isep.dei.esoft.project.domain.MessageMapper;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.*;

public class ListMessageController {
    MessageRepository messageRepository = null;
    AuthenticationRepository authenticationRepository = null;
    UserRepository userRepository = null;
    //private UserRepository userRepository;

    public ListMessageController() {
        getMessageRepository();
        getAuthenticationRepository();
        getUserRepository();
    }

    public ListMessageController(MessageRepository messageRepository, AuthenticationRepository authenticationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
    }

    private MessageRepository getMessageRepository() {
        if (messageRepository == null) {
            Repositories repositories = Repositories.getInstance();

            messageRepository = repositories.getMessageRepository();
        }
        return messageRepository;
    }

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

    public void sortBookingRequests(List<Message> bookingRequests) {
        Collections.sort(bookingRequests, Comparator.comparing(Message::getInitialDate));
    }



    public List<Message> getBookingRequestsForPeriod(Date beginDate, Date endDate) {
        List<Message> bookingRequests = new ArrayList<>();
        MessageRepository messageRepository1 = getMessageRepository();
        AuthenticationRepository authenticationRepository1 = getAuthenticationRepository();
        for (Message message : messageRepository1.getMessages()) {
            if (message.getInitialDate().compareTo(beginDate) >= 0 && message.getInitialDate().compareTo(endDate) <= 0) {
                if(message.getPublishedAnnouncement().getAgent().getEmail().equals(authenticationRepository1.getCurrentUserSession().getUserId().getEmail())) {
                    bookingRequests.add(message);
                }

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
