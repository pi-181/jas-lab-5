package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Group;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.RepositoryService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.SimpleService")
public class GroupService extends RepositoryService<Group> {

    public GroupService() {
        super(Database.getDatabase().getGroupRepository(), () -> new Group[0]);
    }

}
