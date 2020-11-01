package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.ConversationSubscription;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IConversationSubscriptionService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IConversationSubscriptionService")
public class ConversationSubscriptionService implements IConversationSubscriptionService {
    private final CrudRepository<Integer, ConversationSubscription> repository;
    public ConversationSubscriptionService() {
        repository = Database.getDatabase().getConversationSubscriptionRepository();
    }

    @Override
    public boolean put(Integer subscriptionId, Integer subscriber,
                       Integer conversation, Long creationTime) {
        repository.save(new ConversationSubscription(subscriptionId, subscriber, conversation, creationTime));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public ConversationSubscription get(int id) {
        return repository.getById(id);
    }

    @Override
    public ConversationSubscription[] getAll() {
        return repository.getAll().toArray(new ConversationSubscription[0]);
    }

}
