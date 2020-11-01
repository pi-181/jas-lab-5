package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IUserService {
    @WebMethod
    boolean put(Integer id, String username, String surname, String patronymic, Long birthdayDate,
                Long creationTime, String email, Boolean verified, Boolean mailConfirmed);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    User get(int id);

    @WebMethod
    User[] getAll();
}
