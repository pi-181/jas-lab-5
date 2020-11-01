package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Conversation;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IConversationService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IConversationService")
public class ConversationService implements IConversationService {
    private final CrudRepository<Integer, Conversation> repository;
    public ConversationService() {
        repository = Database.getDatabase().getConversationRepository();
    }

    @Override
    public boolean put(Integer conversationId, String conversationName, Boolean removed, Boolean personal) {
        repository.save(new Conversation(conversationId, conversationName, removed, personal));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public Conversation get(int id) {
        return repository.getById(id);
    }

    @Override
    public Conversation[] getAll() {
        return repository.getAll().toArray(new Conversation[0]);
    }

}
