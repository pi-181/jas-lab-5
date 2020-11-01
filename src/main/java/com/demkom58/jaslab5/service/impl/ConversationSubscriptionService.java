package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.ConversationSubscription;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.RepositoryService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.SimpleService")
public class ConversationSubscriptionService extends RepositoryService<ConversationSubscription> {

    public ConversationSubscriptionService() {
        super(Database.getDatabase().getConversationSubscriptionRepository(), () -> new ConversationSubscription[0]);
    }

}
