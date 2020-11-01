package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Post;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.RepositoryService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.SimpleService")
public class PostService extends RepositoryService<Post> {

    public PostService() {
        super(Database.getDatabase().getPostRepository(), () -> new Post[0]);
    }

}
