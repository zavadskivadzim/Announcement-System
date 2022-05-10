package com.zavadski.rest;

import com.zavadski.model.Message;
import com.zavadski.model.dto.CreateMessageDto;
import com.zavadski.service.api.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {

    private final MessageService messageService;

    private static final Logger logger = LogManager.getLogger(MessageController.class);

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/admin/messages")
    public final List<Message> findAllMessages() {

        logger.info("find All Messages");

        return messageService.findAllMessages();
    }

    @PostMapping(path = "/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public final void createComment(@RequestBody CreateMessageDto createMessageDto) {

        logger.info("create Message ({})", createMessageDto);

        messageService.createMessage(createMessageDto);
    }

    @PutMapping(path = "/messages")
    public final void updateComment(@RequestBody CreateMessageDto createMessageDto) throws Exception {

        logger.info("update Message ({})", createMessageDto);

        messageService.updateMessage(createMessageDto);
    }

    @DeleteMapping(value = "/messages/{id}")
    public void deleteMessage(@PathVariable UUID id) throws Exception {

        logger.info("delete Message by id={}", id);

        messageService.deleteMessage(id);
    }
}
