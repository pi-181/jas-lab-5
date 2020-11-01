package com.demkom58.jaslab5.service;

import com.demkom58.jaslab3.model.ConversationSubscription;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IConversationSubscriptionService {
    @WebMethod
    boolean put(Integer subscriptionId, Integer subscriber,
                Integer conversation, Long creationTime);

    @WebMethod
    boolean delete(int id);

    @WebMethod
    ConversationSubscription get(int id);

    @WebMethod
    ConversationSubscription[] getAll();
}
