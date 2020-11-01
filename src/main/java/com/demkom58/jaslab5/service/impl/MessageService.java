package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Message;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.RepositoryService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.SimpleService")
public class MessageService extends RepositoryService<Message> {

    public MessageService() {
        super(Database.getDatabase().getMessageRepository(), () -> new Message[0]);
    }

}
