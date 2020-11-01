package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.Group;
import com.demkom58.jaslab3.model.GroupSubscription;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IGroupService {
    @WebMethod
    boolean put(Group group);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    Group get(int id);

    @WebMethod
    Group[] getAll();
}
