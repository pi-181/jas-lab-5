package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.Group;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IGroupService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IGroupService")
public class GroupService implements IGroupService {
    private final CrudRepository<Integer, Group> repository;
    public GroupService() {
        repository = Database.getDatabase().getGroupRepository();
    }

    @Override
    public boolean put(Group p) {
        repository.save(p);
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public Group get(int id) {
        return repository.getById(id);
    }

    @Override
    public Group[] getAll() {
        return repository.getAll().toArray(new Group[0]);
    }
}
