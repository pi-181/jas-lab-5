package com.demkom58.jaslab5.service.impl;

import com.demkom58.jaslab3.model.User;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab4.config.Database;
import com.demkom58.jaslab5.service.IUserService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.demkom58.jaslab5.service.IUserService")
public class UserService implements IUserService {
    private final CrudRepository<Integer, User> repository;
    public UserService() {
        repository = Database.getDatabase().getUserRepository();
    }

    @Override
    public boolean put(Integer id, String username, String surname, String patronymic,
                       Long birthdayDate, Long creationTime, String email, Boolean verified, Boolean mailConfirmed) {
        repository.save(
                new User(id, username, surname, patronymic, birthdayDate,
                        creationTime, email, verified, mailConfirmed
                )
        );
        return true;
    }

    @Override
    public boolean delete(int id) {
        return repository.removeById(id);
    }

    @Override
    public User get(int id) {
        return repository.getById(id);
    }

    @Override
    public User[] getAll() {
        return repository.getAll().toArray(new User[0]);
    }

}
