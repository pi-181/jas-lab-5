package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Group;
import com.demkom58.jaslab3.model.Post;
import com.demkom58.jaslab3.model.User;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IPostService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IPostService")
public class PostService implements IPostService {
    private final CrudRepository<Integer, Post> repository;
    public PostService() {
        repository = Database.getDatabase().getPostRepository();
    }

    @Override
    public boolean put(Integer postId, String postContent, Integer views,
                       Integer group, Integer author, Long postDate) {
        repository.save(new Post(postId, postContent, views, group, author, postDate));
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public Post get(int id) {
        return repository.getById(id);
    }

    @Override
    public Post[] getAll() {
        return repository.getAll().toArray(new Post[0]);
    }
}
