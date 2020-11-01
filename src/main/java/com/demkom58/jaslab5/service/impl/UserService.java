package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.User;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IUserService;
import com.demkom58.jaslab5.service.RepositoryService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IUserService")
public class UserService extends RepositoryService<User> implements IUserService {

    public UserService() {
        super(Database.getDatabase().getUserRepository(), () -> new User[0]);
    }
}
