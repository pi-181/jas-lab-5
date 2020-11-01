package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.GroupSubscription;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IGroupSubscriptionService {
    @WebMethod
    boolean put(Integer subscriptionId, Integer subscriber, Integer group,
                Long creationTime, Boolean accepted);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    GroupSubscription get(int id);

    @WebMethod
    GroupSubscription[] getAll();
}
