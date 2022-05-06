package com.zavadski.service.api;

import com.zavadski.model.Message;
import com.zavadski.model.dto.CreateMessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    List<Message> findAllMessages();

    Message findMessageById(UUID id);

    void createMessage(CreateMessageDto createMessageDto);

    void updateMessage(CreateMessageDto createMessageDto) throws Exception;

    void deleteMessage(UUID id) throws Exception;

}
