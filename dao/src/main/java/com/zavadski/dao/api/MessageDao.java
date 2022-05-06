package com.zavadski.dao.api;

import com.zavadski.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageDao {

    List<Message> findAll();

    Message findById(UUID id);

    Message save(Message message);

    Message update(Message message);

    void delete(UUID id);

}
