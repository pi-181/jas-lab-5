package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.GroupSubscription;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IGroupSubscriptionService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IGroupSubscriptionService")
public class GroupSubscriptionService implements IGroupSubscriptionService {
    private final CrudRepository<Integer, GroupSubscription> repository;
    public GroupSubscriptionService() {
        repository = Database.getDatabase().getGroupSubscriptionRepository();
    }

    @Override
    public boolean put(Integer subscriptionId, Integer subscriber, Integer group,
                       Long creationTime, Boolean accepted) {
        repository.save(new GroupSubscription(subscriptionId, subscriber, group, creationTime, accepted));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public GroupSubscription get(int id) {
        return repository.getById(id);
    }

    @Override
    public GroupSubscription[] getAll() {
        return repository.getAll().toArray(new GroupSubscription[0]);
    }

}
