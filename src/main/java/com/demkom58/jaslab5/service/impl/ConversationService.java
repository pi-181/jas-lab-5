package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Conversation;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.RepositoryService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.SimpleService")
public class ConversationService extends RepositoryService<Conversation> {

    public ConversationService() {
        super(Database.getDatabase().getConversationRepository(), () -> new Conversation[0]);
    }

}
