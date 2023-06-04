package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Message;
import pt.ipp.isep.dei.esoft.project.domain.MessageDto;
import pt.ipp.isep.dei.esoft.project.domain.MessageMapper;
import pt.ipp.isep.dei.esoft.project.repository.MessageRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ListMessageController {

    private MessageRepository messageRepository = null;

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

    public List<Message> getMessagesByAscendingDate() {
        MessageRepository messageRepository = getMessageRepository();
        return messageRepository.getMessagesByAscendingDate();
    }

    public List<MessageDto> toDtoAscendingArea() {
        MessageMapper messageMapper = new MessageMapper();

        return messageMapper.toDto(getMessagesByAscendingDate());
    }

}
