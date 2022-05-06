package com.zavadski.service;

import com.zavadski.dao.api.MessageDao;
import com.zavadski.model.Message;
import com.zavadski.model.User;
import com.zavadski.model.dto.CreateMessageDto;
import com.zavadski.service.api.MessageService;
import com.zavadski.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;
    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, UserService userService) {
        this.messageDao = messageDao;
        this.userService = userService;
    }

    @Override
    public List<Message> findAllMessages() {
        return messageDao.findAll();
    }

    @Override
    public Message findMessageById(UUID id) {
        return messageDao.findById(id);
    }

    @Override
    public void createMessage(CreateMessageDto createMessageDto) {
        Message message = new Message();
        User author = userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()));
        message.setSender(author);
        message.setReceiver(userService.findUserById(createMessageDto.getReceiverId()));
        message.setBody(createMessageDto.getBody());
        message.setDateOfSending(LocalDateTime.now());
        messageDao.save(message);
    }

    @Override
    public void updateMessage(CreateMessageDto createMessageDto) throws Exception {
        Message message = findMessageById(createMessageDto.getId());
        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(message.getSender())) {
            message.setBody(createMessageDto.getBody());
            message.setDateOfEditing(LocalDateTime.now());
            messageDao.update(message);
        } else {
            throw new Exception("you can't update this Message");
        }
    }

    @Override
    public void deleteMessage(UUID id) throws Exception {

        Message message = findMessageById(id);
        if (userService.findUserByLogin(Objects.requireNonNull(CurrentUserService.getCurrentUserLogin()))
                .equals(message.getSender())) {
            messageDao.delete(id);
        } else {
            throw new Exception("you can't delete this Message");
        }
    }

}
