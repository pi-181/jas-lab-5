package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Message;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IMessageService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IMessageService")
public class MessageService implements IMessageService {
    private final CrudRepository<Integer, Message> repository;
    public MessageService() {
        repository = Database.getDatabase().getMessageRepository();
    }

    @Override
    public boolean put(Integer messageId, Integer sender, String textMessage,
                       Boolean removed, Integer conversation) {
        repository.save(new Message(messageId, sender, textMessage, removed, conversation));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public Message get(int id) {
        return repository.getById(id);
    }

    @Override
    public Message[] getAll() {
        return repository.getAll().toArray(new Message[0]);
    }
}
